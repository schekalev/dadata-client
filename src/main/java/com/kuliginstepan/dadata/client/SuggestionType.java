package com.kuliginstepan.dadata.client;

import com.kuliginstepan.dadata.client.domain.address.AddressResponse;
import com.kuliginstepan.dadata.client.domain.bank.BankResponse;
import com.kuliginstepan.dadata.client.domain.court.CourtResponse;
import com.kuliginstepan.dadata.client.domain.delivery.DeliveryResponse;
import com.kuliginstepan.dadata.client.domain.email.EmailResponse;
import com.kuliginstepan.dadata.client.domain.fio.FioResponse;
import com.kuliginstepan.dadata.client.domain.fms.FmsUnitResponse;
import com.kuliginstepan.dadata.client.domain.fns.FnsUnitResponse;
import com.kuliginstepan.dadata.client.domain.organization.OrganizationResponse;
import com.kuliginstepan.dadata.client.domain.postal.PostalOfficeResponse;

public enum SuggestionType {

    ORGANIZATION(OrganizationResponse.class, "/party", "/party"),
    ADDRESS(AddressResponse.class, "/address", "/address"),
    BANK(BankResponse.class, "/bank", null),
    FIO(FioResponse.class, "/fio", null),
    EMAIL(EmailResponse.class, "/email", null),
    FMS(FmsUnitResponse.class, "/fms_unit", "/fms_unit"),
    FNS(FnsUnitResponse.class, "/fns_unit", "/fns_unit"),
    POSTAL_OFFICE(PostalOfficeResponse.class, "/postal_office", "/postal_office"),
    COURT(CourtResponse.class, "/region_court", "/region_court"),
    DELIVERY(DeliveryResponse.class, null, "/delivery");


    String suggestOperationPrefix;
    String findByIdOperationPrefix;
    Class responseClass;

    SuggestionType(Class responseClass, String suggestOperationPrefix, String findByIdOperationPrefix) {
        this.responseClass = responseClass;
        this.suggestOperationPrefix = suggestOperationPrefix;
        this.findByIdOperationPrefix = findByIdOperationPrefix;
    }

    public Class getResponseClass() {
        return responseClass;
    }

    public String getSuggestOperationPrefix() {
        if (suggestOperationPrefix == null) {
            throw new UnsupportedOperationException();
        }
        return suggestOperationPrefix;
    }

    public String getFindByIdOperationPrefix() {
        if (findByIdOperationPrefix == null) {
            throw new UnsupportedOperationException();
        }
        return findByIdOperationPrefix;
    }
}
