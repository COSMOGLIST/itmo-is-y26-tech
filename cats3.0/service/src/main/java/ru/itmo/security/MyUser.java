package ru.itmo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itmo.dto.UserDto;
import ru.itmo.mappers.UserMapper;
import ru.itmo.models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUser implements UserDetails {
    private final UserDto user;

    public MyUser(User user) {
        this.user = UserMapper.asDto(user);
    }
    public MyUser(UserDto user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(user.getRole()));
        return list;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
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

    public int getOwnerId() {
        return user.getOwnerId();
    }

    public String getRole() {
        return user.getRole();
    }
}
