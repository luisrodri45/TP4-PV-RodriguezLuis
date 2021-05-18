package are.edu.unju.fi.tp4.service.imp;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import are.edu.unju.fi.tp4.controller.ClienteController;
import are.edu.unju.fi.tp4.model.Producto;
import are.edu.unju.fi.tp4.repository.IproductoDAO;
import are.edu.unju.fi.tp4.service.IProductoService;
@Service
@Qualifier("impdos")
public class ProductoServiceMySQL implements IProductoService{
	private static final Log LOGGER = LogFactory.getLog(ClienteController.class);
	@Autowired
	Producto unProducto;
	
	@Autowired
	IproductoDAO productoDAO;
	@Override
	public void guardarProducto(Producto unProducto) {
		// TODO Auto-generated method stub
		productoDAO.save(unProducto);
	}

	@Override
	public void eliminarProducto(int id) throws Exception{
		// TODO Auto-generated method stub
		Producto productoEliminar = productoDAO.findByCodigo(id).orElseThrow(()->new Exception("El Cliente no fue encontrado"));
		productoDAO.delete(productoEliminar);
	}

	@Override
	public Producto obtenerProducto(String nombreProducto) {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public List<Producto> obtenerTodosProductos() {
		// TODO Auto-generated method stub
		return (List<Producto>) productoDAO.findAll();
	}

	@Override
	public Producto obtenerProductoNuevo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto obtenerUltimoProducto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto encontrarUnProducto(int codigo, Integer id) throws Exception{
		// TODO Auto-generated method stub
		return productoDAO.findByCodigo(codigo).orElseThrow(()->new Exception("El producto NO existe"));
	}

	@Override
	public void modificarProducto(Producto productoModificado) throws Exception{
		// TODO Auto-generated method stub
		LOGGER.info("METHOD: Modificando cliente: "+productoModificado.getNombre());
		LOGGER.info("METHOD: Modificando cliente: "+productoModificado.getIdProducto());
		Producto productoAModificar = productoDAO.findById(productoModificado.getIdProducto()).orElseThrow(()->new Exception("El producto no fue encontrado"));
		cambiarProducto(productoModificado, productoAModificar);
		LOGGER.info("METHOD: Modificando cliente 1: "+productoModificado.getIdProducto());
		LOGGER.info("METHOD: Modificando cliente 2: "+productoAModificar.getIdProducto());
		productoDAO.save(productoAModificar);
	}
	private void cambiarProducto(Producto desde, Producto hacia) {
		//observen que vamos a pasar todos los atributos del cliente que viene, hacia el cliente que ya está en la BD
		hacia.setCodigo(desde.getCodigo());
		hacia.setDescripcion(desde.getDescripcion());
		hacia.setMarca(desde.getMarca());
		hacia.setNombre(desde.getNombre());
		hacia.setPrecio(desde.getPrecio());
		hacia.setStock(desde.getStock());
		//observen que NO se ha cambiado el id, ya que ese atributo no debería permitirse cambiar
	}
}
