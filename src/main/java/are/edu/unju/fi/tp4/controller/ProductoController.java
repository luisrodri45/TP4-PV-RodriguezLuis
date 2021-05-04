package are.edu.unju.fi.tp4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import are.edu.unju.fi.tp4.model.Producto;
import are.edu.unju.fi.tp4.service.IProductoService;
@Controller
public class ProductoController {
	
		@Autowired
		IProductoService productoService;
		
		@GetMapping("/producto/mostrar")
		public String cargarProducto(Model model) {
			model.addAttribute("unProducto", productoService.obtenerProductoNuevo());
			model.addAttribute("unProducto", new Producto());
			model.addAttribute("productos", productoService.obtenerTodosProductos());
			return "producto";
		}
		@GetMapping("/producto/editar/{codigo}/{nombre}")
		public String editarProducto(Model model, @PathVariable(name="codigo") int codigo, @PathVariable(name="nombre") String nombre) throws Exception {
			try {
				Producto productoEncontrado = productoService.encontrarUnProducto(codigo,nombre);
				model.addAttribute("unProducto", productoEncontrado);	
				model.addAttribute("editMode", "true");
			}
			catch (Exception e) {
				model.addAttribute("formUsuarioErrorMessage",e.getMessage());
				model.addAttribute("unProducto", productoService.obtenerProductoNuevo());
				model.addAttribute("editMode", "false");
				
			}
			model.addAttribute("productos", productoService.obtenerTodosProductos());
			return("producto");
		}
		@PostMapping("/producto/guardar")
		public String guardarNuevoProducto(@ModelAttribute("unProducto") Producto nuevoProducto, Model model) {
			productoService.guardarProducto(nuevoProducto);
			//mostrar el listado de producto luego de la carga de un producto
			model.addAttribute("unProducto", new Producto());
			model.addAttribute("productos", productoService.obtenerTodosProductos());
			return("producto");
		}
		@PostMapping("/producto/modificar")
		public String modificarProducto(@ModelAttribute("unProducto") Producto productoModificado, Model model) {
			try {
				productoService.modificarProducto(productoModificado);
				model.addAttribute("unProducto", new Producto());				
				model.addAttribute("editMode", "false");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// pasar las excepciones al html
				model.addAttribute("formUsuarioErrorMessage",e.getMessage());
				model.addAttribute("unProducto", productoModificado);			
				model.addAttribute("productos", productoService.obtenerTodosProductos());
				model.addAttribute("editMode","true");
			}
			model.addAttribute("productos", productoService.obtenerTodosProductos());
			return("producto");
		}
}
