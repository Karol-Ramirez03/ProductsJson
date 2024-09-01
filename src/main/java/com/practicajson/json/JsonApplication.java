package com.practicajson.json;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.practicajson.json.registro.infrastructure.controller.ControllerRestController;

@SpringBootApplication
public class JsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonApplication.class, args);
		ControllerRestController ejecutar = new ControllerRestController();
		ejecutar.all();
	}


}
