package com.codigo.msdelacruzortiz.infraestructure.adapters;

import com.codigo.msdelacruzortiz.domain.aggregates.constants.Constant;
import com.codigo.msdelacruzortiz.domain.aggregates.dto.PersonaDTO;
import com.codigo.msdelacruzortiz.domain.aggregates.dto.ReniecPersonaDTO;
import com.codigo.msdelacruzortiz.domain.aggregates.request.PersonaRequest;
import com.codigo.msdelacruzortiz.domain.port.out.PersonaServiceOut;
import com.codigo.msdelacruzortiz.infraestructure.client.ClientePersonaReniec;
import com.codigo.msdelacruzortiz.infraestructure.dao.EmpresaRepository;
import com.codigo.msdelacruzortiz.infraestructure.dao.PersonaRepository;
import com.codigo.msdelacruzortiz.infraestructure.entity.EmpresaEntity;
import com.codigo.msdelacruzortiz.infraestructure.entity.PersonaEntity;
import com.codigo.msdelacruzortiz.infraestructure.mapper.PersonaMapper;
import com.codigo.msdelacruzortiz.infraestructure.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaServiceOut {

    private final PersonaRepository personaRepository;
    private final ClientePersonaReniec clientePersonaReniec;
    private final RedisService redisService;
    private final EmpresaRepository empresaRepository;

    @Value("${token.api}")
    private String tokenReniec;

    private PersonaEntity getEntity(PersonaRequest personaRequest, boolean actualiza, Long id) {
        ReniecPersonaDTO reniecPersonaDTO = getExecReniec(personaRequest.getNumDoc());
        PersonaEntity entity = new PersonaEntity();
        entity.setNombre(reniecPersonaDTO.getNombres());
        entity.setNumeroDoc(reniecPersonaDTO.getNumeroDocumento());
        entity.setApeMat(reniecPersonaDTO.getApellidoMaterno());
        entity.setApePat(reniecPersonaDTO.getApellidoPaterno());
        entity.setTipoDoc(reniecPersonaDTO.getTipoDocumento());

        Optional<EmpresaEntity> empresaEntity = empresaRepository.findByNumeroDocumento(personaRequest.getEmpresa());

        if (empresaEntity.isPresent()) {
            EmpresaEntity empresa = empresaEntity.get();
            entity.setEmpresa(empresa);

            if (actualiza) {
                entity.setId(id);
                entity.setDateCreate(getTimestamp());
                entity.setUsuaCrea(Constant.USU_ADMIN);
            } else {
                entity.setDateCreate(getTimestamp());
                entity.setUsuaCrea(Constant.USU_ADMIN);
            }
        }
        return entity;
    }

    private ReniecPersonaDTO getExecReniec(String numDoc) {
        String authorization = "Bearer " + tokenReniec;
        return clientePersonaReniec.getInfoReniec(numDoc, authorization);
    }

    private Timestamp getTimestamp() {
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }

    @Override
    public PersonaDTO createPersonaOut(PersonaRequest personaRequest) {
        PersonaEntity personaEntity = getEntity(personaRequest, false, null);
        if(personaEntity.getEmpresa() != null)
        {
            PersonaEntity personaSaved = personaRepository.save(personaEntity);
            return PersonaMapper.fromEntity(personaSaved);
        }
        else {
            return null;
        }
    }

    @Override
    public Optional<PersonaDTO> findByIdOut(Long id) {
       Optional<PersonaEntity> personaOptional = personaRepository.findById(id);
       if (personaOptional.isPresent()) {
           PersonaEntity personaEntity = personaOptional.get();
           PersonaDTO personaDTO = PersonaMapper.fromEntity(personaEntity);
           return Optional.of(personaDTO);
       }
        return Optional.empty();
    }

    @Override
    public List<PersonaDTO> findAllOut() {
        List<PersonaDTO> listaDto = new ArrayList<>();
        List<PersonaEntity> entidades = personaRepository.findAll();
        for (PersonaEntity dato :entidades){
            listaDto.add(PersonaMapper.fromEntity(dato));
        }
        return listaDto;
    }

    @Override
    public PersonaDTO updatePersonaOut(Long id, PersonaRequest personaRequest) {

        Optional<PersonaEntity> empresa = personaRepository.findById(id);
        if(empresa.isPresent()){
            PersonaEntity personaEntity = getEntity(personaRequest,true, id);
            return PersonaMapper.fromEntity(personaRepository.save(personaEntity));
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public PersonaDTO deletePersonaOut(Long id) {
        Optional<PersonaEntity> empresa = personaRepository.findById(id);
        if(empresa.isPresent()){
            empresa.get().setEstado(0);
            empresa.get().setUsuaDelete(Constant.USU_ADMIN);
            empresa.get().setDateDelete(getTimestamp());
            return PersonaMapper.fromEntity(personaRepository.save(empresa.get()));
        }else {
            throw new RuntimeException();
        }
    }
}