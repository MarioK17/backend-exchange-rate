package com.mario.backendbasicbcp.service;

import com.mario.backendbasicbcp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import reactor.core.publisher.Mono;

public interface UserService extends UserDetailsService  {
    Mono<User> findByUsername(String username);
}
