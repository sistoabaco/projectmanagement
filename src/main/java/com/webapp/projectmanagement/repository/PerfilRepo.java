package com.webapp.projectmanagement.repository;

import com.webapp.projectmanagement.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepo extends JpaRepository<Perfil, Integer> {
}
