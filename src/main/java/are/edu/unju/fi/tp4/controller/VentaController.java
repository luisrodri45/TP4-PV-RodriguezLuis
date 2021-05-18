package are.edu.unju.fi.tp4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import are.edu.unju.fi.tp4.service.IProductoService;

@Controller
public class VentaController {
	@Autowired
	@Qualifier("impdos")
	IProductoService productoService;
	@GetMapping("/producto/ventas")
	public String cargarVenta(Model model) {
		model.addAttribute("productos", productoService.obtenerTodosProductos());
		return "ventas";
	}
}
