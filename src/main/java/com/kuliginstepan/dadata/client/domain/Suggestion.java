package com.kuliginstepan.dadata.client.domain;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.Value;

@Data
public class Suggestion {

    String value;
    @JsonAlias("unrestricted_value")
    String unrestrictedValue;
}
