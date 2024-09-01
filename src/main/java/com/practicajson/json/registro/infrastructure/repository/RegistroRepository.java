package com.practicajson.json.registro.infrastructure.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.practicajson.json.registro.domain.entity.Registro;
import com.practicajson.json.registro.domain.service.RegistroService;

public class RegistroRepository implements RegistroService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    //proporciona funcionalidad para leer y escribir JSON,
    private final File file = new File("registro.json");
    //el nombre de la carpeta y busca 


    //metodo para leer el archivo json

    private ObjectNode leerDatos() {
        // esta variable busca almacenar el contenido del json
        ObjectNode rootNode;

        if (file.exists()) {
            //si el archivo existe 
            try {
                rootNode = (ObjectNode) objectMapper.readTree(file);
                /*
                 * intenta leer el archivo json y convierte el contenido en un jsonNode
                 * que es una estructura que representa json
                 * y se convierte el jsonNode a un ObjectNode que es una subclase 
                 * que permite manipular objectos json
                */
            } catch (IOException e) {
                e.printStackTrace();
                rootNode = objectMapper.createObjectNode();
                //se crea un nuevo ObjectNode vacio
            }
            
        } else {
            rootNode = objectMapper.createObjectNode();
        }
        return rootNode;
        //devuelve un ObjectNode

    }

    private void guardarDatos(ObjectNode rootNode){
        try {           
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, rootNode);
            /*
             * el objectMapper convierte el objectNode a formato json
             * writerWithDefaultPrettyPrinter
             * escritor de JSON que formatea el JSON de manera legible (con sangrías y saltos de línea)
             * writeValue
             * Escribe el contenido de rootNode en el archivo file
            */
            System.out.println("guardado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void saveRegistro(Registro registro) {
        String id = String.valueOf(registro.getCodigo());
        ObjectNode rootNode = leerDatos();
        // se obtiene los datos del json y como se sabe me retorna un object
        ObjectNode registroNode = objectMapper.valueToTree(registro);
        //creo otro object del registro
        rootNode.set(id, registroNode);
        //lo agrego al primer object el registro 
        // y guardo datos
        guardarDatos(rootNode);
    }

    public void eliminarRegistro(String codigo) {
        String id = String.valueOf(codigo);
        ObjectNode rootNode = leerDatos();

        if (rootNode.has(id)) {
            /*
             * rootNode.has(id)):
             * verifica si hay una clave que tiene el misma proporcionado
            */
            rootNode.remove(id);
            //elimina el dato con la clave correspondiente
            guardarDatos(rootNode);
            System.out.println("Registro eliminado de registro.json");
        } else {
            System.out.println("Registro no encontrado.");
        }
    }


    public List<Registro> listarRegistros() {
        ObjectNode rootNode = leerDatos();
        List<Registro> listResgistro = new ArrayList<>();
        if (rootNode.size() > 0) {
            rootNode.fieldNames().forEachRemaining(id -> {
                ObjectNode registroNode = (ObjectNode) rootNode.get(id);
                String nombre = registroNode.get("nombre").asText();
                double precio = registroNode.get("precio").asDouble();
                int stock = registroNode.get("stock").asInt();

                Registro registro = new Registro(id, nombre, precio, stock);
                listResgistro.add(registro);
            });
        } else {
            System.out.println("No hay registros para mostrar.");
        }
        return listResgistro;
    }


}
