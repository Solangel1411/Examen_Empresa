package com.codigo.msdelacruzortiz.infraestructure.dao;

import com.codigo.msdelacruzortiz.infraestructure.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {
}
