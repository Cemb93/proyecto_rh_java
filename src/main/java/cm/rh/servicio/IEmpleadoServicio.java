package cm.rh.servicio;

import cm.rh.modelo.Empleado;

import java.util.List;

public interface IEmpleadoServicio {
  public List<Empleado> listarEmpleados();
  public Empleado buscarEmpleadoPorId(Integer idEmpleado);
  public Empleado guardaEmpleado(Empleado empleado);
  public void eliminarEmpleado(Empleado empleado);
}
