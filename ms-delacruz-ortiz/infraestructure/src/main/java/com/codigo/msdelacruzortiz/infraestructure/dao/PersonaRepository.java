package com.codigo.msdelacruzortiz.infraestructure.dao;

import com.codigo.msdelacruzortiz.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
}
