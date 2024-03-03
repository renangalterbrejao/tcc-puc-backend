package br.com.tcc.easyfood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.tcc.easyfood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>, 
	JpaSpecificationExecutor<Pedido> 
{

	@Query("from Pedido p "
			+ " join fetch p.cliente cli"
			+ " join fetch p.estabelecimento e "
			+ " join fetch p.formaPagamento fp "
			+ " join fetch e.usuario u "
			+ " join fetch cli.usuario uCli "
			+ " join fetch e.horario h "
			+ " join fetch e.endereco ender "
			+ " join fetch ender.cidade cid "
			+ " join fetch cid.estado est "
			+ " join fetch p.itens itens "
			+ " where p.codigo = :codigo")
	Optional<Pedido> findByCodigo(String codigo);

	List<Pedido> findAll();
	
	/*@Query("select p from Pedido p "
			+ " where p.cliente.usuario.id = :usuarioId")
	Page<Pedido> findAllByUsuario(Pageable pageable, Long usuarioId);*/
	
	@Query("select p from Pedido p "
			+ " where p.cliente.usuario.id = :usuarioId")
	List<Pedido> findAllByUsuario(Long usuarioId);
	
	@Query("from Pedido p "
			+ " join fetch p.cliente cli"
			+ " join fetch p.estabelecimento e "
			+ " join fetch p.formaPagamento fpPedido "
			+ " join fetch e.usuario u "
			+ " join fetch cli.usuario uCli "
			+ " join fetch e.horario h "
			+ " join fetch e.endereco ender "
			+ " join fetch ender.cidade cid "
			+ " join fetch cid.estado est "
			//+ " join fetch p.itens itens "
			+ " where p IN :pedidos"
			+ " order by p.dataHorarioCriacao desc")
	List<Pedido> findPedidosAllData(List<Pedido> pedidos);
	
	@Query("from Pedido p "
			+ " join fetch p.itens itens "
			+ " where p.codigo = :codigo")
	List<Pedido> findItensPedidoByCodigo(String codigo);

}
