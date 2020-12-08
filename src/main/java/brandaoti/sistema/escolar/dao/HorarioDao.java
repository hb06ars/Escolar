package brandaoti.sistema.escolar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.escolar.model.Horarios;
import brandaoti.sistema.escolar.model.Perfil;

public interface HorarioDao extends JpaRepository<Horarios, Integer> {
	
	@Query(" select p from Horarios p where upper( p.periodo.nome ) like upper( :periodo ) order by p.periodo.inicio asc, horarioDaAula asc , sala asc ")
	List<Horarios> buscarPeriodo(@Param("periodo") String periodo);
	
	@Query(" select max(p.sala) from Horarios p where upper( p.periodo.nome ) like upper( :periodo ) ")
	Integer qtdSalas(@Param("periodo") String periodo);
}
