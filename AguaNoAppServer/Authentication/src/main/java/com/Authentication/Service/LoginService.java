package com.Authentication.Service;


import com.Authentication.Repository.LoginRepository;
import com.Security.Model.OauthClient;
import com.Security.Repository.OauthClientRepository;
import com.User.Model.User;
import com.Util.Utils.ConvetStringToJson;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@Service
@Path("/login")
@Produces("application/json")
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class LoginService {
    @Inject
    private LoginRepository repositoryUser;
    @Inject
    private OauthClientRepository repositoryOauthClient;

   @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@CrossOrigin(origins = "*", maxAge = 3600,
    //        allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})

    public String authObtainAccessToken(@QueryParam("username") String username, @QueryParam("password") String password) throws NotFoundException {
    //public String authObtainAccessToken(@QueryParam("username") String username, @QueryParam("password") String password) throws NotFoundException {
        String ret = null;
        try {
          //  String pa = new BCryptPasswordEncoder().encode(password);
            User user = repositoryUser.findByUserNameAndPassword(username, password);
            if (user == null) {
                //throw new NotFoundException();
                ret = "USER NOT FOUND!";
            }else
            {
                OauthClient oauthClientTenant = repositoryOauthClient.findByIdCompany(user.getIdCompany());
                if (oauthClientTenant == null) {
                    ret = "COMPANY NOT AUTHORIZED!";
                }else
                {
                  //  HttpPost post = new HttpPost("http://35.155.157.83:8080/api/oauth/token");
                    HttpPost post = new HttpPost("http://localhost:8080/oauth/token");
                    CredentialsProvider credsProvider = new BasicCredentialsProvider();
                    //credsProvider.setCredentials( new AuthScope(target.getHostName(), target.getPort()),
                    //credsProvider.setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), HttpGet httpget = new HttpGet
                    //credsProvider.setCredentials(AuthScope.ANY, new BasicScheme()
                    //credsProvider.setCredentials(AuthScope.ANY,
                /*credsProvider.setCredentials(new AuthScope("localhost",9998),
                        new UsernamePasswordCredentials("Tenant_5","secret"));*/

                   // credsProvider.setCredentials(new AuthScope("35.155.157.83",8080),
                    credsProvider.setCredentials(new AuthScope("localhost",8080),
                           // credsProvider.setCredentials(new AuthScope("localhost",8383),
                            new UsernamePasswordCredentials(oauthClientTenant.getTenantName(), oauthClientTenant.getPassword()));
                   /* StringBuilder userString = new StringBuilder();
                    userString.append(username.concat(";").concat(password).concat(";").concat(user.getPerfil()));
                    user.getPerfil();*/

                    List<NameValuePair> arguments = new ArrayList<>();
                    // arguments.add(new BasicNameValuePair("t_id", Long.toString(user.getIdCompany())));
                    //  arguments.add(new BasicNameValuePair("client_id", oauthClientTenant.getTenantName()));
                    //arguments.add(new BasicNameValuePair("client_secret", "secret"));
                    arguments.add(new BasicNameValuePair("grant_type", "password"));
                    // arguments.add(new BasicNameValuePair("username", username.concat(";").concat(password)));
                /*arguments.add(new BasicNameValuePair("username", username));
                arguments.add(new BasicNameValuePair("password", password));*/
                    arguments.add(new BasicNameValuePair("username", user.getUserName()));
                    arguments.add(new BasicNameValuePair("password", user.getPassword()));
                    CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
                    post.setEntity(new UrlEncodedFormEntity(arguments));
                    CloseableHttpResponse response = httpclient.execute(post);

                   // ret = EntityUtils.toString(response.getEntity());
                    HttpEntity entity = response.getEntity();

                    if (entity != null) {
                        /*
                       // String retSrc = EntityUtils.toString(entity);
                        // parsing JSON
                        JSONObject jsonConverted = ConvetStringToJson.convert(EntityUtils.toString(entity));*/
                      //  JSONObject jsonConverted = ConvetStringToJson.convert(EntityUtils.toString(entity));
                        JSONObject jsonConverted = ConvetStringToJson.convert(EntityUtils.toString(entity), oauthClientTenant.getIdCompany());
                      // UserAuthenticated jsonConverted = ConvetStringToJson.toUserAuthenticated(EntityUtils.toString(entity));
                        if (null != jsonConverted){
                          //  System.out.println("DADOS==> " + jsonConverted.getString("access_token"));
                            ret = jsonConverted.toString();
                        }

                       /* JSONObject result = new JSONObject(retSrc); //Convert String to JSON Object

                        JSONArray tokenList = result.getJSONArray("names");
                        JSONObject oj = tokenList.getJSONObject(0);
                        String token = oj.getString("name");*/
                    }
                   // EntityUtils.
                   // System.out.println("DADOS==> " + ret);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

}
