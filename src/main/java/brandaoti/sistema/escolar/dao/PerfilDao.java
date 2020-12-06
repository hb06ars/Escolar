package brandaoti.sistema.escolar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import brandaoti.sistema.escolar.model.Perfil;

public interface PerfilDao extends JpaRepository<Perfil, Integer> {
	@Query(" select p from Perfil p where upper( p.codigo ) like upper( :codigo ) and ativo = 1 ")
	Perfil buscarCodigo(@Param("codigo") String codigo);
}
