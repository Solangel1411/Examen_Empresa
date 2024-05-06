package com.codigo.mssolangel_tisza.infraestructure.dao;

import com.codigo.mssolangel_tisza.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
}
