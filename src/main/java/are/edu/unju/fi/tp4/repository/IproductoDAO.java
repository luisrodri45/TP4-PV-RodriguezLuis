package are.edu.unju.fi.tp4.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import are.edu.unju.fi.tp4.model.Producto;
@Repository
public interface IproductoDAO extends CrudRepository<Producto, Integer>{
	@Query("from Producto p order by p.codigo")
	public List<Producto> obtenerProductos();
	
	public Optional<Producto> findByCodigo(int codigo);
	public Optional<Producto> findById(Integer id);
}
