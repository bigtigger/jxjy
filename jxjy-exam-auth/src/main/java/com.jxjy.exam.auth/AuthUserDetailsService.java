package com.jxjy.exam.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yusheng
 */
@Component
public class AuthUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = new SysUser(s);
        sysUser.setId(110L);
        sysUser.setPassword(validUser(s));
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("admin");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(grantedAuthority);
        sysUser.setAuthorities(authorities);
        return sysUser;
    }

    public String validUser(String username){
        return "admin";
    }
}
