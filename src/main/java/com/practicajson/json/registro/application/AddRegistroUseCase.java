package com.practicajson.json.registro.application;

import com.practicajson.json.registro.domain.entity.Registro;
import com.practicajson.json.registro.domain.service.RegistroService;

public class AddRegistroUseCase {
    private RegistroService registroService;

    public AddRegistroUseCase(RegistroService registroService) {
        this.registroService = registroService;
    }

    public void execute(Registro registro){
        registroService.saveRegistro(registro);
    }
}
