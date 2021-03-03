package brandaoti.sistema.ligaweb.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.ligaweb.model.Recado;

public interface RecadoDao extends JpaRepository<Recado, Integer> {
	@Query(" select p from Recado p order by p.data asc")
	List<Recado> ordenado( );
	
	
}
