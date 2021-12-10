package br.com.springboot.jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.jdev_treinamento.model.Usuario;
import br.com.springboot.jdev_treinamento.repository.UsuarioRepository;

@RestController
public class GreetingsController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	/* ----------- C R U D ----------- */

	/* LISTAR */
	@GetMapping(value = "listatodos")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listaUsuario() {

		List<Usuario> usuarios = usuarioRepository.findAll();

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	/* SALVAR */
	@PostMapping(value = "salvar") /* Mapeia a URL */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { /* Recebe os dados para salvar */

		Usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}

	/* ATUALIZAR */
	@PutMapping(value = "atualizar") /* Mapeia a URL */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {

		if (usuario.getId() == null) {
			return new ResponseEntity<String>("Id não foi informado para atualização.", HttpStatus.OK);
		}

		Usuario user = usuarioRepository.saveAndFlush(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}

	/* DELETAR */
	@DeleteMapping(value = "deletar") /* Mapeia a URL */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<String> deletar(@RequestParam Long iduser) {

		usuarioRepository.deleteById(iduser);

		return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
	}

	// -----------------------------------------------

	/* BUSCAR POR ID */
	@GetMapping(value = "buscarpoid") /* Mapeia a URL */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<Usuario> buscarpoid(@RequestParam(name = "iduser") Long iduser) { /* Recebe os dados para consultar */

		Usuario usuario = usuarioRepository.findById(iduser).get();
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	/* BUSCAR POR NOME */
	@GetMapping(value = "buscarpornome") /* Mapeia a URL */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<List<Usuario>> buscarpornome(@RequestParam(name = "name") String name) {

		List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
	}

}
