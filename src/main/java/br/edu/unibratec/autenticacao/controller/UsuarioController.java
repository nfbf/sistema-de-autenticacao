package br.edu.unibratec.autenticacao.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unibratec.autenticacao.dao.UsuarioDao;
import br.edu.unibratec.autenticacao.fachada.AutenticacaoFachada;
import br.edu.unibratec.autenticacao.model.Usuario;
import br.edu.unibratec.autenticacao.util.Messages;
import br.edu.unibratec.autenticacao.util.ValidacaoUtil;

public class UsuarioController {

	private UsuarioDao dao;
	private AutenticacaoFachada autenticacaoFachada;

	public UsuarioController() {
		dao = new UsuarioDao();
	}

	@SuppressWarnings("finally")
	public String inserir(Usuario novoUsuario) {
		String retorno = "";

		try {

			if (!ValidacaoUtil.isValidProfile(novoUsuario.getPerfil()))
				retorno = Messages.PROFILE_INVALID;
			else if (ValidacaoUtil.isPermitidoUsername(novoUsuario.getLogin()))
				retorno = Messages.USERNAME_NOT_ALLOWED;

			else {
				Usuario verificar = buscar(novoUsuario.getLogin());
				if (verificar != null)
					retorno = Messages.USERNAME_ALREADY_REGISTERED;
				else
					retorno = dao.inserir(novoUsuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			retorno = Messages.SQL_EXECPTION_ERROR;

		} finally {
			return retorno;
		}
	}

	@SuppressWarnings("finally")
	public String deletar(String login) {
		autenticacaoFachada = AutenticacaoFachada.getInstancia();
		String retorno = "";
		try {
			if (login.equals(autenticacaoFachada.getUsuarioSessao()))
				retorno = Messages.WITHOUT_PERMISSION_DELETE_USERNAME;
			else
				retorno = dao.deletar(login);

		} catch (SQLException e) {
			retorno = Messages.SQL_EXECPTION_ERROR;
			e.printStackTrace();

		} finally {
			return retorno;
		}
	}

	@SuppressWarnings("finally")
	public String atualizar(String username, int opcao) {
		String retorno = "";
		try {
			Usuario usuario = buscar(username);
			if (usuario == null) {
				return retorno = Messages.USER_NOT_FOUND;
			}
			if (opcao == 2) {
				retorno = (usuario.getSituacao().equalsIgnoreCase(Messages.CONDITION_BLOCKED)
						? Messages.SUCESS_USERNAME_DESBLOQ
						: Messages.SUCESS_USERNAME_BLOQ);
				usuario.setSituacao((usuario.getSituacao().equalsIgnoreCase(Messages.CONDITION_BLOCKED)
						? Messages.CONDITION_UNBLOCKED
						: Messages.CONDITION_BLOCKED));
			} else {
				retorno = (usuario.getStatus().equalsIgnoreCase(Messages.STATUS_INACTIVE)
						? Messages.SUCESS_USERNAME_ATIV
						: Messages.SUCESS_USERNAME_DESATIV);
				usuario.setStatus(
						(usuario.getStatus().equalsIgnoreCase(Messages.STATUS_INACTIVE) ? Messages.STATUS_ACTIVE
								: Messages.STATUS_INACTIVE));
			}

			dao.atualizar(usuario);

		} catch (SQLException e) {
			retorno = Messages.SQL_EXECPTION_ERROR;
			e.printStackTrace();
		} finally {
			return retorno;
		}

	}

	@SuppressWarnings({ "finally" })
	public Usuario buscar(String username) {
		Usuario buscarUsuario = null;
		try {
			buscarUsuario = dao.buscar(username);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			return buscarUsuario;
		}
	}

	@SuppressWarnings({ "finally" })
	public List<Usuario> listar(int opcao) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			usuarios = dao.listar(opcao);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return usuarios;
		}

	}

}
