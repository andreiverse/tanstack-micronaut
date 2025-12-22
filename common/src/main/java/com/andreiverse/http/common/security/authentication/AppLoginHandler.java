package com.andreiverse.http.common.security.authentication;

import java.util.List;

import io.micronaut.context.annotation.Replaces;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.AuthorizationException;
import io.micronaut.security.handlers.LoginHandler;
import io.micronaut.security.session.SessionPopulator;
import io.micronaut.session.Session;
import io.micronaut.session.SessionStore;
import io.micronaut.session.http.SessionForRequest;

@Slf4j
@Singleton
@Replaces(LoginHandler.class)
@RequiredArgsConstructor
public class AppLoginHandler implements LoginHandler<HttpRequest<?>, MutableHttpResponse<?>> {

    private final List<SessionPopulator<HttpRequest<?>>> sessionPopulators;
    protected final SessionStore<Session> sessionStore;

    @Override
    public MutableHttpResponse<?> loginFailed(AuthenticationResponse authenticationResponse, HttpRequest<?> request) {
        log.info("Login failed");
        throw new AuthorizationException(null);
    }

    @Override
    public MutableHttpResponse<?> loginRefresh(Authentication authentication, String refreshToken,
            HttpRequest<?> request) {
        throw new RuntimeException("Login refresh failed");
    }

    @Override
    public MutableHttpResponse<?> loginSuccess(Authentication authentication, HttpRequest<?> request) {
        saveAuthenticationInSession(authentication, request);
        return HttpResponse.ok("Login successful");
    }

    /**
     * Saves the authentication in the session.
     * 
     * @param authentication Authentication
     * @param request        HTTP Request
     */
    private void saveAuthenticationInSession(Authentication authentication, HttpRequest<?> request) {
        Session session = SessionForRequest.find(request)
                .orElseGet(() -> SessionForRequest.create(sessionStore, request));
        sessionPopulators
                .forEach(sessionPopulator -> sessionPopulator.populateSession(request, authentication, session));
    }

}
