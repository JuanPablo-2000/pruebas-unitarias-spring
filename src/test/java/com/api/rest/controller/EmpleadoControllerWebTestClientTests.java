package com.api.rest.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.api.rest.empleado.Empleado;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmpleadoControllerWebTestClientTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Order(1)
    public void testSaveEmployee() {
        // given
        Empleado empleado = Empleado.builder()
            .id(3l)
            .nombre("Adrian")
            .apellido("Ramirez")
            .email("aab@gmail.com")
            .build();
        
        // when
        webTestClient.post().uri("http://localhost:8080/api/empleados/save/employee")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(empleado)
            .exchange() // envia el request

        // then
            .expectStatus().isCreated()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isEqualTo(empleado.getId())
            .jsonPath("$.nombre").isEqualTo(empleado.getNombre())
            .jsonPath("$.apellido").isEqualTo(empleado.getApellido())
            .jsonPath("$.email").isEqualTo(empleado.getEmail());

    }

    @Test
    @Order(2)
    public void testGetEmployeeById() {

    }

    @Test
    @Order(3)
    public void testListEmployee() {

    }

    @Test
    @Order(4)
    public void testUpdatedEmployee() {

    }

    @Test
    @Order(5)
    public void testDeletedEmployee() {
        
    }
}
