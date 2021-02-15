package brandaoti.sistema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.model.Avaliacao;
import brandaoti.sistema.model.Perfil;
import brandaoti.sistema.model.Treino;


public interface AvaliacaoDao extends JpaRepository<Avaliacao, Integer> {
	
	@Query(" select p from Avaliacao p where upper( p.aluno.matricula ) like upper( :matricula ) and p.ativo = TRUE ")
	List<Avaliacao> buscarMatricula(@Param("matricula") String codigo);
	
	@Query(" select p from Avaliacao p where upper( p.avaliador.matricula ) like upper( :matricula ) and p.ativo = TRUE ")
	List<Avaliacao> buscarPorAvaliador(@Param("matricula") String codigo);
	
	@Query(" select p from Avaliacao p where p.ativo = TRUE")
	List<Avaliacao> buscarTudo();
	
	
}
