package com.codigo.msdelacruzortiz.infraestructure.util;

import com.codigo.msdelacruzortiz.domain.aggregates.dto.PersonaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PersonaUtil {
    public static String convertirAString(PersonaDTO personaDto){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(personaDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convertirDesdeString( String json, Class<T> tipoClase){
        try {
            ObjectMapper objectMapper= new ObjectMapper();
            return objectMapper.readValue(json,tipoClase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
