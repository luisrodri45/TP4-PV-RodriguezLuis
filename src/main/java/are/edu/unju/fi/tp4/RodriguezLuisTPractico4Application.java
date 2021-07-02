package are.edu.unju.fi.tp4;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import are.edu.unju.fi.tp4.model.Cliente;
import are.edu.unju.fi.tp4.service.IClienteService;

@SpringBootApplication
public class RodriguezLuisTPractico4Application implements CommandLineRunner{
	@Autowired
	@Qualifier("impmysql")
	IClienteService clienteService;
	@Autowired
	Cliente cliente;
	public static void main(String[] args) {
		SpringApplication.run(RodriguezLuisTPractico4Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		//inicializo un cliente para pruebas
				//cliente.setNroDocumento(100001);
				//cliente.setNombreApellido("user");
				//cliente.setTipoDocumento("DNI");
				//cliente.setEmail("correo@gmail.com");
				//cliente.setFechaNacimiento(LocalDate.now());
				//cliente.setFechaUltimaCompra(LocalDate.now());
				//cliente.setPassword("123456");				
				//clienteService.guardarCliente(cliente);		
	}
}
