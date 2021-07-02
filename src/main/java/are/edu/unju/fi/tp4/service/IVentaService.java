package are.edu.unju.fi.tp4.service;

import java.util.List;

import are.edu.unju.fi.tp4.model.Venta;

public interface IVentaService {
	public void guardarVenta(Venta unaVenta);
	public Venta crearVenta();
	public List<Venta> obtenerTodasVentas();
	public Venta encontrarUnaVenta(int id) throws Exception;
}
