package brandaoti.sistema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.model.Perfil;
import brandaoti.sistema.model.Treino;


public interface TreinoDao extends JpaRepository<Treino, Integer> {
	
	@Query(" select p from Treino p where upper( p.matricula ) like upper( :matricula ) order by tipoOrdem, ordemDoDia ")
	List<Treino> buscarMatricula(@Param("matricula") String codigo);
	
	@Query(" select p from Treino p where p.ativo = TRUE")
	List<Treino> buscarTudo();
	
	@Query(" select max(p.tipoOrdem) from Treino p where upper( p.matricula ) like upper( :matricula )")
	Integer maiorTreino(@Param("matricula") String codigo);
	
}
