package com.practicajson.json.registro.infrastructure.repository;

import java.io.IOException;
import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.practicajson.json.registro.domain.entity.Registro;
import com.practicajson.json.registro.domain.service.RegistroService;

public class RegistroRepository implements RegistroService {

    @Override
    public void saveRegistro(Registro registro) {
        // Convertir el código del registro en una cadena para usarla como clave
        String id =String.valueOf(registro.getCodigo());
        registro.getNombre();
        registro.getPrecio();
        registro.getStock();
        // define el archivo para guardar el JSON
        File file = new File("registro.json");
        // Crear una instancia de ObjectMapper para manejar el JSON
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode rootNode;

        // Leer el archivo JSON existente si existe
        if (file.exists()) {
            try {
                // Leer el contenido del archivo JSON en un ObjectNode
                rootNode = (ObjectNode) objectMapper.readTree(file);
            } catch (IOException e) {
                e.printStackTrace();
                rootNode = objectMapper.createObjectNode();
            }
        } else {
            // Crear un nuevo ObjectNode si el archivo no existe
            rootNode = objectMapper.createObjectNode();
        }

        // Convertir el objeto registro a un ObjectNode
        ObjectNode registroNode = objectMapper.valueToTree(registro);

        // Añadir el registro al ObjectNode con el código como clave
        rootNode.set(id, registroNode);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, rootNode);
            System.out.println("registro guardado en registros.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
