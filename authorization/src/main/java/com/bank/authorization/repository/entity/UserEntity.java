package com.bank.authorization.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

// класс описывает характеристики авторизованного пользователя
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users") //schema = "authorization"
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //key

    @NotNull
    @Column(name = "role", nullable = false, length = 40)
    private String role;//varc40

    @Column(name = "profile_id", nullable = false)
    private long profileId;
    @NotNull
    @Column(name = "password", unique = true, nullable = false, length = 500)
    private String password;// unic varc255                     unic nullable

    public UserEntity(long profileId, String password, String role) {
        this.profileId = profileId;
        this.password = password;
        this.role = role;
    }

    public String getAuthority() {
        return getRole();
    }

    public long getProfileId() {
        return profileId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return Long.toString(getProfileId());
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



