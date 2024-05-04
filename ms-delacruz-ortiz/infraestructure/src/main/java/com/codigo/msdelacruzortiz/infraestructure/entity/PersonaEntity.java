package com.codigo.msdelacruzortiz.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "persona")
@Getter
@Setter

public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long id;

    @Column(name = "Nombre", nullable = false, length = 225)
    private String nombre;

    @Column(name = "ape_pat", nullable = false, length = 150)
    private String apePat;

    @Column(name = "ape_mat", nullable = false, length = 150)
    private String apeMat;

    @Column(name = "tipoDocumento",nullable = false, length = 5)
    private String tipoDoc;

    @Column(name = "numeroDocumento",unique = false, length = 20)
    private String numeroDoc;

    @Column(name = "email", unique = false, length = 225)
    private String email;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "estado", nullable = false)
    private int estado;

    @Column(name = "usuarioCreate", nullable = false, length = 255)
    private String usuaCrea;

    @Column(name = "dateCreate", nullable = false)
    private Timestamp dateCreate;

    @Column(name = "usuarioModif", length = 225)
    private String usuaModif;

    @Column(name = "dateModif")
    private Timestamp dateModif;

    @Column(name = "usuaDelete", length = 255)
    private String usuaDelete;

    @Column(name = "dateDelete")
    private Timestamp dateDelete;

    @Column(name = "empresa_id", nullable = false)
    private Long empresaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id",nullable = false)
    private EmpresaEntity empresa;
}
