package are.edu.unju.fi.tp4.service;
import java.util.List;


import are.edu.unju.fi.tp4.model.Producto;

public interface IProductoService {
	public void guardarProducto(Producto unProducto);
	public void eliminarProducto(int id) throws Exception;
	public Producto obtenerProducto(String nombreProducto);
	public List<Producto> obtenerTodosProductos();
	public Producto obtenerProductoNuevo();
	public Producto obtenerUltimoProducto();
	public Producto encontrarUnProducto(int codigo,Integer id) throws Exception;
	public void modificarProducto(Producto productoModificado) throws Exception;
	
}
