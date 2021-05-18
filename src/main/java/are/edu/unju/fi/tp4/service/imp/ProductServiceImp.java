package are.edu.unju.fi.tp4.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import are.edu.unju.fi.tp4.model.Producto;
import are.edu.unju.fi.tp4.service.IProductoService;

import are.edu.unju.fi.tp4.util.ListadoProductos;
@Service
@Qualifier("otroImp1")
public class ProductServiceImp implements IProductoService{
	private static final Log LUIS = LogFactory.getLog(ProductServiceImp.class);
	//como se hace la solucion del problema
			//guardar en Array		
			//guarde en una BD MySQL		
			//guarde en una DB Oracle
	@Autowired
	Producto unProducto;
	//estoy creando una estructura que guardará todos los productos
		//ArrayList<Producto> listaDeProductos = new ArrayList<Producto>();
	private List<Producto> listadoProductos = ListadoProductos.productos;
	@Override
	public void guardarProducto(Producto unProducto) {
		// TODO Auto-generated method stub
		//esta línea la puse solo para que escriba en la consola el nombre del producto que llega
				//una línea que me sirve a mí de control
				//un log artesanal
				System.out.println(unProducto.getNombre());
				listadoProductos.add(unProducto);

				//otra línea de control
				//quiero saber cuántos elementos hay en el arreglo
				System.out.println(listadoProductos.size());
				LUIS.info("METHOD: ingresando a Guardar Producto");
				LUIS.info("RESULT: guardado " + listadoProductos.get(listadoProductos.size()-1).getNombre()+" de forma exitosa");
	}

	@Override
	public void modificarProducto(Producto productoModificado) {
		int aux,aux2,aux5 = 0;
		String aux3,aux4;
		boolean band=false;
		aux2=productoModificado.getCodigo();
		aux4=productoModificado.getNombre();
		for (int i = 0; i < listadoProductos.size() && band==false; i++){
			aux=listadoProductos.get(i).getCodigo();
			aux3=listadoProductos.get(i).getNombre();
		    if ((aux == aux2)&&(aux4.equals(aux3))) {
		    	band=true;
		    	aux5=i;
		    }
		}
		for (int i = 0; i < listadoProductos.size(); i++){
		    if ((i == aux5)) {
		    	listadoProductos.set(i, productoModificado);
		    }
		}
		
	}


	@Override
	public Producto obtenerProducto(String nombreProducto) {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public List<Producto> obtenerTodosProductos() {
		// TODO Auto-generated method stub
		return listadoProductos;
	}

	@Override
	public Producto obtenerProductoNuevo() {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public Producto obtenerUltimoProducto() {
		// TODO Auto-generated method stub
		int i = listadoProductos.size() - 1;
		LUIS.error("METHOD: Buscando ultimo producto");
		LUIS.error("RESULT: encontrado " + listadoProductos.get(listadoProductos.size()-1).getNombre()+" de forma exitosa");
		return listadoProductos.get(i);
	}

	@Override
	//esto esta mal
	public Producto encontrarUnProducto(int codigo, Integer nombre) {
		for (int i = 0; i < listadoProductos.size(); i++){
		    if ((listadoProductos.get(i).getCodigo() == codigo)&&(listadoProductos.get(i).getNombre().equals(nombre))){
		    	unProducto = listadoProductos.get(i);
		    }
		}
		return unProducto;
	}

	@Override
	public void eliminarProducto(int id) {
		for (int i = 0; i < listadoProductos.size(); i++){
			if(listadoProductos.get(i).getCodigo()==id) {
				listadoProductos.remove(i);
			}
		}
		
	}

}
