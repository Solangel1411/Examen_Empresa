package com.codigo.msdelacruzortiz.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PersonaRequest {
    private String tipoDoc;
    private Long numDoc;
    private String empresa;
}
