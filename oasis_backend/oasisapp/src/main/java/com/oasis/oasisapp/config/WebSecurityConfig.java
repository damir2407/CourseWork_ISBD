package com.oasis.oasisapp.config;

import com.oasis.oasisapp.config.jwt.AuthEntryPointJwt;
import com.oasis.oasisapp.config.jwt.AuthTokenFilter;
import com.oasis.oasisapp.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    private AuthTokenFilter authenticationJwtTokenFilter;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/oasis/auth/**").permitAll()
                .antMatchers("/oasis/user/get_login").hasAnyRole("USER", "DEV")
                .antMatchers("/oasis/user/check-user-on-exist").permitAll()
                .antMatchers("/oasis/user/get_status").permitAll()
                .antMatchers("/oasis/user/get_last_login_date").permitAll()
                .antMatchers("/oasis/user/get_registration_date").permitAll()
                .antMatchers("/oasis/user/get_status_and_dates").permitAll()
                .antMatchers("/oasis/user/logout_user").hasAnyRole("USER", "DEV")
                .antMatchers("/oasis/dev").hasRole("DEV")
                .antMatchers("/oasis/shop/get_all_games").permitAll()
                .antMatchers("/oasis/shop/get_by_genre").permitAll()
                .antMatchers("/oasis/shop/get_by_game_name").permitAll()
                .antMatchers("/oasis/shop/get_by_game_name_and_genres").permitAll()
                .antMatchers("/oasis/game/get_game_info").permitAll()
                .antMatchers("/oasis/game/check-game-on-exist").permitAll()
                .antMatchers("/oasis/user/add_money").hasRole("USER")
                .antMatchers("/oasis/user/get_balance").hasRole("USER")
                .antMatchers("/oasis/game/buy_game").hasRole("USER")
                .antMatchers("/oasis/library/get_by_game_name_library").hasRole("USER")
                .antMatchers("/oasis/library/enter_game").hasRole("USER")
                .antMatchers("/oasis/inventory/get_all_items").hasRole("USER")
                .antMatchers("/oasis/library/get_all_games_count").permitAll()
                .antMatchers("/oasis/library/get_last_games").permitAll()
                .antMatchers("/oasis/user_activity/activity_submit").hasRole("USER")
                .antMatchers("/oasis/user_activity/get_all_activities").permitAll()
                .antMatchers("/oasis/guide/add_guide").hasRole("USER")
                .antMatchers("/oasis/guide/get_guides_by_selected_game").permitAll()
                .antMatchers("/oasis/oasis/market/sell_item").hasRole("USER")
                .antMatchers("/oasis/game/get_all_games").permitAll()
                .antMatchers("/oasis/game/get_all_games_by_login").hasRole("USER")
                .antMatchers("/oasis/game/get_all_games_for_market").permitAll()
                .antMatchers("/oasis/market/get_all_slots").permitAll()
                .antMatchers("/oasis/market/buy_item").hasRole("USER")


                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
