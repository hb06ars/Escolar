package brandaoti.sistema.escolar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import brandaoti.sistema.escolar.model.Alunos;

public interface AlunosDao extends JpaRepository<Alunos, Integer> {
	@Query(" select u from Alunos u where upper( u.login ) like upper( :login ) and upper( u.senha ) like upper( :senha ) ")
	Alunos fazerLogin(@Param("login") String login, @Param("senha") String senha);
}
