package brandaoti.sistema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.model.Plano;
import brandaoti.sistema.model.Presenca;
import brandaoti.sistema.model.Treino;


public interface PresencaDao extends JpaRepository<Plano, Integer> {
	
	@Query(" select p from Presenca p order by presenca asc")
	List<Presenca> buscarTudoAlunos();
	
	@Query(" select p from Presenca p where extract(month from presenca) = extract(month from sysdate) and usuario.perfil.aluno = TRUE order by presenca asc")
	List<Presenca> buscarMesAlunos();
	
	
	@Query(" select p from Presenca p order by presenca asc")
	List<Presenca> buscarTudoFuncionarios();
	
	@Query(" select p from Presenca p where extract(month from presenca) = extract(month from sysdate) and usuario.perfil.funcionario = TRUE order by presenca asc")
	List<Presenca> buscarMesFuncionarios();
	
	@Query(" select p from Presenca p where DAY(presenca)=(DAY(NOW())-1) and usuario.perfil.funcionario = TRUE order by presenca asc")
	List<Presenca> presentesOntem();
	
	
	
}
