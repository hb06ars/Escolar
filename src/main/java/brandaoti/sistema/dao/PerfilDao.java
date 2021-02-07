package brandaoti.sistema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.model.Perfil;

public interface PerfilDao extends JpaRepository<Perfil, Integer> {
	
	@Query(" select p from Perfil p where admin = 1 ")
	List<Perfil> buscarAdm();
	
	@Query(" select p from Perfil p where aluno = 1 ")
	List<Perfil> buscarAluno();
	
	@Query(" select p from Perfil p where funcionario = 1 ")
	List<Perfil> buscarFuncionario();
	
	@Query(" select p from Perfil p where professor = 1 ")
	List<Perfil> buscarProfessor();
	
	@Query(" select p from Perfil p where upper( p.codigo ) like upper( :codigo ) and ativo = 1 ")
	Perfil buscarCodigo(@Param("codigo") String codigo);
	
	@Query(" select p from Perfil p where upper( p.nome ) like upper( :nome ) and ativo = 1 ")
	List<Perfil> buscarNome(@Param("nome") String nome);
}
