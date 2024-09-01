package com.practicajson.json.registro.application;

import com.practicajson.json.registro.domain.service.RegistroService;

public class DeleteRegistroUseCase {
    private RegistroService registroService;

    public DeleteRegistroUseCase(RegistroService registroService) {
        this.registroService = registroService;
    }

    public void execute(String codigo){
        registroService.eliminarRegistro(codigo);
    }

}
