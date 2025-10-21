package com.genesiscontabil.clientportal.service;

import com.genesiscontabil.clientportal.model.User;
import com.genesiscontabil.clientportal.repository.UserRepository;
import com.genesiscontabil.clientportal.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User user = repository.findByIdentifier(identifier)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado com CPF/CNPJ: " + identifier));

        return new UserPrincipal(user);
    }
}
