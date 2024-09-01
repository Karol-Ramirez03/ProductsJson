package com.practicajson.json.registro.application;

import java.util.List;

import com.practicajson.json.registro.domain.entity.Registro;
import com.practicajson.json.registro.domain.service.RegistroService;

public class AllRegistroUseCase {
    private RegistroService registroService;

    public AllRegistroUseCase(RegistroService registroService) {
        this.registroService = registroService;
    }

    public List<Registro> execute(){
        return registroService.listarRegistros();
    }
}
