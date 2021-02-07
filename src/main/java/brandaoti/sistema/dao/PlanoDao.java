package brandaoti.sistema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.model.Plano;
import brandaoti.sistema.model.Treino;


public interface PlanoDao extends JpaRepository<Plano, Integer> {
	
	@Query(" select p from Plano p where upper( p.codigo ) like upper( :codigo )")
	List<Plano> buscarCodigo(@Param("codigo") String codigo);
	
}
