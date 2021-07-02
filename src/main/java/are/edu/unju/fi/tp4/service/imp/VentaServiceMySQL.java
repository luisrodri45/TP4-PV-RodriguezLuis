package are.edu.unju.fi.tp4.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import are.edu.unju.fi.tp4.model.Venta;
import are.edu.unju.fi.tp4.repository.IventaDAO;
import are.edu.unju.fi.tp4.service.IVentaService;
@Service
public class VentaServiceMySQL implements IVentaService{
@Autowired
Venta venta;
@Autowired
IventaDAO iVentaDAO;
	@Override
	public void guardarVenta(Venta unaVenta) {
		// TODO Auto-generated method stub
		iVentaDAO.save(unaVenta);
	}

	@Override
	public Venta crearVenta() {
		// TODO Auto-generated method stub
		return venta;
	}

	@Override
	public List<Venta> obtenerTodasVentas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Venta encontrarUnaVenta(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
