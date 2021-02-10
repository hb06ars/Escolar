package brandaoti.sistema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.model.Contrato;
import brandaoti.sistema.model.Parcela;
import brandaoti.sistema.model.Plano;
import brandaoti.sistema.model.Treino;
import brandaoti.sistema.model.Usuario;


public interface ParcelaDao extends JpaRepository<Parcela, Integer> {
	
	@Query(" select p from Parcela p where upper( p.contrato.cliente.id ) like ( :id )")
	List<Parcela> buscarCliente(@Param("id") String id);
	
	@Query(" select p from Parcela p where upper( p.contrato.id ) like ( :id )")
	List<Parcela> buscarParcelas(@Param("id") Integer id);
	
	@Query(" select p from Parcela p where p.ativo = TRUE")
	List<Parcela> buscarTudo();
}
