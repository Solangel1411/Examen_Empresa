package com.codigo.msdelacruzortiz.infraestructure.client;

import com.codigo.msdelacruzortiz.domain.aggregates.dto.ReniecPersonaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-persona-sunat", url = "https://api.apis.net.pe/v2/reniec/")
public interface ClientePersonaReniec {

        @GetMapping("/dni")
        ReniecPersonaDTO getInfoReniec(@RequestParam("numero") String dni,
                                       @RequestHeader("Authorization") String authorization);

    }

