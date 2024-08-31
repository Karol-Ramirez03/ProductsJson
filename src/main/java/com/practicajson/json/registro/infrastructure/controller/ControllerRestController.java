package com.practicajson.json.registro.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practicajson.json.registro.application.AddRegistroUseCase;
import com.practicajson.json.registro.domain.entity.Registro;
import com.practicajson.json.registro.domain.service.RegistroService;
import com.practicajson.json.registro.infrastructure.repository.RegistroRepository;

@RestController
public class ControllerRestController {
   private RegistroService registroService;
   private AddRegistroUseCase addRegistroUseCase;

    public ControllerRestController() {
        this.registroService = new RegistroRepository();
        this.addRegistroUseCase = new AddRegistroUseCase(registroService);
    };
   
    
    public void saveRegistro() {
        Registro registro = new Registro("143", "shampoo", 1000, 13);
        addRegistroUseCase.execute(registro);
    }

}
