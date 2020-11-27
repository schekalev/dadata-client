package com.kuliginstepan.dadata.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuliginstepan.dadata.client.domain.BasicRequest;
import com.kuliginstepan.dadata.client.domain.DadataResponse;
import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.address.*;
import com.kuliginstepan.dadata.client.domain.bank.BankRequest;
import com.kuliginstepan.dadata.client.domain.bank.BankSuggestion;
import com.kuliginstepan.dadata.client.domain.court.CourtRequest;
import com.kuliginstepan.dadata.client.domain.court.CourtSuggestion;
import com.kuliginstepan.dadata.client.domain.delivery.DeliverySuggestion;
import com.kuliginstepan.dadata.client.domain.email.EmailSuggestion;
import com.kuliginstepan.dadata.client.domain.fio.FioRequest;
import com.kuliginstepan.dadata.client.domain.fio.FioSuggestion;
import com.kuliginstepan.dadata.client.domain.fms.FmsUnitRequest;
import com.kuliginstepan.dadata.client.domain.fms.FmsUnitSuggestion;
import com.kuliginstepan.dadata.client.domain.fns.FnsUnitRequest;
import com.kuliginstepan.dadata.client.domain.fns.FnsUnitSuggestion;
import com.kuliginstepan.dadata.client.domain.organization.FindOrganizationByIdRequest;
import com.kuliginstepan.dadata.client.domain.organization.OrganizationRequest;
import com.kuliginstepan.dadata.client.domain.organization.OrganizationSuggestion;
import com.kuliginstepan.dadata.client.domain.postal.PostalOfficeRequest;
import com.kuliginstepan.dadata.client.domain.postal.PostalOfficeSuggestion;
import com.kuliginstepan.dadata.client.exception.DadataException;
import com.kuliginstepan.dadata.client.exception.ErrorDetails;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Should be build using {@link DadataClientBuilder}
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class DadataClient {

    private static final String SUGGESTION_PREFIX = "suggest";
    private static final String GEOLOCATE_PREFIX = "geolocate";
    private static final String FIND_BY_ID_PREFIX = "findById";

    private final OkHttpClient httpClient;
    private final String apiKey;
    private final String baseUrl;

    public List<OrganizationSuggestion> suggestOrganization(OrganizationRequest request) {
        return suggest(SuggestionType.ORGANIZATION, request);
    }

    public List<AddressSuggestion> suggestAddress(AddressRequest request) {
        return suggest(SuggestionType.ADDRESS, request);
    }

    public List<BankSuggestion> suggestBank(BankRequest request) {
        return suggest(SuggestionType.BANK, request);
    }

    public List<FioSuggestion> suggestFio(FioRequest request) {
        return suggest(SuggestionType.FIO, request);
    }

    public List<EmailSuggestion> suggestEmail(BasicRequest request) {
        return suggest(SuggestionType.EMAIL, request);
    }

    public List<FmsUnitSuggestion> suggestFmsUnit(FmsUnitRequest request) {
        return suggest(SuggestionType.FMS, request);
    }

    public List<FnsUnitSuggestion> suggestFnsUnit(FnsUnitRequest request) {
        return suggest(SuggestionType.FNS, request);
    }

    public List<PostalOfficeSuggestion> suggestPostalOffice(PostalOfficeRequest request) {
        return suggest(SuggestionType.POSTAL_OFFICE, request);
    }

    public List<CourtSuggestion> suggestCourt(CourtRequest request) {
        return suggest(SuggestionType.COURT, request);
    }

    public AddressSuggestion findAddressById(String id) {
        return findById(SuggestionType.ADDRESS, new BasicRequest(id));
    }

    public OrganizationSuggestion findOrganizationById(String id) {
        return findById(SuggestionType.ORGANIZATION, new BasicRequest(id));
    }

    public OrganizationSuggestion findOrganizationById(FindOrganizationByIdRequest request) {
        return findById(SuggestionType.ORGANIZATION, request);
    }

    /**
     * @param id Fms unit code. Format: 'XXX-XXX'
     */
    public FmsUnitSuggestion findFmsUnitById(String id) {
        return findById(SuggestionType.FMS, new BasicRequest(id));
    }

    /**
     * @param id Fns unit code or inn
     */
    public FnsUnitSuggestion findFnsUnitById(String id) {
        return findById(SuggestionType.FNS, new BasicRequest(id));
    }

    /**
     * @param index Postal office index
     */
    public PostalOfficeSuggestion findPostalOfficeById(String index) {
        return findById(SuggestionType.POSTAL_OFFICE, new BasicRequest(index));
    }

    /**
     * @param id Court code
     */
    public CourtSuggestion findCourtById(String id) {
        return findById(SuggestionType.COURT, new BasicRequest(id));
    }

    /**
     * @param id City kladr id
     */
    public DeliverySuggestion findDeliveryById(String id) {
        return findById(SuggestionType.DELIVERY, new BasicRequest(id));
    }

    public List<AddressSuggestion> geolocate(GeolocateRequest request) {
        return executeOperation(AddressResponse.class, request, GEOLOCATE_PREFIX,
                SuggestionType.ADDRESS.getSuggestOperationPrefix());
    }

    public AddressSuggestion iplocate(String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        return get("iplocate" + SuggestionType.ADDRESS.getSuggestOperationPrefix(), params, IplocateResponse.class).getLocation();
    }

    protected <T extends Suggestion> List<T> suggest(SuggestionType suggestionType, BasicRequest request) {
        return executeOperation(suggestionType.getResponseClass(), request, SUGGESTION_PREFIX,
                suggestionType.getSuggestOperationPrefix());
    }

    protected <T extends Suggestion> T findById(SuggestionType suggestionType, BasicRequest request) {
        List<T> suggestions = executeOperation(suggestionType.getResponseClass(), request, FIND_BY_ID_PREFIX,
                suggestionType.getFindByIdOperationPrefix());
        return suggestions.get(0);
    }

    protected <T extends Suggestion, R extends DadataResponse> List<T> executeOperation(Class<R> responseClass,
                                                                                        BasicRequest request,
                                                                                        String operationPrefix,
                                                                                        String suggestionTypePrefix) {
        try {
            R response = post(operationPrefix + suggestionTypePrefix, null, request, responseClass);
            return response.getSuggestions();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    protected <R> R get(String url, Map<String, String> params, Class<R> responseClass) {

        HttpUrl httpUrl = buildUrl(url, params);

        Request.Builder requestBuilder = new Request.Builder().get().url(httpUrl);

        setupToken(requestBuilder);

        Request request = requestBuilder.build();
        return executeRequest(request, responseClass);
    }

    protected <R> R post(String url, Map<String, String> params, Object body, Class<R> responseClass) throws JsonProcessingException {

        HttpUrl httpUrl = buildUrl(url, params);
        ObjectMapper objectMapper = new ObjectMapper();
        String bodyString = objectMapper.writeValueAsString(body);

        RequestBody requestBody = RequestBody.create(bodyString, MediaType.get("application/json; charset=utf-8"));

        okhttp3.Request.Builder requestBuilder = new okhttp3.Request.Builder().post(requestBody).url(httpUrl);
        setupToken(requestBuilder);

        okhttp3.Request request = requestBuilder.build();
        return executeRequest(request, responseClass);
    }

    protected <R> R executeRequest(Request request, Class<R> responseClass) {
        try (okhttp3.Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw prepareException(response);
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                String responseString = responseToString(response);
                return objectMapper.readValue(responseString, responseClass);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected DadataException prepareException(Response response) throws JsonProcessingException {
        int statusCode = response.code();
        String message = responseToString(response);
        ObjectMapper objectMapper = new ObjectMapper();
        ErrorDetails errorDetails = objectMapper.readValue(message, ErrorDetails.class);
        return new DadataException(statusCode, errorDetails);
    }

    protected String responseToString(Response response) {
        try {
            if (response.body() == null) {
                return null;
            } else {
                return Objects.requireNonNull(response.body()).string();
            }
        } catch (NullPointerException e) {
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void setupToken(Request.Builder requestBuilder) {
        if (apiKey != null && !apiKey.isEmpty()) {
            requestBuilder.addHeader("Authorization", "Token " + apiKey);
        }
    }

    protected HttpUrl buildUrl(String url, Map<String, String> params) {
        HttpUrl apiAddressUrl = HttpUrl.parse(baseUrl);
        if (apiAddressUrl == null) {
            throw new RuntimeException("Service address is null");
        }
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme(apiAddressUrl.scheme())
                .host(apiAddressUrl.host())
                .port(apiAddressUrl.port());
        for (String pathSegment : apiAddressUrl.pathSegments()) {
            urlBuilder.addPathSegment(pathSegment);
        }
        urlBuilder.addPathSegments(url);

        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> paramEntry : params.entrySet()) {
                if (paramEntry.getValue() != null) {
                    urlBuilder.addQueryParameter(paramEntry.getKey(), paramEntry.getValue());
                }
            }
        }
        return urlBuilder.build();
    }
}
