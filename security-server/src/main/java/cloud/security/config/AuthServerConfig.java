package cloud.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	
    @Autowired
    @Qualifier("authenticationManagerBean")
    AuthenticationManager authenticationManager;
    
    @Autowired 
    TokenStore tokenStore;    
    
    @Autowired
    TokenEnhancer tokenEnhancer;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			// Resource Owner Password
			.withClient("client-password")
				.secret("secret")
				.authorizedGrantTypes("password")
				.scopes("oauth2")
				.autoApprove(true).and()
			// Client Credentials
			.withClient("client-credentials")
	           .secret("secret")
	           .authorizedGrantTypes("client_credentials")
	           .scopes("oauth2")
	           .autoApprove(true).and()
	        // Authorization Code
           .withClient("client-auth-code")
	           .secret("secret")
	           .authorizedGrantTypes("authorization_code")
	           .scopes("oauth2")
	           .autoApprove(true).and()
	        // Implicit
           .withClient("client-implicit")
	           .secret("secret")
	           .authorizedGrantTypes("implicit")
	           .scopes("oauth2")
	           .autoApprove(true);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore)
        	.tokenEnhancer(tokenEnhancer)
        	.authenticationManager(authenticationManager);
	}
}