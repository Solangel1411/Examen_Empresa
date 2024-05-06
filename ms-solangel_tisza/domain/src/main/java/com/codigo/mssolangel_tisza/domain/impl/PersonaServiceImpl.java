package com.codigo.mssolangel_tisza.domain.impl;

import com.codigo.mssolangel_tisza.domain.aggregates.dto.PersonaDTO;
import com.codigo.mssolangel_tisza.domain.aggregates.request.PersonaRequest;
import com.codigo.mssolangel_tisza.domain.port.in.PersonaServiceIn;
import com.codigo.mssolangel_tisza.domain.port.out.PersonaServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class PersonaServiceImpl implements PersonaServiceIn {

    private final PersonaServiceOut personaServiceOut;

    @Override
    public PersonaDTO createPersonaIn(PersonaRequest personaRequest) {
        return personaServiceOut.createPersonaOut(personaRequest);
    }

    @Override
    public Optional<PersonaDTO> findByIdIn(Long id) {
        return personaServiceOut.findByIdOut(id);
    }

    @Override
    public List<PersonaDTO> findAllIn() {
        return personaServiceOut.findAllOut();
    }

    @Override
    public PersonaDTO updatePersonaIn(Long id, PersonaRequest personaRequest) {
        return personaServiceOut.updatePersonaOut(id, personaRequest);
    }

    @Override
    public PersonaDTO deletePersonaIn(Long id) {
        return personaServiceOut.deletePersonaOut(id);
    }
}
