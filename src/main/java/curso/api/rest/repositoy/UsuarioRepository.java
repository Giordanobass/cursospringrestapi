package curso.api.rest.repositoy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import curso.api.rest.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
