package com.codigo.mssolangel_tisza.domain.port.in;

import com.codigo.mssolangel_tisza.domain.aggregates.dto.PersonaDTO;
import com.codigo.mssolangel_tisza.domain.aggregates.request.PersonaRequest;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceIn {
    PersonaDTO createPersonaIn(PersonaRequest personaRequest);
    Optional<PersonaDTO> findByIdIn(Long id);
    List<PersonaDTO> findAllIn();
    PersonaDTO updatePersonaIn(Long id, PersonaRequest personaRequest);
    PersonaDTO deletePersonaIn(Long id);
}
