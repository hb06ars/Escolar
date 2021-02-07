package brandaoti.sistema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.model.Contrato;
import brandaoti.sistema.model.Plano;
import brandaoti.sistema.model.Treino;


public interface ContratoDao extends JpaRepository<Contrato, Integer> {
	
	@Query(" select p from Contrato p where upper( p.id ) like ( :id )")
	List<Contrato> buscarId(@Param("id") String codigo);
	
}
