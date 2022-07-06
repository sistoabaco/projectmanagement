package com.webapp.projectmanagement.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "idUser", referencedColumnName = "idUser"),
            inverseJoinColumns = @JoinColumn(
                    name = "idRole", referencedColumnName = "idRole"))
    private Collection <Role> role;
}
