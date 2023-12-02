package com.Authentication.Business;

import com.Authentication.Util.RequestWrapper;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;


//@Component
public class AuthenticationFilterResource extends OncePerRequestFilter {
    /*private final JwtConfig jwtConfig;

    public AuthenticationFilterResource(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }*/


    /*
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // 1. get the authentication header. Tokens are supposed to be passed in the authentication header
        String header = request.getHeader(jwtConfig.getHeader());

        // 2. validate the header and check the prefix
        if(header == null || !header.startsWith(jwtConfig.getPrefix())) {
            chain.doFilter(request, response);  		// If not valid, go to the next filter.
            return;
        }

        // If there is no token provided and hence the user won't be authenticated.
        // It's Ok. Maybe the user accessing a public path or asking for a token.

        // All secured paths that needs a token are already defined and secured in config class.
        // And If user tried to access without access token, then he won't be authenticated and an exception will be thrown.

        // 3. Get the token
        String token = header.replace(jwtConfig.getPrefix(), "");

        try {	// exceptions might be thrown in creating the claims if for example the token is expired

            // 4. Validate the token
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret().getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            if(username != null) {
                @SuppressWarnings("unchecked")
                List<String> authorities = (List<String>) claims.get("authorities");

                // 5. Create auth object
                // UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
                // It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

                // 6. Authenticate the user
                // Now, user is authenticated
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            // In case of failure. Make sure it's clear; so guarantee user won't be authenticated
            SecurityContextHolder.clearContext();
        }

        // go to the next filter in the filter chain
        chain.doFilter(request, response);
    }*/

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (Objects.equals(request.getServletPath(), "/oauth/token")) {
            //CRIAR NO BANCO DE DADOS DA EMPRESA, UMA TABELA COM OS CLIENTES(NOME_EMPRESA, ID, TENANT_ID)
            //QUE SERÁ UTILIZADA NO configure(final ClientDetailsServiceConfigurer clients) DO OAUTH2AUTHSERVERCONFIGJWT
            //NESTE BANCO CRIAR UMA TABELA COM OS USUÁRIOS, ONDE O LOGIN SERÁ O EMAIL E DEVE ESTAR RELACIONADO O REGISTRO
            // DO CLIENTE COM O TENTANT_ID DA EMPRESA OU O ID DA MESMA, O TENANT_ID SERÁ PASSADO POR DOMINIO
            // OU PELO ATTRIBUTE HEADER.

            /*byte[] json = ByteStreams.toByteArray(request.getInputStream());

            Map<String, String> jsonMap = new ObjectMapper().readValue(json, Map.class);;
            Map<String, String[]> parameters =
                    jsonMap.entrySet().stream()
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey,
                                    e ->  new String[]{e.getValue()})
                            );
            HttpServletRequest requestWrapper = new RequestWrapper(request, parameters);*/
            //filterChain.doFilter(requestWrapper, response);
            // byte[] json = ByteStreams.newDataInput(request.getParameterMap());
            //Map<String, String> jsonMap = new ObjectMapper().readValue(json, Map.class);;

            Map<String, String[]> additionalInfo = request.getParameterMap();
           /* System.out.println("Original>>>>>>>>>>>>>>>>>>>>>>>>>" + request.getParameter("username"));
            request.setAttribute("username","adriele@teste.com"); */
            /* Map<String, String[]> additionalInfo = new HashMap<>();
            Map<String, String> pega = new HashMap<>();
            String[] teste = new String[4];
            teste[0] = request.getParameter("client_id");
            teste[1] = "JESUS IS THE LORD!";
            teste[2] = "JESUS IS THE LORD!";
            teste[3] = "JESUS IS THE LORD!";
            //additionalInfo.entrySet().add("client_id",  )
            additionalInfo.put("AMOR",teste);*/
            HttpServletRequest requestWrapper = new RequestWrapper(request, additionalInfo);
            System.out.println("--------------------.................>>>>>>>>>>>>>>>>>>>>>>>>>" + request.getParameter("client_id"));
            System.out.println("--------------------.................>>>>>>>>>>>>>>>>>>>>>>>>>" + request.getParameter("username"));


            String url = request.getRequestURL().toString();
            URI uri;
            try {
                uri = new URI(url);
                String domain = uri.getHost();
                if(domain!=null){
                    //TenantContext.setCurrentTenant(domain);
                    System.out.println("CURRENT TENANT>>>>>>>>>>>>>>>>>>>>>>>>>" + domain);
                }
            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            // System.out.println("--------------------.................>>>>>>>>>>>>>>>>>>>>>>>>>" + a++);
            filterChain.doFilter(requestWrapper, response);
        }
        else if (Objects.equals(request.getServletPath(), "/oauth/authorize")) {
            System.out.println("HOPE---------------.................>>>>>>>>>>>>>>>>>>>>>>>>>" + request.getParameter("client_id"));
            System.out.println("HOPE----------------.................>>>>>>>>>>>>>>>>>>>>>>>>>" + request.getParameter("user_nameMy"));
            filterChain.doFilter(request, response);
        }
        else {
            filterChain.doFilter(request, response);
        }
    }

}
