package are.edu.unju.fi.tp4.service;

import java.util.List;

import org.springframework.stereotype.Service;

import are.edu.unju.fi.tp4.model.Producto;
@Service
public interface IProductoService {
	//que hace con Producto( clase) 
	//*
	//como se hace la solucion al problema 
	//guardar en una array o en una BD de oracle o MySql
	public void guardarProducto(Producto unProducto);
	public void eliminarProducto(Producto productoaEliminar);
	public Producto obtenerProducto(String nombreProducto);
	public List<Producto> obtenerTodosProductos();
	public Producto obtenerProductoNuevo();
	public Producto obtenerUltimoProducto();
	public Producto encontrarUnProducto(int codigo,String nombre);
	public void modificarProducto(Producto productoModificado);
	
}
