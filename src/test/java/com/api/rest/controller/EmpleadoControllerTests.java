package com.api.rest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.api.rest.empleado.Empleado;
import com.api.rest.empleado.EmpleadoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest // Sirve para poder probar los controladores
public class EmpleadoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // Sirve para agregar objetos simulados al contexto de la aplicacion
    private EmpleadoService empleadoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveEmployee() {
        // given
        Empleado e = Empleado.builder()
                .id(1L)
                .nombre("Juan Pablo")
                .apellido("Mera Agudelo")
                .email("juan@juan.com")
                .build();

        given(empleadoService.saveEmpleado(any(Empleado.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        try {
            // when
            ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/empleados/save/employee")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(e)));

            // then
            response.andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.nombre", is(e.getNombre())))
                    .andExpect(jsonPath("$.apellido", is(e.getApellido())))
                    .andExpect(jsonPath("$.email", is(e.getEmail())));
        } catch (Exception e1) {

        }

    }

}
