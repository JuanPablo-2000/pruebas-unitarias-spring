package com.api.rest.empleado;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/save/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado saveEmployee(@RequestBody Empleado empleado) {
        return empleadoService.saveEmpleado(empleado);
    }

    @GetMapping("/get/all/employee")
    public List<Empleado> getAllEmployee() {
        return empleadoService.getAllEmpleados();
    }

    @GetMapping("/get/employee/by/{id}")
    public Optional<Empleado> getByIdEmployee(@PathVariable("id") long id) {
        return empleadoService.getEmpleadoById(id);
    }

    @PutMapping("/update/employee")
    public Empleado updateEmployee(@RequestBody Empleado e) {
        return empleadoService.updateEmpleado(e);
    }

    @DeleteMapping("/delete/employee")
    public void deleteEmployee() {
        
    }
}
