package com.codigo.msdelacruzortiz.infraestructure.adapters;


import com.codigo.msdelacruzortiz.domain.aggregates.constants.Constant;
import com.codigo.msdelacruzortiz.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msdelacruzortiz.domain.aggregates.dto.SunatEmpresaDTO;
import com.codigo.msdelacruzortiz.domain.aggregates.request.EmpresaRequest;
import com.codigo.msdelacruzortiz.domain.port.out.EmpresaServiceOut;
import com.codigo.msdelacruzortiz.infraestructure.client.ClienteEmpresaSunat;
import com.codigo.msdelacruzortiz.infraestructure.dao.EmpresaRepository;
import com.codigo.msdelacruzortiz.infraestructure.entity.EmpresaEntity;
import com.codigo.msdelacruzortiz.infraestructure.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class EmpresaAdapter implements EmpresaServiceOut {

    private final EmpresaRepository empresaRepository;
    private final ClienteEmpresaSunat clienteSunat;
    private final RedisService redisService;

    @Value("${token.sunat}")
    private String tokenSunat;

    private EmpresaEntity getEntity(EmpresaRequest empresaRequest, boolean actualiza, Long id) {
        // Ejecutar servicio de la SUNAT
        SunatEmpresaDTO empresaDTO = getDatosSunat(String.valueOf(empresaRequest.getNumDoc()));

        EmpresaEntity entity = new EmpresaEntity();
        entity.setNumeroDocumento(empresaDTO.getNumDoc());
        entity.setRazonSocial(empresaDTO.getRazonSocial());
        entity.setEstado(Constant.STATUS_ACTIVE);

        // Datos de auditoria
        if (actualiza) {
            entity.setId(id);
            entity.setUsuaModif(Constant.USU_ADMIN);
            entity.setDateModif(getTimestamp());
        } else {
            entity.setUsuaCrea(Constant.USU_ADMIN);
            entity.setDateCreate(getTimestamp());
        }

        return entity;
    }

    private SunatEmpresaDTO getDatosSunat(String numeroRuc) {
        String authorization = "Bearer " + tokenSunat;
        // Aquí debes llamar al cliente Feign para obtener los datos de la SUNAT
        // y mapear la respuesta a un objeto EmpresaDTO
        return clienteSunat.getInfoSunat(numeroRuc, authorization);
    }

    private Timestamp getTimestamp() {
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }

    @Override
    public EmpresaDTO createEmpresaOut(EmpresaRequest empresaRequest) {
        return null; // Implementa la lógica para crear una empresa
    }

    @Override
    public Optional<EmpresaDTO> findByIdOut(Long id) {
        return Optional.empty(); // Implementa la lógica para buscar una empresa por ID
    }

    @Override
    public List<EmpresaDTO> findAllOut() {
        return List.of(); // Implementa la lógica para obtener todas las empresas
    }

    @Override
    public EmpresaDTO updateEmpresaOut(Long id, EmpresaRequest empresaRequest) {
        return null; // Implementa la lógica para actualizar una empresa
    }

    @Override
    public EmpresaDTO deleteEmpresaOut(Long id) {
        return null; // Implementa la lógica para eliminar una empresa
    }
}
