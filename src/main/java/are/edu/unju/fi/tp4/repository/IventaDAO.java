package are.edu.unju.fi.tp4.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import are.edu.unju.fi.tp4.model.Venta;
@Repository
public interface IventaDAO extends CrudRepository<Venta, Integer>{
	
}
