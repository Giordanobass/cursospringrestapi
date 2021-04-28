package curso.api.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Usuario;
import curso.api.rest.repositoy.UsuarioRepository;

@RestController /* Arquitetura REST */
@RequestMapping(value = "/usuario")
public class IndexController {

    @Autowired /* de fosse CDI seria @Inject */
    private UsuarioRepository usuarioRepository;

    /* 
     * Serviço RESTful 
     * 
     * */
    @GetMapping(value = "/{id}/codigovenda/{venda}", produces = "application/json")
    public ResponseEntity<Usuario> relatorio(@PathVariable(value = "id") Long id,
        @PathVariable(value = "venda") Long venda) {

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        /* O retorno seria um relatorio */
        return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
    }

    /*
     *  Serviço RESTful 
     *  
     *  */
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Usuario> init(@PathVariable(value = "id") Long id) {

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
    }

    /* 
     * Serviço RESTful
     * 
     *  */
    @DeleteMapping(value = "/{id}", produces = "application/text")
    public String delete(@PathVariable(value = "id") Long id) {

        usuarioRepository.deleteById(id);

        return "OK";
    }

    /* 
     * Serviço RESTful
     * 
     *  */
    @DeleteMapping(value = "/{id}/venda", produces = "application/text")
    public String deletevenda(@PathVariable(value = "id") Long id) {

        usuarioRepository.deleteById(id);

        return "OK";
    }

    /*
     * Lista de usuarios
     * 
     * */
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Usuario>> usuario() {

        List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();

        return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
    }

    /*
     * Metodo cadastrar
     * 
     * */
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {

        for (int pos = 0; pos < usuario.getTelefones().size(); pos++) {
            usuario.getTelefones().get(pos).setUsuario(usuario);
        }

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
    }

    /*
     * Metodo para update
     * 
     * */
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {

        for (int pos = 0; pos < usuario.getTelefones().size(); pos++) {
            usuario.getTelefones().get(pos).setUsuario(usuario);
        }

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
    }

    /*
     * Metodo update venda
     * 
     * */
    @PutMapping(value = "/{iduser}/idvenda/{idvenda}", produces = "application/json")
    public ResponseEntity<Usuario> updateVenda(@RequestBody Usuario usuario) {

        // Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new ResponseEntity("Venda atualizada", HttpStatus.OK);
    }

    /*
     * Metodo cadastra venda
     * 
     * */
    @PostMapping(value = "/{iduser}/idvenda/{idvenda}", produces = "application/json")
    public ResponseEntity<Usuario> cadastrarvenda(@PathVariable Long iduser,
        @PathVariable Long idvenda) {

        // Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new ResponseEntity("id user: " + iduser + " idvenda: " + idvenda, HttpStatus.OK);
    }
}
