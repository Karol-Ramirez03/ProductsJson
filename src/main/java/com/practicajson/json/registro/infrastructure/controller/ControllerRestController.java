package com.practicajson.json.registro.infrastructure.controller;


import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.practicajson.json.registro.application.AddRegistroUseCase;
import com.practicajson.json.registro.application.AllRegistroUseCase;
import com.practicajson.json.registro.application.DeleteRegistroUseCase;
import com.practicajson.json.registro.domain.entity.Registro;
import com.practicajson.json.registro.domain.service.RegistroService;
import com.practicajson.json.registro.infrastructure.repository.RegistroRepository;

@RestController
public class ControllerRestController {
   private RegistroService registroService;
   private AddRegistroUseCase addRegistroUseCase;
   private DeleteRegistroUseCase deleteRegistroUseCase;
   private AllRegistroUseCase allRegistroUseCase;

    public ControllerRestController() {
        this.registroService = new RegistroRepository();
        this.addRegistroUseCase = new AddRegistroUseCase(registroService);
        this.deleteRegistroUseCase = new DeleteRegistroUseCase(registroService);
        this.allRegistroUseCase = new AllRegistroUseCase(registroService);
    };
   
    
    public void saveRegistro() {
        Registro registro = new Registro("1", "celular", 100,3);
        addRegistroUseCase.execute(registro);
    }
    public void deleteResgistro(String cod) {
        deleteRegistroUseCase.execute(cod);
    }
    public void all(){
        List<Registro> datos = allRegistroUseCase.execute();
        for (Registro registro : datos) {
            
            System.out.println(registro.getCodigo());
            System.out.println(registro.getNombre());
            System.out.println(registro.getPrecio());
            System.out.println(registro.getStock());
            System.out.println("-------------");
        }
    }

}
