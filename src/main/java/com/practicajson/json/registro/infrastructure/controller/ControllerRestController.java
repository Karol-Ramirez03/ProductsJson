package com.practicajson.json.registro.infrastructure.controller;


import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import com.practicajson.json.registro.application.AddRegistroUseCase;
import com.practicajson.json.registro.application.AllRegistroUseCase;
import com.practicajson.json.registro.application.DeleteRegistroUseCase;
import com.practicajson.json.registro.domain.entity.Registro;
import com.practicajson.json.registro.domain.service.RegistroService;
import com.practicajson.json.registro.infrastructure.repository.RegistroRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;





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
   

    @PostMapping("/agregar")
    public void creaRegistro(@RequestBody Registro registro) {
        addRegistroUseCase.execute(registro);
    }

    @GetMapping("/data")
    public List<Registro> visualizarRegistro(){
        return allRegistroUseCase.execute();
    }

    @DeleteMapping("/eliminar")
    public void eliminar(@RequestBody Registro registro){
        String codigo = registro.getCodigo();
        deleteRegistroUseCase.execute(codigo);
    }
    
}
