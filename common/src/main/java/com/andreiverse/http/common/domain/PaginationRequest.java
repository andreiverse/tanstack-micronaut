package com.andreiverse.http.common.domain;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort;
import io.micronaut.http.annotation.QueryValue;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
@Introspected
public class PaginationRequest {
    @Parameter(description = "Page number (zero-based)", schema = @Schema(defaultValue = "0"))
    @Nullable
    @QueryValue
    private Integer page;

    @Parameter(description = "Number of items per page", schema = @Schema(defaultValue = "20"))
    @Nullable
    @QueryValue
    private Integer size;

    @Parameter(description = "Sort criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")
    @Nullable
    @QueryValue
    private String sort;

    public Pageable toPageable() {
        int p = page != null ? page : 0;
        int s = size != null ? size : 20;
        if (sort == null || sort.isEmpty()) {
            return Pageable.from(p, s);
        }
        List<Sort.Order> orders = List.of(parseOrder(sort));
        return Pageable.from(p, s, Sort.of(orders));
    }

    private Sort.Order parseOrder(String sortStr) {
        if (sortStr == null)
            return null;
        String[] parts = sortStr.split(",");
        String property = parts[0];
        if (parts.length > 1) {
            String direction = parts[1];
            if ("desc".equalsIgnoreCase(direction)) {
                return Sort.Order.desc(property);
            }
        }
        return Sort.Order.asc(property);
    }
}
