package com.codigo.msdelacruzortiz.domain.port.in;

import com.codigo.msdelacruzortiz.domain.aggregates.dto.PersonaDTO;
import com.codigo.msdelacruzortiz.domain.aggregates.request.PersonaRequest;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceIn {
    PersonaDTO createPersonaIn(PersonaRequest personaRequest);
    Optional<PersonaDTO> findByIdIn(Long id);
    List<PersonaDTO> findAllIn();
    PersonaDTO updatePersonaIn(Long id, PersonaRequest personaRequest);
    PersonaDTO deletePersonaIn(Long id);
}
