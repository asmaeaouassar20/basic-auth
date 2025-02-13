package com.algostyle.appSecurity.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security {


    /**
     *Bean pour encoder les mots de passe avec BCrypt
     * Cela permet de stocker les mots de passe de manière sécurisée
     */
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


    /**
     *Création d'une liste d'utilisateurs en mémoire avec des rôles spécifiques
     * Ici, nous avons un administrateur et 3 utilisateurs classiques
     */
    @Bean
    public InMemoryUserDetailsManager createUsers(){
        UserDetails user1=User.builder()
                .username("asmae")
                .password(encoder().encode("asmae@123"))
                .roles("ADMIN")
                .build();

        UserDetails user2=User.builder()
                .username("achraf")
                .password(encoder().encode("achraf@123"))
                .roles("USER")
                .build();

        UserDetails user3=User.builder()
                .username("siham")
                .password(encoder().encode("siham@123"))
                .roles("USER")
                .build();

        UserDetails user4=User.builder()
                .username("chaimae")
                .password(encoder().encode("chaimae@123"))
                .roles("USER")
                .build();


        // Retourne un gestionnaire d'utilisateurs en mémoire
        return new InMemoryUserDetailsManager(user1,user2,user3,user4);
    }


    /**
     *Configuration des autorisations et de la sécurité HTTP
     * - Seul un utilisateur avecle rôle ADMIN peut accéder à "/api/creator"
     * - Toutes les autres requêtes nécessitent une authentification
     * - Utilisation de l'authentification HTTP basic
     */
    @Bean
    public SecurityFilterChain authorize(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeRequests(auth->
                auth.requestMatchers("/api/creator").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic().and()
                .build();
    }
}
