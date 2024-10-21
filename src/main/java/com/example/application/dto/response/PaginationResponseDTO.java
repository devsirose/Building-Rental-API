package com.example.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaginationResponseDTO<T> {
    @JsonProperty("total_records")
    private Long totalRecords;
    @JsonProperty("current_page")
    private Integer currentPage;
    @JsonProperty("total_pages")
    private Integer totalPages;
    @JsonProperty("next_page")
    private Integer nextPage;
    @JsonProperty("prev_page")
    private Integer prev_page;
}
