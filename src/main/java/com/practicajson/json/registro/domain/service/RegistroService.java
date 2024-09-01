package com.practicajson.json.registro.domain.service;

import java.util.List;

import com.practicajson.json.registro.domain.entity.Registro;

public interface RegistroService {

    public void saveRegistro(Registro registro);
    public void eliminarRegistro(String codigo);
    public List<Registro> listarRegistros();
}
