package br.edu.unibratec.autenticacao.fachada;

import br.edu.unibratec.autenticacao.controller.AutenticacaoController;
import br.edu.unibratec.autenticacao.fachada.AutenticacaoFachada;
import br.edu.unibratec.autenticacao.model.Usuario;

public class AutenticacaoFachada {

	private static AutenticacaoFachada instancia;
	private static AutenticacaoController controller;
	

	private AutenticacaoFachada() {

	}

	public String login(String username, String senha) {

		this.setUsuarioSessao(username);
		
		return controller.login(username, senha);

	}

	public String resetarSenha(Usuario usuario) {

		return controller.resetarSenha(usuario);
	}

	public static AutenticacaoFachada getInstancia() {
		if (instancia == null) {
			controller = new AutenticacaoController();
			instancia = new AutenticacaoFachada();
		}

		return instancia;
	}

	public String getUsuarioSessao() {
		return controller.getUsuarioSessao();
	}

	public void setUsuarioSessao(String usuarioSessao) {
		controller.setUsuarioSessao(usuarioSessao);
	}

}
