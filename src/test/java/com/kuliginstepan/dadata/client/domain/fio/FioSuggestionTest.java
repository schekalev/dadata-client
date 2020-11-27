package com.kuliginstepan.dadata.client.domain.fio;

import static org.junit.Assert.assertEquals;

import com.kuliginstepan.dadata.client.SuggestionType;
import org.junit.Test;

public class FioSuggestionTest {


    @Test
    public void getSuggestOperationPrefix() {
        assertEquals("/fio", SuggestionType.FIO.getSuggestOperationPrefix());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getFindByIdOperationPrefix() {
        SuggestionType.FIO.getFindByIdOperationPrefix();
    }
}