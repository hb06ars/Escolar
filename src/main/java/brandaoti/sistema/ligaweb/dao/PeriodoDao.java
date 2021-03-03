package brandaoti.sistema.ligaweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.ligaweb.model.Alunos;
import brandaoti.sistema.ligaweb.model.Perfil;
import brandaoti.sistema.ligaweb.model.Periodos;

public interface PeriodoDao extends JpaRepository<Periodos, Integer> {
	
	@Query(" select u from Periodos u where upper( u.nome ) like upper( :nome )")
	List<Periodos> porNome(@Param("nome") String nome);
	
	@Query(" select u from Periodos u order by u.inicio asc")
	List<Periodos> ordenado();
	
	@Query("select u from Periodos u")
	List<Periodos> periodos();
	
}
