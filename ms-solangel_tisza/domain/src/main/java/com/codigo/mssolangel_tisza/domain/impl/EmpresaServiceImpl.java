package com.codigo.mssolangel_tisza.domain.impl;

import com.codigo.mssolangel_tisza.domain.aggregates.dto.EmpresaDTO;
import com.codigo.mssolangel_tisza.domain.aggregates.request.EmpresaRequest;
import com.codigo.mssolangel_tisza.domain.port.in.EmpresaServiceIn;
import com.codigo.mssolangel_tisza.domain.port.out.EmpresaServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class EmpresaServiceImpl implements EmpresaServiceIn {

    private final EmpresaServiceOut empresaServiceOut;

        @Override
        public EmpresaDTO createEmpresaIn(EmpresaRequest empresaRequest) {
            return empresaServiceOut.createEmpresaOut(empresaRequest);
        }

        @Override
        public Optional<EmpresaDTO> findByIdIn(Long id) {
            return empresaServiceOut.findByIdOut(id);
        }

        @Override
        public List<EmpresaDTO> findAllIn() {
            return empresaServiceOut.findAllOut();
        }

        @Override
        public EmpresaDTO updateEmpresaIn(Long id, EmpresaRequest empresaRequest) {
            return empresaServiceOut.updateEmpresaOut(id, empresaRequest);
        }

        @Override
        public EmpresaDTO deleteEmpresaIn(Long id) {
            return empresaServiceOut.deleteEmpresaOut(id);
        }
    }