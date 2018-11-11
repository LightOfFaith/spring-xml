
# Java Configuration配置Web Security

1.  创建WebSecurityConfig
  
example：
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 @Bean
 public UserDetailsService userDetailsService() throws Exception {
  InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
  manager.createUser(User.withUsername("user").password("password").roles("USER").build());
  return manager;
 }
}

------------------------------------------------------------


2.  创建SecurityWebApplicationInitializer


AbstractSecurityWebApplicationInitializer without Existing Spring
If you are not using Spring or Spring MVC, you will need to pass in the WebSecurityConfig into the
superclass to ensure the configuration is picked up.
example：
import org.springframework.security.web.context.*;
public class SecurityWebApplicationInitializer
 extends AbstractSecurityWebApplicationInitializer {
 public SecurityWebApplicationInitializer() {
  super(WebSecurityConfig.class);
 }
}
  
AbstractSecurityWebApplicationInitializer with Spring MVC
If  we  were  using  Spring  elsewhere  in  our  application  we  probably  already  had  a
WebApplicationInitializer  that  is  loading  our  Spring  Configuration.  If  we  use  the
previous  configuration  we  would  get  an  error.  Instead,  we  should  register  Spring  Security
with  the  existing  ApplicationContext.  For  example,  if  we  were  using  Spring  MVC  our
SecurityWebApplicationInitializer would look something like the following:

import org.springframework.security.web.context.*;
public class SecurityWebApplicationInitializer
 extends AbstractSecurityWebApplicationInitializer {
}



