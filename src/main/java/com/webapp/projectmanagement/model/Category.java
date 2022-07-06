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
@Table(name = "categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategory;
    private String description;
    private double  salary;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Collection <Employee> employees;

    public Category(String description, double salary) {
        this.description = description;
        this.salary = salary;
    }
}
