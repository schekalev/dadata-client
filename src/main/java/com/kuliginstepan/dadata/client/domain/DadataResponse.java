package com.kuliginstepan.dadata.client.domain;

import java.util.List;

public interface DadataResponse<T extends Suggestion> {
    List<T> getSuggestions();
}
