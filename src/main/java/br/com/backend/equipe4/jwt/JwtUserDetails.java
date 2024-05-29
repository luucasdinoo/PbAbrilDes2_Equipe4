package br.com.backend.equipe4.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class JwtUserDetails extends User {

    private User user;

    public JwtUserDetails(User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList());
        this.user = user;
    }

//    public Long getId(){
//        return user.getId();
//    }

    public String getUsername() {
        return user.getUsername();
    }
}
