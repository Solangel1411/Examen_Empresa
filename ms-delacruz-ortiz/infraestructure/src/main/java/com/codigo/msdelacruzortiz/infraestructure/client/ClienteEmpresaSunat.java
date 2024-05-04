package com.codigo.msdelacruzortiz.infraestructure.client;

import com.codigo.msdelacruzortiz.domain.aggregates.dto.SunatEmpresaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-empresa-sunat", url = "https://api.apis.net.pe/v2/sunat/")
public interface ClienteEmpresaSunat {

    @GetMapping("/ruc")
    SunatEmpresaDTO getInfoSunat(@RequestParam("numero") String numeroRuc,
                                 @RequestHeader("Authorization") String authorization);

}
