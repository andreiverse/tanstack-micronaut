package com.andreiverse.http.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Composite annotation for pagination parameters in OpenAPI documentation.
 * Adds standard pagination parameters (page, size, sort) to the API
 * documentation.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@io.swagger.v3.oas.annotations.Parameters({
        @Parameter(name = "page", description = "Page number (zero-based)", schema = @Schema(type = "integer", defaultValue = "0")),
        @Parameter(name = "size", description = "Number of items per page", schema = @Schema(type = "integer", defaultValue = "20")),
        @Parameter(name = "sort", description = "Sort criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.", schema = @Schema(type = "string"))
})
public @interface PaginationParams {
}
