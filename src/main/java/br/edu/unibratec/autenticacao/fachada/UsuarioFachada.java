package br.edu.unibratec.autenticacao.fachada;

import java.util.List;

import br.edu.unibratec.autenticacao.controller.UsuarioController;
import br.edu.unibratec.autenticacao.fachada.UsuarioFachada;
import br.edu.unibratec.autenticacao.model.Usuario;

public class UsuarioFachada {

	private static UsuarioFachada instancia;
	private static UsuarioController controller;

	private UsuarioFachada() {

	}

	public String inserir(Usuario usuario) {
		return controller.inserir(usuario);
	}

	public String deletar(String login) {
		return controller.deletar(login);
	}

	public String atualizar(String usuario, int opcao) {
		return controller.atualizar(usuario, opcao);
	}

	public List<Usuario> listarUsuarios(int opcao) {
		return controller.listar(opcao);
	}

	public Usuario buscarUsuario(String username) {
		return controller.buscar(username);
	}

	public static UsuarioFachada getInstancia() {
		if (instancia == null) {
			instancia = new UsuarioFachada();
			controller = new UsuarioController();
		}

		return instancia;
	}

}
