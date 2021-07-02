package are.edu.unju.fi.tp4.controller;

import java.io.IOException;
import java.util.Base64;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import are.edu.unju.fi.tp4.model.Producto;
import are.edu.unju.fi.tp4.service.IProductoService;
@Controller
public class ProductoController {
		@Autowired
		@Qualifier("impdos")
		IProductoService productoService;
		
		@GetMapping("/producto/mostrar")
		public String cargarProducto(Model model) {
			model.addAttribute("unProducto", productoService.obtenerProductoNuevo());
			model.addAttribute("unProducto", new Producto());
			model.addAttribute("productos", productoService.obtenerTodosProductos());
			return "producto";
		}
		@GetMapping("/producto/editar/{codigo}/{id}")
		public String editarProducto(Model model, @PathVariable(name="codigo") int codigo, @PathVariable(name="id") Integer id) throws Exception {
			try {
				Producto productoEncontrado = productoService.encontrarUnProducto(codigo,id);
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
		@PostMapping(value="/producto/guardar",consumes="multipart/form-data")
		public String guardarNuevoProducto(@Valid @RequestParam("file") MultipartFile file,@ModelAttribute("unProducto") Producto nuevoProducto,BindingResult resultado, Model model)throws IOException {
			if(resultado.hasErrors()) {
				model.addAttribute("unProducto", nuevoProducto);
				model.addAttribute("productos", productoService.obtenerTodosProductos());
				return "producto";
			}
			else {
				byte[] content = file.getBytes();
				String base64 = Base64.getEncoder().encodeToString(content);
				nuevoProducto.setImagen(base64);
				productoService.guardarProducto(nuevoProducto);
				//mostrar el listado de producto luego de la carga de un producto
				model.addAttribute("unProducto", new Producto());
				model.addAttribute("productos", productoService.obtenerTodosProductos());
				return("producto");
			}
		}
		@PostMapping(value="/producto/modificar",consumes="multipart/form-data")
		public String modificarProducto(@RequestParam("file") MultipartFile file ,@ModelAttribute("unProducto") Producto productoModificado, Model model)throws IOException {
			try {
				byte[] content = file.getBytes();
				String base64 = Base64.getEncoder().encodeToString(content);
				productoModificado.setImagen(base64);
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
		@GetMapping("/producto/eliminarProducto/{id}")
		public String eliminarProducto(Model model, @PathVariable(name="id") int id) {		
			try {			productoService.eliminarProducto(id);			
			}
			catch(Exception e){
				model.addAttribute("listErrorMessage",e.getMessage());
			}			
			return "redirect:/producto/mostrar";
		}
}
