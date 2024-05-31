package br.com.backend.equipe4.jwt;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.UUID;


public class JwtUserDetails extends User {

    private br.com.backend.equipe4.entity.User user;

    public JwtUserDetails(br.com.backend.equipe4.entity.User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }

   public UUID getId(){
       return this.user.getId();
    }

    public String getUsername() {
        return this.user.getUsername();
    }

    public String getRole(){
        return this.user.getRole().name();
    }
}
