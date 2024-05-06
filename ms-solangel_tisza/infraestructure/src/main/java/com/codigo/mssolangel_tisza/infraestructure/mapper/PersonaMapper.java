package com.codigo.mssolangel_tisza.infraestructure.mapper;

import com.codigo.mssolangel_tisza.domain.aggregates.dto.PersonaDTO;
import com.codigo.mssolangel_tisza.infraestructure.entity.PersonaEntity;
import org.springframework.stereotype.Service;

@Service

public class PersonaMapper {
    public static PersonaDTO fromEntity(PersonaEntity entity) {
        PersonaDTO dto = new PersonaDTO();

        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApeMat());
        dto.setTipoDoc(entity.getTipoDoc());
        dto.setNumDoc(entity.getNumeroDoc());
        dto.setEmail(entity.getEmail());
        dto.setTelefono(entity.getTelefono());
        dto.setDireccion(entity.getDireccion());
        dto.setEstado(entity.getEstado());
        dto.setUsuaCrea(entity.getUsuaCrea());
        dto.setDateCreate(entity.getDateCreate());
        dto.setUsuaModif(entity.getUsuaModif());
        dto.setDateModif(entity.getDateModif());
        dto.setUsuaDelet(entity.getUsuaDelete());
        dto.setDateDelet(entity.getDateDelete());

        return dto;
    }
}
