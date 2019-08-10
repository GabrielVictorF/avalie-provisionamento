package Models;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.androidmanagement.v1.AndroidManagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class Project {
    private String projectId;
    private String credentialPath;
    private AndroidManagement androidManagement;


    public Project(String projectId, String credentialPath, String oAuth) throws IOException, GeneralSecurityException {
        this.projectId = projectId;
        this.credentialPath = credentialPath;
        this.oAuth = oAuth;
    }

    public String getoAuth() {

        return oAuth;
    }

    public void setoAuth(String oAuth) {
        this.oAuth = oAuth;
    }

    private String oAuth;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCredentialPath() {
        return credentialPath;
    }

    public void setCredentialPath(String credentialPath) {
        this.credentialPath = credentialPath;
    }
}
