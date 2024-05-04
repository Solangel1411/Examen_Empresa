package com.codigo.msdelacruzortiz.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SunatPersonaDTO {
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String tipoDocumento;
    private String numeroDocumento;
    private String digitoVerificador;
}