package cm.rh.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Para crear la tabla auto en la db
@Entity
// Simplifica los metodos GET y SET
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Empleado {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer idEmpleado;
  String nombre;
  String departamento;
  Double sueldo;
}
