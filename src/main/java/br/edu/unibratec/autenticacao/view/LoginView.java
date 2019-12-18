package br.edu.unibratec.autenticacao.view;

import java.util.Scanner;

import br.edu.unibratec.autenticacao.fachada.AutenticacaoFachada;
import br.edu.unibratec.autenticacao.fachada.UsuarioFachada;
import br.edu.unibratec.autenticacao.model.Usuario;
import br.edu.unibratec.autenticacao.util.Messages;
import br.edu.unibratec.autenticacao.util.ValidacaoUtil;

public class LoginView {

	public static String viewLogin() {

		Scanner input = ValidacaoUtil.getScanner();
		String login, senha;
		String tipoUsuario = "";

		AutenticacaoFachada fachadaAutenticacao = AutenticacaoFachada.getInstancia();
		UsuarioFachada fachadaUsuario = UsuarioFachada.getInstancia();

		System.out.println("\n****************************************");
		System.out.println("        SISTEMA DE AUTENTICAÇÃO           ");
		System.out.println("****************************************");

		boolean resultado = false;
		System.out.print("\n\nUsername/E-mail:");
		login = input.nextLine();
		while (!resultado) {

			System.out.print("Senha:");
			senha = input.nextLine();

			String retorno = fachadaAutenticacao.login(login, senha);

			if ((login.equalsIgnoreCase(Messages.ADMIN) || login.equalsIgnoreCase(Messages.EMAIL_ADMIN))
					&& retorno.equalsIgnoreCase(Messages.PASSWORD_MUST_BE_RESET)) {
				String retornoResetarSenha = ResetarSenhaView.resetarSenha(login);
				if (retornoResetarSenha.equalsIgnoreCase(Messages.SUCCESS_PASSWORD_CHANGED)) {
					System.out.println(Messages.SUCCESS_PASSWORD_CHANGED);
					resultado = true;
					tipoUsuario = "Administrador";
				} else {
					System.out.println("\n\n" + retornoResetarSenha + "\n");
				}

			} else if (retorno.equalsIgnoreCase(Messages.USERNAME_BLOQUEADO)
					|| retorno.equalsIgnoreCase(Messages.USER_NOT_FOUND)) {
				System.err.println("\n\n" + retorno);
				resultado = true;
			}

			else if (retorno.equals(Messages.USERNAME_INACTIVE)) {
				System.err.println("\n\n" + retorno);
				break;
			}

			else if (!retorno.equalsIgnoreCase(Messages.PASSWORD_IS_INCORRECT)) {
				Usuario usuarioLogado = fachadaUsuario.buscarUsuario(login);
				tipoUsuario = usuarioLogado.getPerfil();
				resultado = true;
			} else {
				System.out.println("\n\n" + retorno);

			}

		}
		return tipoUsuario;

	}

}
