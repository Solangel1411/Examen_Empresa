package com.codigo.mssolangel_tisza.domain.port.in;

import com.codigo.mssolangel_tisza.domain.aggregates.dto.EmpresaDTO;
import com.codigo.mssolangel_tisza.domain.aggregates.request.EmpresaRequest;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceIn {
    EmpresaDTO createEmpresaIn(EmpresaRequest empresaRequest);
    Optional<EmpresaDTO> findByIdIn(Long id);
    List<EmpresaDTO> findAllIn();
    EmpresaDTO updateEmpresaIn (Long id, EmpresaRequest empresaRequest);
    EmpresaDTO deleteEmpresaIn (Long id);
}
