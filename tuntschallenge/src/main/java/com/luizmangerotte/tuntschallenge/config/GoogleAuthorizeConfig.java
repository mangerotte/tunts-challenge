package com.luizmangerotte.tuntschallenge.config;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
public class GoogleAuthorizeConfig {

    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS);
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    public static Credential authorize() throws IOException {

        log.info("Getting the verification code and building a port for the redirect for authentication");
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        log.info("Return authorization");
        return new AuthorizationCodeInstalledApp(getCredentialsToken(), receiver).authorize("user");
    }
    private static InputStreamReader credentialsPathReader(String path) throws FileNotFoundException {
        if (path == null) {
            throw new FileNotFoundException("Resource not found: " + path);
        }
        log.info("Finding the file with credentials data");
        InputStream fileCredentials = GoogleAuthorizeConfig.class.getResourceAsStream(path);
        log.info("Reading the file with the credentials data");
        return new InputStreamReader(fileCredentials);
    }
    private static GoogleClientSecrets clientSecretsReader() throws IOException {
        log.info("Getting client secret from credentials file");
        return GoogleClientSecrets.load(GsonFactory.getDefaultInstance(),
                credentialsPathReader(CREDENTIALS_FILE_PATH));
    }
    private static GoogleAuthorizationCodeFlow getCredentialsToken() throws IOException {
        log.info("Requesting authentication");
        return new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecretsReader(), SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
    }



}
