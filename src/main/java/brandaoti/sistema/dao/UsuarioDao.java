package brandaoti.sistema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.model.Treino;
import brandaoti.sistema.model.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Integer> {
	@Query(" select u from Usuario u where upper( u.matricula ) like upper( :matricula ) and upper( u.senha ) like upper( :senha ) ")
	Usuario fazerLogin(@Param("matricula") String login, @Param("senha") String senha);
	
	@Query(" select u from Usuario u where 1=1 and nome = '(:aluno.nome)' ")
	List<Usuario> filtro(@Param("aluno") Usuario aluno);
	
	@Query(" select t from Treino t where 1=1 and t.matricula like '(:usuario.matricula)' ")
	List<Treino> treinosUsario(@Param("usuario") Usuario usuario);
	
}
