package com.Security.Model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "oauth_client_details")
public class OauthClient {
    @GenericGenerator(
            name = "oauthCleintSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "OAUTH_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "oauthCleintSequenceGenerator")
    @Id
    @Column(name = "id_oauth_client", updatable = false, nullable = false)
    private long idOauthClient;
    @Column(name = "client_id", updatable = false, nullable = false, unique = true)
    private  String tenantName;
    @Column(name = "id_company", updatable = false, nullable = false)
    private long idCompany;
    @Column(name = "resource_ids")
    private  String resourceIds;
    @Column(name = "client_secret", nullable = false)
    private  String password;
    @Column(name = "scope")
    private  String scope;
    @Column(name = "authorized_grant_types", nullable = false)
    private  String authorizedGrantTypes;
    @Column(name = "web_server_redirect_uri")
    private  String webServerRedirectUri;
    @Column(name = "authorities")
    private  String authorities;
    @Column(name = "access_token_validity", nullable = false)
    private  String accessTokenValidity;
    @Column(name = "refresh_token_validity", nullable = false)
    private  String refreshTokenValidity;
    @Column(name = "additional_information")
    private  String additionalInformation;
    @Column(name = "autoapprove")
    private  String autoApprove;

    public OauthClient() {
    }

    public long getIdOauthClient() {
        return idOauthClient;
    }

    public void setIdOauthClient(long idOauthClient) {
        this.idOauthClient = idOauthClient;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(String accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public String getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(String refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getAutoApprove() {
        return autoApprove;
    }

    public void setAutoApprove(String autoApprove) {
        this.autoApprove = autoApprove;
    }

    public long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }
}
