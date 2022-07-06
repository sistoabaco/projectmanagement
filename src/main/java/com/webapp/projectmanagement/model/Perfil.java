package com.webapp.projectmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;
import java.io.Serializable;

@Entity
public class Perfil implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPerfil;

    private String hobbies;
    private String diseases;
    private String school;
    private File image;
}
