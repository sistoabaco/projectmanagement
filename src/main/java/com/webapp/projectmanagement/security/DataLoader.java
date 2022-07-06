package com.webapp.projectmanagement.security;

import com.webapp.projectmanagement.model.*;
import com.webapp.projectmanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    ProjectRepo projectRepo;
    @Autowired
    PartnerRepo partnerRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String...args) throws Exception {

        //Privilegios
        roleRepo.save(new Role(1,"P_ADMIN"));
        roleRepo.save(new Role(2,"P_USER"));
        roleRepo.save(new Role(3, "P_PARTNER"));
        roleRepo.save(new Role(4, "P_TEAM_LEADER"));
        roleRepo.save(new Role(14, "P_COORDENADOR"));
        roleRepo.save(new Role(5, "P_OFICIAL_DE_MEAL"));
        roleRepo.save(new Role(6, "P_GESTOR_DE_PROJECTOS"));
        roleRepo.save(new Role(7, "P_DIRECTOR"));
        roleRepo.save(new Role(8,"P_GESTOR FINANCEIRO"));
        roleRepo.save(new Role(9, "P_CONTABILISTA"));
        roleRepo.save(new Role(10, "P_LOGISTICA"));
        roleRepo.save(new Role(11, "P_SECRETARIA"));
        roleRepo.save(new Role(12, "P_FIEL DE ARMAZEM"));
        roleRepo.save(new Role(13, "P_TECNICOS DO CAMPO"));

        // Utilizadores
        userRepo.save(new User(1,"admin",passwordEncoder.encode("zxcvb"),
                Arrays.asList(roleRepo.findByRole("P_ADMIN"),
                        roleRepo.findByRole("P_USER"),
                        roleRepo.findByRole("P_PARTNER"))));

        userRepo.save(new User(2,"user",passwordEncoder.encode("zxcvb"),
                Arrays.asList(roleRepo.findByRole("P_USER"))));
        userRepo.save(new User(3,"partner",passwordEncoder.encode("zxcvb"),
                Arrays.asList(roleRepo.findByRole("P_PARTNER"))));

        //projecto
        projectRepo.save(new Project(1,"BHA", "Activo", 12000000.0,"Chiure",
                Date.valueOf("2020-08-01"), Date.valueOf( "2022-10-01"), "USD", 8276626.0));
        projectRepo.save(new Project(2,"Hungria", "Terminado", 8000000.0,"Mecufi",
                Date.valueOf("2020-08-01"), Date.valueOf( "2022-04-01"), "MZN", 100000.0));

        //Categorias

        categoryRepo.save (new Category("ADMIN", 0));
        categoryRepo.save (new Category("USER", 0));
        categoryRepo.save(new Category("COORDENADOR", 80000));
        categoryRepo.save(new Category("OFICIAL DE MEAL", 30000));
        categoryRepo.save(new Category("GESTOR FINANCEIRO", 135000));
        categoryRepo.save(new Category("GESTOR DE PROGRAMAS", 150000));
        categoryRepo.save(new Category("DIRECTOR", 200000));

        //Funcionario
        employeeRepo.save(new Employee(1, "Sisto","Abaco","Male",
                "Eduardo Mondlane", "+258 847 264 343", "Pemba",
                Date.valueOf("1995-06-06"), Date.valueOf("2050-01-02"),  Date.valueOf("2050-04-09"),
                userRepo.findByUsername("admin"), categoryRepo.findByName("ADMIN"),
                Arrays.asList(projectRepo.findByName("BHA"))));

        employeeRepo.save(new Employee(2, "cassimo","duarte","Male",
                "Alto-Jingone", "+258 877 285 434", "Pemba",  Date.valueOf("1994-08-09"),
                Date.valueOf("2050-01-02"), Date.valueOf("2050-04-09"),
                userRepo.findByUsername("user"),categoryRepo.findByName("USER"),
                Arrays.asList(projectRepo.findByName("Hungria"))));

        //Parceiro
        partnerRepo.save(new Partner(1, "USAID","123212343","Av. Marinal"
                ,"usaid@gmail.com",Arrays.asList(projectRepo.findByName("BHA"),
                projectRepo.findByName("Hungria")), userRepo.findByUsername("partner")));
    }
}
