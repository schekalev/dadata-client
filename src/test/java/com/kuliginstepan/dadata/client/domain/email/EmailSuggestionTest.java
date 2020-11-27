package com.kuliginstepan.dadata.client.domain.email;

import static org.junit.Assert.assertEquals;

import com.kuliginstepan.dadata.client.SuggestionType;
import org.junit.Test;

public class EmailSuggestionTest {


    @Test
    public void getSuggestOperationPrefix() {
        assertEquals("/email", SuggestionType.EMAIL.getSuggestOperationPrefix());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getFindByIdOperationPrefix() {
        SuggestionType.EMAIL.getFindByIdOperationPrefix();
    }
}