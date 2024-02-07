package cm.rh.controlador;

import cm.rh.excepcion.RecursoNoEncontradoExcepcion;
import cm.rh.modelo.Empleado;
import cm.rh.servicio.EmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// http://localhost:8080/rh-app
@RequestMapping("rh-app")
// PARA RECIBIR PETICIONES DESDE OTRO PUERTO, EN ESTE CASO REACT
@CrossOrigin(value = "http://127.0.0.1:5173")
public class EmpleadoControlador {
  // PARA PODER ENVIAR INFORMACION A CONSOLA
  private static final Logger logger = LoggerFactory.getLogger(EmpleadoControlador.class);

  @Autowired
  private EmpleadoServicio empleadoServicio;

  @GetMapping("/empleados")
  // http://localhost:8080/rh-app/empleados
  public List<Empleado> obtenerEmpleados() {
    var empleados = empleadoServicio.listarEmpleados();
    empleados.forEach(empleado -> logger.info(empleado.toString()));

    return empleados;
  }

  @PostMapping("/empleados")
  public Empleado agregarEmpleado(@RequestBody Empleado empleado) {
    logger.info("AGREGAR EMPLEADO:" + empleado);
    return empleadoServicio.guardaEmpleado(empleado);
  }

  @GetMapping("/empleados/{id}")
  public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Integer id) {
    Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
    if (empleado == null) {
      throw new RecursoNoEncontradoExcepcion("No se encontro el empleado: " + id);
    } else {
      return ResponseEntity.ok(empleado);
    }
  }

  @PutMapping("/empleados/{id}")
  public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleadoRecibido) {
    Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
    if (empleado == null) {
      throw new RecursoNoEncontradoExcepcion("El id no existe");
    } else {
      empleado.setNombre(empleadoRecibido.getNombre());
      empleado.setDepartamento(empleadoRecibido.getDepartamento());
      empleado.setSueldo(empleadoRecibido.getSueldo());

      empleadoServicio.guardaEmpleado(empleado);
      return ResponseEntity.ok(empleado);
    }
  }
}
