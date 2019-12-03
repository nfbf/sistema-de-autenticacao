package br.edu.unibratec.autenticacao.view;

import java.util.List;
import java.util.Scanner;

import br.edu.unibratec.autenticacao.fachada.UsuarioFachada;
import br.edu.unibratec.autenticacao.model.Pessoa;
import br.edu.unibratec.autenticacao.model.Usuario;
import br.edu.unibratec.autenticacao.util.Messages;
import br.edu.unibratec.autenticacao.util.ValidacaoUtil;

public class UsuarioView {

	private static UsuarioFachada fachada;

	public static void listarUsuarios(int opcao) {

		fachada = UsuarioFachada.getInstancia();
		List<Usuario> usuarios = fachada.listarUsuarios(opcao);

		if (!usuarios.isEmpty()) {

			if (opcao == 1)
				System.out.println("\n-------- USUÁRIOS DO SISTEMA ----------\n\n");
			else if (opcao == 2)
				System.out.println("\n-------- USUÁRIOS COM PERFIL DE ADMINISTRADOR ----------\n\n");

			else if (opcao == 3)
				System.out.println("\n-------- USUÁRIOS INATIVOS ----------\n\n");
			else if (opcao == 4)
				System.out.println("\n-------- USUÁRIOS BLOQUEADOS ----------\n\n");

			for (Usuario u : usuarios) {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Username:" + u.getLogin());
				System.out.println("Nome:" + u.getPessoa().getNome());
			}

			System.out.println("-----------------------------------------------------------------------------");
		} else {
			System.out.println(Messages.SQL_EXECPTION_ERROR);

		}
	}

	public static void excluir() {
		fachada = UsuarioFachada.getInstancia();
		Scanner input = ValidacaoUtil.getScanner();
		String username = "", retorno = "";
		boolean resultado = false;

		while (!resultado) {
			System.out.println("\n****************************************");
			System.out.println("        SISTEMA DE AUTENTICAÇÃO           ");
			System.out.println("****************************************");
			System.out.println("     ----Exclusão de Usuário----\n");
			System.out.print("\n Digite o username:");
			username = input.nextLine();
			if (ValidacaoUtil.isValidString(username)) {
				retorno = fachada.deletar(username);
				System.out.println(retorno);
			} else {
				System.err.println("O username digitado é inválido!");
			}
			resultado = retornarMenu();
		}

	}

	public static void inserir() {

		fachada = UsuarioFachada.getInstancia();
		Scanner input = ValidacaoUtil.getScanner();
		String retorno = "";
		boolean resultado = false;

		System.out.println("\n****************************************");
		System.out.println("        SISTEMA DE AUTENTICAÇÃO           ");
		System.out.println("****************************************");
		System.out.println("     ----Cadastro de Usuário----\n");

		System.out.print("Digite o nome completo: ");
		String nome = input.nextLine();
		System.out.print("Digite o e-mail: ");
		String email = input.nextLine();
		System.out.print("Digite o telefone: ");
		String telefone = input.nextLine();
		System.out.print("Digite o perfil (Administrador ou Regular): ");
		String perfil = input.nextLine();
		System.out.print("Digite o username (Não deve conter: números, caracteres especiais e/ou acentos):");
		String username = input.nextLine();
		System.out.print("Digite a senha:");
		String senha = input.nextLine();

		while (!resultado) {

			if (ValidacaoUtil.isValidString(nome) && ValidacaoUtil.isValidString(email)
					&& ValidacaoUtil.isValidString(telefone) && ValidacaoUtil.isValidString(perfil)
					&& ValidacaoUtil.isValidString(perfil) && ValidacaoUtil.isValidString(username)
					&& ValidacaoUtil.isValidString(senha)) {
				Usuario novoUsuario = new Usuario(username, senha, Messages.CONDITION_UNBLOCKED, Messages.STATUS_ACTIVE,
						perfil);
				novoUsuario.setPessoa(new Pessoa(nome, email, telefone, novoUsuario));
				retorno = fachada.inserir(novoUsuario);

				if (retorno.equals(Messages.PROFILE_INVALID)) {
					System.err.println("Perfil Inválido! Digite um perfil válido (Administrador ou Regular)");
					System.out.print("\nPerfil:");
					perfil = input.nextLine();

				}

				else if (retorno.equals(Messages.USERNAME_NOT_ALLOWED)) {
					System.err.println(
							"Username não deve conter números, caracteres especiais e/ou acentos! Digite novamente.");
					System.out.print("\nUsername:");
					username = input.nextLine();
				}

				else if (retorno.equals(Messages.USERNAME_ALREADY_REGISTERED)) {

					System.err.println("Username já cadastrado! Informe outro.");
					System.out.print("\nUsername:");
					username = input.nextLine();
				} else {
					System.out.println(retorno);
					resultado = true;
				}
			}

			else {
				System.err.println("Todos os campos são obrigatórios, por favor, preencher!");
				inserir();
			}

		}
		resultado = retornarMenu();

	}

	public static void atualizar(int opcao) {

		if (opcao == 2)
			bloqDesbloq();
		else
			AtivInat();

	}

	private static void bloqDesbloq() {
		fachada = UsuarioFachada.getInstancia();
		Scanner input = ValidacaoUtil.getScanner();
		String retorno = "";
		boolean resultado = false;

		while (!resultado) {
			System.out.println("\n****************************************");
			System.out.println("        SISTEMA DE AUTENTICAÇÃO           ");
			System.out.println("****************************************");
			System.out.println("----Bloqueando/Desbloqueando Usuário----\n");
			System.out.print("Digite o username:");
			String username = input.nextLine();

			if (ValidacaoUtil.isValidString(username)) {
				retorno = fachada.atualizar(username, 2);
				System.out.println(retorno);
			}

			else {
				System.err.println("Por favor, digite um username válido!");
			}
			resultado = retornarMenu();

		}

	}

	private static void AtivInat() {
		fachada = UsuarioFachada.getInstancia();
		Scanner input = ValidacaoUtil.getScanner();
		String retorno = "";
		boolean resultado = false;

		while (!resultado) {
			System.out.println("\n****************************************");
			System.out.println("        SISTEMA DE AUTENTICAÇÃO           ");
			System.out.println("****************************************");
			System.out.println(" ----Ativando/Inativando Usuário----\n");
			System.out.print("Digite o username:");
			String username = input.nextLine();
			if (ValidacaoUtil.isValidString(username)) {
				retorno = fachada.atualizar(username, 3);
				System.out.println(retorno);
			} else {
				System.err.println("Por favor, digite um username válido!");
			}
			resultado = retornarMenu();

		}

	}

	private static boolean retornarMenu() {
		Scanner input = ValidacaoUtil.getScanner();
		System.out.println("Deseja continuar? Digite 1 para continuar ou 2 para sair.");
		String strOpcao = input.nextLine();
		if (ValidacaoUtil.isValidString(strOpcao)) {
			int opcao = ValidacaoUtil.isNumber(strOpcao);
			if (opcao == 1) {
				return false;
			} else if (opcao == 2) {
				return true;
			} else {
				System.err.println("Você digitou uma opção inválida! Será redirecionado para o menu.");
				return true;
			}
		}

		else {
			System.err.println("Você digitou uma opção inválida! Será redirecionado para o menu.");
			return true;
		}

	}
}
