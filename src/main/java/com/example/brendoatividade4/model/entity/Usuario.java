package com.example.brendoatividade4.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@DiscriminatorValue("Usuario")
public class Usuario implements Serializable, UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Login é obrigatório!")
    private String login;

    @NotBlank(message = "Senha é obrigatória!")
    private String password;

    @ManyToMany
    private List<Role> roles = new ArrayList<>();


    // getters e setters

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String getUsername() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    @Override
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}

    @Override
    public String toString() {
        return "Dados{" +
                "id=" + id +
                "login=" + login +
                "senha=" + password +
                ", roles='" + roles + '\'' +
                '}';
    }

}