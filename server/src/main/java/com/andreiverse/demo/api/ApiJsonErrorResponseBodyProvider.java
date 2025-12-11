package com.andreiverse.demo.api;

import com.andreiverse.demo.domain.ApiErrorResponse;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.response.ErrorContext;
import io.micronaut.http.server.exceptions.response.JsonErrorResponseBodyProvider;
import jakarta.inject.Singleton;

@Singleton
@Replaces(JsonErrorResponseBodyProvider.class)
public class ApiJsonErrorResponseBodyProvider implements JsonErrorResponseBodyProvider<ApiErrorResponse> {

    @Override
    public @NonNull ApiErrorResponse body(@NonNull ErrorContext ctx, @NonNull HttpResponse<?> response) {
        return new ApiErrorResponse(ctx.getRootCause().get().getMessage());
    }

}
