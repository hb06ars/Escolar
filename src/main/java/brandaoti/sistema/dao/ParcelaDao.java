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
	
	@Query(" select p from Parcela p where upper( p.contrato.id ) like ( :id ) order by p.vencimento asc")
	List<Parcela> buscarParcelas(@Param("id") Integer id);
	
	@Query(" select p from Parcela p where p.ativo = TRUE order by p.vencimento asc")
	List<Parcela> buscarTudo();
	
	@Query(" select p from Parcela p where p.vencimento < now() and p.ativo = TRUE order by p.vencimento asc ")
	List<Parcela> buscarPendencias();
	
	@Query(" select p from Parcela p where upper( p.contrato.id ) like ( :id ) and p.ativo = TRUE")
	List<Parcela> buscarPorContrato(@Param("id") Integer id);
	
	
}
