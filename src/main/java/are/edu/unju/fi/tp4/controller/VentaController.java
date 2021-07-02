package are.edu.unju.fi.tp4.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import are.edu.unju.fi.tp4.model.Producto;
import are.edu.unju.fi.tp4.model.Venta;
import are.edu.unju.fi.tp4.service.IClienteService;
import are.edu.unju.fi.tp4.service.IProductoService;
import are.edu.unju.fi.tp4.service.IVentaService;

@Controller
public class VentaController {
	private static final Log LOGGER = LogFactory.getLog(ClienteController.class);
	@Autowired
	@Qualifier("impdos")
	IProductoService productoService;
	@Autowired
	@Qualifier("impmysql")
	IClienteService clienteService;
	@Autowired
	Producto productoSeleccionado;
	@Autowired
	IVentaService ventaService;
	@Autowired
	Venta venta;
	@GetMapping("/producto/ventas")
	public String cargarVenta(Model model) {
		model.addAttribute("productos", productoService.obtenerTodosProductos());
		return "ventas";
	}
	@GetMapping("/producto/vender/{codigo}")	
	public String realizarVenta(Model model, @PathVariable(name="codigo") Integer codigo) throws Exception {
		try {
			productoSeleccionado = productoService.encontrarUnProducto(codigo, 0);
			venta = ventaService.crearVenta();
			venta.setProducto(productoSeleccionado);
			model.addAttribute("venta",venta);
			model.addAttribute("clientes",clienteService.obtenerTodosClientes());
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());		
		}		
		return "modal-venta";
	}
	@PostMapping("/producto/vender")
	public String guardarVenta(@ModelAttribute("venta") Venta unaVenta,Model model) {
		LOGGER.error("Venta real: "+unaVenta.getProducto().getNombre());
		ventaService.guardarVenta(unaVenta);
		
		return ("redirect:/producto/ventas");
	}
}
