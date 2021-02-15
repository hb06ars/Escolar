package brandaoti.sistema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.model.Aula;
import brandaoti.sistema.model.Horario;
import brandaoti.sistema.model.Plano;
import brandaoti.sistema.model.Treino;


public interface AulaDao extends JpaRepository<Aula, Integer> {
	
	@Query(" select p from Aula p where upper( p.codigo ) like upper( :codigo )")
	List<Aula> buscarCodigo(@Param("codigo") String codigo);
	
	@Query(" select p from Aula p where ativo = TRUE")
	List<Aula> buscarTudo();
	
	@Query(" select a from Aula a where ativo = TRUE order by a.inicio, a.fim asc ")
	List<Aula> buscarhorarios();
	
	
	
}
