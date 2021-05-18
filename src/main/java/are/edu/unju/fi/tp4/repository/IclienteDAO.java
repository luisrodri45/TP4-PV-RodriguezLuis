package are.edu.unju.fi.tp4.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import are.edu.unju.fi.tp4.model.Cliente;
@Repository
public interface IclienteDAO extends CrudRepository<Cliente, Integer>{

	@Query("from Cliente c order by c.nroDocumento")
	public List<Cliente> obtenerClientes();
	
	public Optional<Cliente> findByNroDocumento(int dni);
	public Optional<Cliente> findById(Integer id);
}
