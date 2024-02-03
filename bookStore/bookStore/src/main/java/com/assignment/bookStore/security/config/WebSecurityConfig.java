package com.assignment.bookStore.security.config;

import com.assignment.bookStore.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(
         prePostEnabled = true,
       securedEnabled = true,jsr250Enabled = true
)
public class WebSecurityConfig {


    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http.csrf(AbstractHttpConfigurer::disable).
               authorizeHttpRequests(customizer->{
               customizer.requestMatchers("/api/auth/**","/h2-console/**").permitAll()
               /*.requestMatchers("/api/**").hasAnyAuthority("ADMIN","USER")*/
               .anyRequest().authenticated();})
               .userDetailsService(userDetailsService)
               .sessionManagement(
                session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
               .build();
   }

     @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
     }
      @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
       return configuration.getAuthenticationManager();
      }
      @Bean
    public ModelMapper modelMapper(){
       return new ModelMapper();
      }

}
