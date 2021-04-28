package curso.api.rest.repositoy;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import curso.api.rest.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Query("SELECT U FROM USUARIO U WHERE U.LOGIN = ?1")
    Usuario findUserByLogin(String login);

}
