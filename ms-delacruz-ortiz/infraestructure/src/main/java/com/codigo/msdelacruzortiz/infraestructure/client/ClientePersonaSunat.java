package com.codigo.msdelacruzortiz.infraestructure.client;

import com.codigo.msdelacruzortiz.domain.aggregates.dto.SunatEmpresaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-persona-sunat", url = "https://api.apis.net.pe/v2/reniec/")
public interface ClientePersonaSunat {

        @GetMapping("/dni")
        SunatEmpresaDTO getInfoReniec(@RequestParam("dni") String dni,
                                      @RequestHeader("Authorization") String authorization);

    }

