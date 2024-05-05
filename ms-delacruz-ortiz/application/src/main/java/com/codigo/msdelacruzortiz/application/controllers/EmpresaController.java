package com.codigo.msdelacruzortiz.application.controllers;

import com.codigo.msdelacruzortiz.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msdelacruzortiz.domain.aggregates.request.EmpresaRequest;
import com.codigo.msdelacruzortiz.domain.port.in.EmpresaServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms-delacruz-ortiz/v1/empresa")
@AllArgsConstructor

public class EmpresaController {

        private final EmpresaServiceIn empresaServiceIn;

        @PostMapping("/save")
        public ResponseEntity<EmpresaDTO> createPersonaIn(@RequestBody EmpresaRequest empresaRequest){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(empresaServiceIn.createEmpresaIn(empresaRequest));
        }
        @GetMapping("/{id}")
        public ResponseEntity<EmpresaDTO> findByIdIn(@PathVariable Long id){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(empresaServiceIn.findByIdIn(id).get());
        }

        @GetMapping("/todos")
        public ResponseEntity<List<EmpresaDTO>> findAllIn(){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(empresaServiceIn.findAllIn());
        }
        @PutMapping("/{id}")
        public ResponseEntity<EmpresaDTO> updateEmpresaIn(@PathVariable Long id, @RequestBody EmpresaRequest empresaRequest){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(empresaServiceIn.updateEmpresaIn(id,empresaRequest));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<EmpresaDTO> deleteEmpresaIn(@PathVariable Long id){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(empresaServiceIn.deleteEmpresaIn(id));
        }
    }

