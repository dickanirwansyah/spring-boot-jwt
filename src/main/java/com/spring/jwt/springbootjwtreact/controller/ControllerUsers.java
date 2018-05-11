package com.spring.jwt.springbootjwtreact.controller;

import com.spring.jwt.springbootjwtreact.customize.CustomJwtProvider;
import com.spring.jwt.springbootjwtreact.entity.Roles;
import com.spring.jwt.springbootjwtreact.entity.Users;
import com.spring.jwt.springbootjwtreact.exception.InternalServerErrorException;
import com.spring.jwt.springbootjwtreact.repository.RolesRepository;
import com.spring.jwt.springbootjwtreact.repository.UsersRepository;
import com.spring.jwt.springbootjwtreact.request.LoginRequest;
import com.spring.jwt.springbootjwtreact.request.SignUpRequest;
import com.spring.jwt.springbootjwtreact.request.response.ApiResponse;
import com.spring.jwt.springbootjwtreact.request.response.JwtAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping(value = "/api/auth")
public class ControllerUsers {

    @Autowired private AuthenticationManager authenticationManager;

    @Autowired private UsersRepository usersRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private RolesRepository rolesRepository;

    @Autowired private CustomJwtProvider tokenProvider;

    @PostMapping(value = "/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsernameOrEmail(), request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String generateJwtToken = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(generateJwtToken));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequest request){

        if(usersRepository.existsByUsername(request.getUsername())){
            return new ResponseEntity(new ApiResponse(false,
                    "username is already taken"),HttpStatus.CONFLICT);
        }

        if(usersRepository.existsByEmail(request.getEmail())){
            return new ResponseEntity(new ApiResponse(false,
                    "email is already taken"), HttpStatus.CONFLICT);
        }

        Users users = new Users(
            request.getName(),request.getUsername(), request.getPassword(),
                request.getEmail()
        );

        users.setPassword(passwordEncoder.encode(users.getPassword()));

        Roles roles = rolesRepository.findById("r001")
                .orElseThrow(() ->
                new InternalServerErrorException("user role gagal"));

        users.setRoles(Collections.singleton(roles));
        Users resultPath = usersRepository.save(users);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/users/{username}")
                .buildAndExpand(resultPath.getUsername())
                .toUri();

        return ResponseEntity.created(location).body(
                new ApiResponse(true, "User signup successfully")
        );
    }
}
