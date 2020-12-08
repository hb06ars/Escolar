package brandaoti.sistema.escolar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.escolar.model.Alunos;
import brandaoti.sistema.escolar.model.Perfil;
import brandaoti.sistema.escolar.model.Periodos;

public interface PeriodoDao extends JpaRepository<Periodos, Integer> {
	
	@Query(" select u from Periodos u where upper( u.nome ) like upper( :nome )")
	Periodos porNome(@Param("nome") String nome);
	
	@Query(" select u from Periodos u order by u.inicio asc")
	List<Periodos> ordenado();
}
