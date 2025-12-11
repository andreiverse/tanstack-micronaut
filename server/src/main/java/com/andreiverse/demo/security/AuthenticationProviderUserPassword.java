package com.andreiverse.demo.security;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.provider.HttpRequestReactiveAuthenticationProvider;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Slf4j
@Singleton
public class AuthenticationProviderUserPassword<B> implements HttpRequestReactiveAuthenticationProvider<B> {

    @Override
    public Publisher<AuthenticationResponse> authenticate(
            @Nullable HttpRequest<B> httpRequest,
            @NonNull AuthenticationRequest<String, String> authenticationRequest) {
        log.info("Authentication request: {}/{}", authenticationRequest.getIdentity(),
                authenticationRequest.getSecret());
        return Flux.create(emitter -> {
            if (authenticationRequest.getIdentity().equals("test@example.com") &&
                    authenticationRequest.getSecret().equals("password")) {
                log.info("Authentication successful");
                emitter.next(AuthenticationResponse.success((String) authenticationRequest.getIdentity()));
                emitter.complete();
            } else {
                log.error("Authentication failed");
                emitter.error(AuthenticationResponse.exception());
            }
        }, FluxSink.OverflowStrategy.ERROR);
    }
}
