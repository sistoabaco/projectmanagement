package com.webapp.projectmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProject;

    private String projectName;
    private String status;//terminate or active
    private double budget;
    private String localImplementation;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateStart;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateEnd;
    private String typeCurrency;
    private double balanceAvailable;

    @JsonIgnore
    @ManyToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection <Employee> employee;

    @JsonIgnore
    @ManyToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Collection <Partner> partner;

    public Project(int idProject, String projectName, String status, double budget,
                   String localImplementation, Date dateStart, Date dateEnd, String typeCurrency,
                   double balanceAvailable) {
        this.idProject = idProject;
        this.projectName = projectName;
        this.status = status;
        this.budget = budget;
        this.localImplementation = localImplementation;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.typeCurrency = typeCurrency;
        this.balanceAvailable = balanceAvailable;
    }
}
