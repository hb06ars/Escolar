package brandaoti.sistema.escolar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.escolar.model.Horarios;
import brandaoti.sistema.escolar.model.Perfil;
import brandaoti.sistema.escolar.model.Usuario;

public interface HorarioDao extends JpaRepository<Horarios, Integer> {
	
	@Query(" select p from Horarios p where upper( p.periodo.nome ) like upper( :periodo ) and upper( p.diaDaSemana ) like upper( :diaDaSemana ) order by p.periodo.inicio asc, p.horarioDaAula asc , p.sala asc ")
	List<Horarios> buscarPeriodo(@Param("periodo") String periodo, @Param("diaDaSemana") String diaDaSemana);
	
	@Query(" select distinct(p.sala) from Horarios p where upper( p.periodo.nome ) like upper( :periodo ) and upper( p.diaDaSemana ) like upper( :diaDaSemana ) order by sala asc")
	List<Integer> qtdSalas(@Param("periodo") String periodo, @Param("diaDaSemana") String diaDaSemana );
	
	@Query(" select concat(p.serie,'º', p.turma ) from Horarios p where upper( p.periodo.nome ) like upper( :periodo ) and upper( p.diaDaSemana ) like upper( :diaDaSemana )  group by p.serie, p.turma order by p.sala asc, p.turma asc")
	List<String> qtdSeries(@Param("periodo") String periodo, @Param("diaDaSemana") String diaDaSemana );
	
	@Query(" select p.usuario from Horarios p where upper( p.periodo.nome ) like upper( :periodo ) and upper( p.diaDaSemana ) like upper( :diaDaSemana )  group by p.usuario")
	List<Usuario> presenca(@Param("periodo") String periodo, @Param("diaDaSemana") String diaDaSemana );
	
	@Query(" select p.horarioDaAula from Horarios p where upper( p.periodo.nome ) like upper( :periodo ) and upper( p.diaDaSemana ) like upper( :diaDaSemana )  group by p.horarioDaAula order by p.horarioDaAula asc")
	List<String> qtdHorarios(@Param("periodo") String periodo, @Param("diaDaSemana") String diaDaSemana );
	
	@Query(" select u from Horarios u where u.ultimaAtualizacao not like :hoje ")
	List<Horarios> zeraComparecimento(@Param("hoje") String hoje);
}
