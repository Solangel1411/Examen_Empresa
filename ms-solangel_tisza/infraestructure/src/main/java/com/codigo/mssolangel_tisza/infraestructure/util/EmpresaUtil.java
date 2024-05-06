package com.codigo.mssolangel_tisza.infraestructure.util;

import com.codigo.mssolangel_tisza.domain.aggregates.dto.EmpresaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmpresaUtil {
        public static String convertirAString(EmpresaDTO empresaDto){
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.writeValueAsString(empresaDto);
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

