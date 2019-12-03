package br.edu.unibratec.autenticacao.view;

import java.util.Scanner;

import br.edu.unibratec.autenticacao.fachada.AutenticacaoFachada;
import br.edu.unibratec.autenticacao.model.Usuario;
import br.edu.unibratec.autenticacao.util.ValidacaoUtil;

public class ResetarSenhaView {

	public static String resetarSenha(String username) {

		Scanner input = ValidacaoUtil.getScanner();
		String password;
		System.out.println("\n");
		System.out.println("\n****************************************");
		System.out.println("        SISTEMA DE AUTENTICAÇÃO           ");
		System.out.println("****************************************");
		System.out.println("     ----Redefinição de Senha----");
		System.out.println("\n");
		System.out.print("Por favor, digite sua nova senha:");
		password = input.nextLine();
		Usuario usuario = new Usuario(username, password);
		AutenticacaoFachada fachada = AutenticacaoFachada.getInstancia();

		String retorno = fachada.resetarSenha(usuario);
		return retorno;

	}

}
