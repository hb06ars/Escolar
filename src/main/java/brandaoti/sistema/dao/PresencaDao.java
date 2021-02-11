package brandaoti.sistema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.model.Plano;
import brandaoti.sistema.model.Presenca;
import brandaoti.sistema.model.Treino;
import brandaoti.sistema.model.Usuario;


public interface PresencaDao extends JpaRepository<Plano, Integer> {
	
	@Query(" select p from Presenca p order by presenca asc")
	List<Presenca> buscarTudoAlunos();
	
	@Query(" select p from Presenca p where extract(month from presenca) = extract(month from sysdate) and usuario.perfil.aluno = TRUE and YEAR(presenca)=(YEAR(NOW())) order by presenca asc")
	List<Presenca> buscarMesAlunos();
	
	
	@Query(" select p from Presenca p order by presenca asc")
	List<Presenca> buscarTudoFuncionarios();
	
	@Query(" select p from Presenca p where extract(month from presenca) = extract(month from sysdate) and YEAR(presenca)=(YEAR(NOW())) and usuario.perfil.funcionario = TRUE order by presenca asc")
	List<Presenca> buscarMesFuncionarios();
	
	@Query(" select p from Presenca p where DAY(presenca)=(DAY(NOW())-1) and YEAR(presenca)=(YEAR(NOW())) and usuario.perfil.funcionario = TRUE order by presenca asc")
	List<Presenca> presentesOntem();
	
	@Query(" select p from Presenca p where extract(month from presenca) = :mes and YEAR(presenca)=(YEAR(NOW()))")
	List<Presenca> presentesMes(@Param("mes") Integer mes);
	
	@Query(" select p from Presenca p where extract(month from presenca) = :mes and YEAR(presenca)=(YEAR(NOW())) and hour(presenca) between 0 and 11")
	List<Presenca> presentesMesManha(@Param("mes") Integer mes);
	
	@Query(" select p from Presenca p where extract(month from presenca) = :mes and YEAR(presenca)=(YEAR(NOW())) and hour(presenca) between 12 and 18")
	List<Presenca> presentesMesTarde(@Param("mes") Integer mes);
	
	@Query(" select p from Presenca p where extract(month from presenca) = :mes and YEAR(presenca)=(YEAR(NOW())) and hour(presenca) between 18 and 23")
	List<Presenca> presentesMesNoite(@Param("mes") Integer mes);
	
}
