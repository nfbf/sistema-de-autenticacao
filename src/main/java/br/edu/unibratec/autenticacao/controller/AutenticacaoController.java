package br.edu.unibratec.autenticacao.controller;

import java.sql.SQLException;
import br.edu.unibratec.autenticacao.dao.AutenticacaoDao;
import br.edu.unibratec.autenticacao.model.Usuario;
import br.edu.unibratec.autenticacao.util.Messages;
import br.edu.unibratec.autenticacao.util.ValidacaoUtil;

public class AutenticacaoController {

	private AutenticacaoDao dao;
	private String usuarioSessao;
	private int tentativas = 1;

	public AutenticacaoController() {
		dao = new AutenticacaoDao();
	}

	private String autenticarUsuario(String username, String senha) throws SQLException {

		String resultado = "";

		Usuario usuario = dao.autenticarUsuario(username);

		if (usuario == null) {
			return resultado = Messages.USER_NOT_FOUND;
		}

		if ((!ValidacaoUtil.isAdmin(username)) && tentativas >= Messages.NUMBER_OF_ATTEMPTS) {
			resultado = dao.bloquearUsuario(username, senha);
			tentativas = 1;
		} else if (senha.equals(Messages.ADMIN)
				&& (username.equals(Messages.ADMIN) || username.equalsIgnoreCase(Messages.EMAIL_ADMIN))) {
			resultado = Messages.PASSWORD_MUST_BE_RESET;
		} else if (usuario.getSituacao().equals(Messages.CONDITION_BLOCKED)) {
			resultado = Messages.USER_BLOCKED;
		} else if (usuario.getStatus().equalsIgnoreCase(Messages.STATUS_INACTIVE)) {
			resultado = Messages.USERNAME_INACTIVE;
		}

		else
			resultado = (senha.equals(usuario.getSenha())) ? Messages.PASSWORD_IS_CORRECT
					: Messages.PASSWORD_IS_INCORRECT;

		if (!resultado.equalsIgnoreCase(Messages.PASSWORD_IS_CORRECT))
			tentativas++;

		return resultado;
	}

	@SuppressWarnings("finally")
	public String login(String username, String senha) {

		String resultado = "";

		try {
			resultado = autenticarUsuario(username, senha);
		} catch (SQLException e) {
			resultado = Messages.SQL_EXECPTION_ERROR;
		} finally {
			return resultado;
		}
	}

	@SuppressWarnings("finally")
	public String resetarSenha(Usuario usuario) {

		String resultado = "";

		try {

			if (!ValidacaoUtil.isValidString(usuario.getSenha()))
				resultado = Messages.FAILED_PASSWORD_CHANGED;
			else {
				resultado = dao.resertarSenha(usuario);

			}
		} catch (SQLException e) {
			resultado = Messages.SQL_EXECPTION_ERROR;
		} finally {
			return resultado;
		}
	}

	public String getUsuarioSessao() {
		return usuarioSessao;
	}

	public void setUsuarioSessao(String usuarioSessao) {
		this.usuarioSessao = usuarioSessao;
	}
}
