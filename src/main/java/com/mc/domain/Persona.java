
package com.mc.domain;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;


//Desde lombok no hace falta agregar getters y setters, constructores, ToString
@Data
@Entity
@Table(name="persona")
public class Persona implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPersona;
    
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    
    private Double saldo;
    
}
