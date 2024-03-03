package br.com.tcc.easyfood.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.tcc.easyfood.domain.model.Cliente;
import br.com.tcc.easyfood.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario>  {

	Optional<Usuario> findByEmail(String email);
	
	@Query("from Usuario usu "
			+ " where usu.email = :email "
			+ " and usu.senha = :senha ")
	Optional<Usuario> findByEmailAndSenha(@Param("email") String email, @Param("senha") String senha);
	
}
