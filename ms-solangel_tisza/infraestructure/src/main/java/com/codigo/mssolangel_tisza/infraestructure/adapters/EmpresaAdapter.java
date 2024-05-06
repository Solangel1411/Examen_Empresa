package com.codigo.mssolangel_tisza.infraestructure.adapters;


import com.codigo.mssolangel_tisza.domain.aggregates.constants.Constant;
import com.codigo.mssolangel_tisza.domain.aggregates.dto.EmpresaDTO;
import com.codigo.mssolangel_tisza.domain.aggregates.dto.SunatEmpresaDTO;
import com.codigo.mssolangel_tisza.domain.aggregates.request.EmpresaRequest;
import com.codigo.mssolangel_tisza.domain.port.out.EmpresaServiceOut;
import com.codigo.mssolangel_tisza.infraestructure.client.ClienteEmpresaSunat;
import com.codigo.mssolangel_tisza.infraestructure.dao.EmpresaRepository;
import com.codigo.mssolangel_tisza.infraestructure.entity.EmpresaEntity;
import com.codigo.mssolangel_tisza.infraestructure.mapper.EmpresaMapper;
import com.codigo.mssolangel_tisza.infraestructure.redis.RedisService;
import com.codigo.mssolangel_tisza.infraestructure.util.EmpresaUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class EmpresaAdapter implements EmpresaServiceOut {

    private EmpresaRepository empresaRepository;
    private ClienteEmpresaSunat clienteSunat;
    private RedisService redisService;
    private ClienteEmpresaSunat clienteEmpresaSunat;

    @Value("${token.api}")
    private String tokenSunat;

    public EmpresaAdapter(EmpresaRepository empresaRepository, RedisService redisService, ClienteEmpresaSunat clienteSunat, ClienteEmpresaSunat clienteEmpresaSunat) {
        this.empresaRepository = empresaRepository;
        this.redisService = redisService;
        this.clienteSunat = clienteSunat;
        this.clienteEmpresaSunat = clienteEmpresaSunat;
    }

    private EmpresaEntity getEntity(EmpresaRequest empresaRequest, boolean actualiza, Long id) {
        // Ejecutar servicio de la SUNAT
        SunatEmpresaDTO empresaDTO = getDatosSunat(empresaRequest.getNumDoc());

        EmpresaEntity entity = new EmpresaEntity();
        entity.setNumeroDocumento(empresaDTO.getNumeroDocumento());
        entity.setRazonSocial(empresaDTO.getRazonSocial());
        entity.setTipoDocumento(empresaDTO.getTipoDocumento());
        entity.setEstado(Constant.STATUS_ACTIVE);
        entity.setCondicion(empresaDTO.getCondicion());
        entity.setDireccion(empresaDTO.getDireccion());
        entity.setProvincia(empresaDTO.getProvincia());
        entity.setDireccion(empresaDTO.getDistrito());
        entity.setDepartamento(empresaDTO.getDepartamento());
        entity.setEsAgenteRetencion(empresaDTO.isEsAgenteRetencion());
        // Datos de auditoria
        if (actualiza) {
            entity.setId(id);
            entity.setUsuaModif(Constant.USU_ADMIN);
            entity.setDateModif(getTimestamp());
        } else {
            entity.setUsuaCrea(Constant.USU_ADMIN);
            entity.setDateCreate(getTimestamp());
        }

        return entity;
    }

    private SunatEmpresaDTO getDatosSunat(String numeroRuc) {
        String authorization = "Bearer " + tokenSunat;
        // Llama al metodo del cliente Feign para obtener los datos de la SUNAT
        return clienteEmpresaSunat.getInfoSunat(numeroRuc, authorization);
    }

    private Timestamp getTimestamp() {
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }

    @Override
    public EmpresaDTO createEmpresaOut(EmpresaRequest empresaRequest) {
        EmpresaEntity empresaEntity = getEntity(empresaRequest, false, null);
        EmpresaEntity empresaSaved = empresaRepository.save(empresaEntity);
        return EmpresaMapper.fromEntity(empresaSaved);
    }

    @Override
    public Optional<EmpresaDTO> findByIdOut(Long id) {
        // consultar redis
        String redisCache = redisService.getFromRedis(Objects.toString(id));
        if (redisCache == null)
        {
            Optional<EmpresaEntity> empresaOptional = empresaRepository.findById(id);
            if(empresaOptional.isPresent())
            {
                EmpresaEntity empresa = empresaOptional.get();
                EmpresaDTO empresaDTO = EmpresaMapper.fromEntity(empresa);
                String jsonEmpresa = EmpresaUtil.convertirAString(empresaDTO);
                redisService.saveInRedis(Objects.toString(id), jsonEmpresa, 10);
                return Optional.of(empresaDTO);
            }
            else {
                return Optional.empty();
            }
        }
        else {
            EmpresaDTO empresaDTO = EmpresaUtil.convertirDesdeString(redisCache, EmpresaDTO.class);
            return Optional.of(empresaDTO);
        }
    }

    @Override
    public List<EmpresaDTO> findAllOut() {
        // Obtener todas las empresas de la base de datos
        List<EmpresaEntity> empresaEntities = empresaRepository.findAll();

        List<EmpresaDTO> empresaDTOs = new ArrayList<>();
        for (EmpresaEntity empresaEntity : empresaEntities) {
            EmpresaDTO empresaDTO = EmpresaMapper.fromEntity(empresaEntity);
            empresaDTOs.add(empresaDTO);
        }
        return empresaDTOs;
    }

    @Override
    public EmpresaDTO updateEmpresaOut(Long id, EmpresaRequest empresaRequest) {
        Optional<EmpresaEntity> empresa = empresaRepository.findById(id);
        if(empresa.isPresent()){
            EmpresaEntity personaEntity = getEntity(empresaRequest,true, id);
            return EmpresaMapper.fromEntity(empresaRepository.save(personaEntity));
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public EmpresaDTO deleteEmpresaOut(Long id) {
        Optional<EmpresaEntity> empresa = empresaRepository.findById(id);

        if (empresa.isPresent()) {
            EmpresaEntity empresaDesactivada = empresa.get();

            empresaDesactivada.setEstado(0);
            empresa.get().setUsuaDelet(Constant.USU_ADMIN);
            empresa.get().setDateDelet(getTimestamp());

            return EmpresaMapper.fromEntity(empresaRepository.save(empresa.get()));
        } else {
            throw new RuntimeException();
        }
    }}
