package com.spring.jwt.springbootjwtreact.customize;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.jwt.springbootjwtreact.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class CustomUserPrincipal implements UserDetails {

    private String idusers;

    private String name;

    private String username;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserPrincipal(String idusers,
                               String name,
                               String username,
                               String email,
                               String password,
                               Collection<? extends GrantedAuthority> authorities){
        this.idusers = idusers;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static CustomUserPrincipal create(Users users){
        List<GrantedAuthority> authorities = users.getRoles()
                .stream().map(roles -> new SimpleGrantedAuthority(roles.getName()))
                .collect(Collectors.toList());

        return new CustomUserPrincipal(users.getIdusers(),
                users.getName(), users.getUsername(),
                users.getEmail(), users.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     *
     * @dickanirwansyah
     */
    public String getIdusers(){
        return idusers;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
