package brandaoti.sistema.escolar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import brandaoti.sistema.escolar.model.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, String> {
	@Query(" select u from Usuario u where upper( u.login ) like upper( :login ) and upper( u.senha ) like upper( :senha ) ")
	Usuario fazerLogin(@Param("login") String login, @Param("senha") String senha);
}
