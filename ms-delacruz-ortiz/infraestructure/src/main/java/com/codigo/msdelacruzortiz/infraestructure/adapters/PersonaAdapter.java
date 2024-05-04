package com.codigo.msdelacruzortiz.infraestructure.adapters;

import com.codigo.msdelacruzortiz.domain.aggregates.constants.Constant;
import com.codigo.msdelacruzortiz.domain.aggregates.dto.PersonaDTO;
import com.codigo.msdelacruzortiz.domain.aggregates.dto.SunatEmpresaDTO;
import com.codigo.msdelacruzortiz.domain.aggregates.dto.SunatPersonaDTO;
import com.codigo.msdelacruzortiz.domain.aggregates.request.PersonaRequest;
import com.codigo.msdelacruzortiz.domain.port.out.PersonaServiceOut;
import com.codigo.msdelacruzortiz.infraestructure.client.ClientePersonaSunat;
import com.codigo.msdelacruzortiz.infraestructure.dao.PersonaRepository;
import com.codigo.msdelacruzortiz.infraestructure.entity.PersonaEntity;
import com.codigo.msdelacruzortiz.infraestructure.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaServiceOut {

    private final PersonaRepository personaRepository;
    private final ClientePersonaSunat clientePersonaSunat;
    private final RedisService redisService;
    @Value("${token.reniec}")
    private String tokenReniec;

    private PersonaEntity getEntity(PersonaRequest personaRequest, boolean actualiza, Long id) {
        SunatEmpresaDTO sunatPersonaDTO = getExecReniec(String.valueOf(personaRequest.getNumDoc()));
        PersonaEntity entity = new PersonaEntity();
        entity.setNumeroDoc(sunatPersonaDTO.getNumeroDocumento());
        entity.setNombre(sunatPersonaDTO.getNombres());
        entity.setApeMat(sunatPersonaDTO.getApellidoPaterno());
        entity.setApePat(sunatPersonaDTO.getApellidoMaterno());
        entity.setEstado(Constant.STATUS_ACTIVE);

        // Datos de auditoria donde corresponda

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

    private SunatEmpresaDTO getExecReniec(String numDoc) {
        String authorization = "Bearer " + tokenReniec;
        return clientePersonaSunat.getInfoReniec(numDoc, authorization);
    }

    private Timestamp getTimestamp() {
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }

    @Override
    public PersonaDTO createPersonaOut(PersonaRequest personaRequest) {
        // Validate and sanitize user input data
        // Implement create logic
        return null;
    }

    @Override
    public Optional<PersonaDTO> findByIdOut(Long id) {
        // Implement find by id logic
        return Optional.empty();
    }

    @Override
    public List<PersonaDTO> findAllOut() {
        // Implement find all logic
        return List.of();
    }

    @Override
    public PersonaDTO updatePersonaOut(Long id, PersonaRequest personaRequest) {
        // Validate and sanitize user input data
        // Implement update logic
        return null;
    }

    @Override
    public PersonaDTO deletePersonaOut(Long id) {
        // Implement delete logic
        return null;
    }
}