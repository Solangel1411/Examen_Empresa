package com.codigo.mssolangel_tisza.domain.port.out;

import com.codigo.mssolangel_tisza.domain.aggregates.dto.EmpresaDTO;
import com.codigo.mssolangel_tisza.domain.aggregates.request.EmpresaRequest;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceOut {

    EmpresaDTO createEmpresaOut(EmpresaRequest empresaRequest);
    Optional<EmpresaDTO> findByIdOut(Long id);
    List<EmpresaDTO> findAllOut();
    EmpresaDTO updateEmpresaOut (Long id, EmpresaRequest empresaRequest);
    EmpresaDTO deleteEmpresaOut (Long id);
}
