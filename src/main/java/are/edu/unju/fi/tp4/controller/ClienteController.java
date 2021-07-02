package are.edu.unju.fi.tp4.controller;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import are.edu.unju.fi.tp4.service.IClienteService;
import are.edu.unju.fi.tp4.model.Cliente;

@Controller
public class ClienteController {
	private static final Log LOGGER = LogFactory.getLog(ClienteController.class);
	@Autowired
	@Qualifier("impmysql")
	IClienteService clienteService;

	@GetMapping("/cliente/mostrar")
	public String cargarCliente(Model model) {
		model.addAttribute("unCliente", clienteService.crearCliente());
		model.addAttribute("unCliente", new Cliente());
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		//UserDetails userCliente = (UserDetails) authentication.getPrincipal();
				//System.out.println(userCliente.getUsername());
		return("cliente");
	}
	@GetMapping("/cliente/editar/{nroDocumento}/{id}")
	public String editarCliente(Model model, @PathVariable(name="nroDocumento") int dni, @PathVariable(name="id") Integer id) throws Exception {
		try {
			Cliente clienteEncontrado = clienteService.encontrarUnCliente(dni,id);
			model.addAttribute("unCliente", clienteEncontrado);
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unCliente", clienteService.crearCliente());
			model.addAttribute("editMode", "false");
			
		}
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return("cliente");
	}
	@PostMapping("/cliente/guardar")
	public String guardarNuevoProducto(@Valid @ModelAttribute("unCliente") Cliente nuevoCliente,BindingResult resultado,Model model) {
		if(resultado.hasErrors()) {
			model.addAttribute("unCliente", nuevoCliente);
			model.addAttribute("clientes", clienteService.obtenerTodosClientes());
			return "cliente";
		}
		else {
		LOGGER.info("METHOD: ingresando el metodo Guardar");	
		clienteService.guardarCliente(nuevoCliente);		
		LOGGER.info("Tamaño del Listado: "+ clienteService.obtenerTodosClientes().size());
		LOGGER.error("Se pudo guardar el usuario");
		model.addAttribute("unCliente", new Cliente());
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return("cliente");
		}
	}
	@PostMapping("/cliente/modificar")
	public String modificarCliente(@ModelAttribute("unCliente") Cliente clienteModificado,Model model) {
		try {
			LOGGER.info("METHOD: Modificando cliente en controller: "+clienteModificado.getIdCliente());
			clienteService.modificarCliente(clienteModificado);
			model.addAttribute("unCliente", new Cliente());				
			model.addAttribute("editMode", "false");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// pasar las excepciones al html
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unCliente", clienteModificado);			
			model.addAttribute("clientes", clienteService.obtenerTodosClientes());
			model.addAttribute("editMode","true");
		}
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return("cliente");
	}
	@GetMapping("/cliente/eliminarCliente/{id}")
	public String eliminarCliente(Model model, @PathVariable(name="id") int id) {		
		try {			clienteService.eliminarCliente(id);			
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/cliente/mostrar";
	}
}
