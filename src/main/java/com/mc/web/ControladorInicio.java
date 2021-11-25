
package com.mc.web;


import com.mc.domain.Persona;
import com.mc.servicio.PersonaService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller //Controlador de tipo MVC
@Slf4j //Enviar informacion al log con la libreria lombok
public class ControladorInicio {
    //Inyeccion de la interface de personaDao en el controlador Inicio
    @Autowired
    private PersonaService personaService;
    
    
    //Indicarle al navegador que este metodo es el que se debe ejecutar
    @GetMapping("/") 
    public String inicio(Model model, @AuthenticationPrincipal User user){ //En lugar de recibir el objeto Request
        List <Persona> personas = (List <Persona>) personaService.listarPersonas();
                      
        log.info("Ejecutando el controlador de tipo Spring MVC");
        log.info("usuario que hizo login: " + user);
        model.addAttribute("personas", personas);
        //Agregando el saldo total
        Double saldoTotal = 0D;
        for(Persona p: personas){
            saldoTotal += p.getSaldo();
        }
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());
        return "index"; //Nombre de la pagina que va a desplegar
    }
    
    @GetMapping("/agregar")
    public String agregar(Persona persona){
        return "modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(Persona persona){
        personaService.guardar(persona);
        return "redirect:/";
    }
    
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
        
        
    }
}
