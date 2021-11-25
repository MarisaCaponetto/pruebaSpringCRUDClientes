
package com.mc.dao;

import com.mc.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IPersonaDao extends JpaRepository<Persona, Long>{
        
    
}
