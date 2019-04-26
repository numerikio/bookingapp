package com.littlehotel.service;

import com.littlehotel.model.PersistentLogin;
import com.littlehotel.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service("persistentTokenRepository")
public class PersistentTokenServiceImpl implements PersistentTokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        PersistentLogin persistentLogin = new PersistentLogin();
        persistentLogin.setUsername(persistentRememberMeToken.getUsername());
        persistentLogin.setSeries(persistentRememberMeToken.getSeries());
        persistentLogin.setToken(persistentRememberMeToken.getTokenValue());
        persistentLogin.setLast_used(persistentRememberMeToken.getDate());
        tokenRepository.save(persistentLogin);
    }

    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        PersistentLogin persistentLogin = tokenRepository.getOne(seriesId);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLast_used(lastUsed);
        tokenRepository.save(persistentLogin);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        PersistentLogin persistentLogin = tokenRepository.getOne(seriesId);
        return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                persistentLogin.getToken(), persistentLogin.getLast_used());
    }

    @Override
    public void removeUserTokens(String username) {
        List<PersistentLogin> existingToken = tokenRepository.findByUsername(username);
        if (existingToken.size() > 0) {
            for (int i = 0; i < existingToken.size(); i++) {
                tokenRepository.delete(existingToken.get(i));
            }
        }
    }
}
