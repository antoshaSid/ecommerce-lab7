package com.asidliar.lab7.ecommerce.dto.perfume;

import com.asidliar.lab7.ecommerce.enums.SearchPerfume;
import lombok.Data;

@Data
public class SearchTypeRequest {
    private SearchPerfume searchType;
    private String text;
}
