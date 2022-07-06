package com.webapp.projectmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Partner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPartner;

    private String fullName;
    //    @Size (min = 10)
    @Column(unique = true)
    private String nuit;
    private String address;
    //    @Size (min = 5)
    @Column(unique = true)
    private String email;

    @ManyToMany
    @JoinTable(
            name = "partner_project",
            joinColumns = @JoinColumn(
                    name = "idPartner", referencedColumnName = "idPartner"),
            inverseJoinColumns = @JoinColumn(
                    name = "idProject", referencedColumnName = "idProject"))
    private Collection <Project> project;

    @JsonIgnore
    @OneToOne (cascade = CascadeType.ALL)
    private User user;
}
