package com.spring.jwt.springbootjwtreact.customize;

import com.spring.jwt.springbootjwtreact.entity.Users;
import com.spring.jwt.springbootjwtreact.exception.ResourceNotException;
import com.spring.jwt.springbootjwtreact.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {

        Users users = usersRepository.findByEmailOrUsername(usernameOrEmail, usernameOrEmail)
                .orElseThrow(()-> new UsernameNotFoundException("user not found with username or email : "+usernameOrEmail));
        return CustomUserPrincipal.create(users);
    }

    @Transactional
    public UserDetails loadUserByIdusers(String idusers){
        Users users = usersRepository.findById(idusers)
                .orElseThrow(()->new ResourceNotException("user", "idusers", idusers));
        return CustomUserPrincipal.create(users);
    }
}
