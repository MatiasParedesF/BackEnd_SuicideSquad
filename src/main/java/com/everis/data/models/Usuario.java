package com.everis.data.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="curso")
public class Usuario {

	@Id //clave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)//auto incrementar
	private Long id;
	private String nombre;
	private String email;

    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @PrePersist //se ejecuta antes de que sea insertado
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate // se ejecuta antes de que sea actualizado
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

}
