package br.edu.unibratec.autenticacao.view;


import java.util.Scanner;


import br.edu.unibratec.autenticacao.util.ValidacaoUtil;

public class MenuView {

	public static void viewMenuRegular() {

		Scanner input = ValidacaoUtil.getScanner();
		int opcao = 0;
		boolean resultado = false;

		while (!resultado) {
			System.out.println("\n****************************************");
			System.out.println("        SISTEMA DE AUTENTICAÇÃO           ");
			System.out.println("****************************************");
			System.out.println("    ---- Listagem de Usuários ----");
			System.out.println("\n");
			System.out.println("  1- Listar todos os usuários ");
			System.out.println("  2- Listar todos os usuários administrativos");
			System.out.println("  3- Listar todos os usuários inativos");
			System.out.println("  4- Listar todos os usuários bloqueados");
			System.out.println("  5- Sair");
			System.out.println("\n");
			System.out.print("Escolha uma opção:");
			String strOpcao = input.nextLine();

			opcao = ValidacaoUtil.isNumber(strOpcao);

			if (opcao >= 1 && opcao <= 4) {
				UsuarioView.listarUsuarios(opcao);
				System.out.println("Deseja continuar? Digite 1 para voltar ao menu ou 2 para sair.");
				strOpcao = input.nextLine();

				if (ValidacaoUtil.isValidString(strOpcao)) {
					opcao = ValidacaoUtil.isNumber(strOpcao);
					if (opcao == 1) {
						resultado = false;
					} else if (opcao == 2) {
						resultado = true;
						sair();
					}

				}

			} else if (opcao == 5) {
				resultado = true;
				sair();
			} else {
				System.err.println("Opção Inválida! Digite uma opção válida.");
			}

		}

	}

	public static void viewMenuAdm() {

		@SuppressWarnings("resource")
		Scanner input = ValidacaoUtil.getScanner();
		String strOpcao;
		int opcao;
		boolean resultado = false;
		while (!resultado) {
			System.out.println("\n****************************************");
			System.out.println("        SISTEMA DE AUTENTICAÇÃO           ");
			System.out.println("****************************************");
			System.out.println(" 1. Listagem de Usuários ");
			System.out.println(" 2. Desbloquear/Bloquear usuário ");
			System.out.println(" 3. Ativar/Inativar usuário ");
			System.out.println(" 4. Excluir um usuário ");
			System.out.println(" 5. Inserir um usuário ");
			System.out.println(" 6. Sair ");
			System.out.println("\n");
			System.out.print("Escolha uma opção:");
			strOpcao = input.nextLine();

			opcao = ValidacaoUtil.isNumber(strOpcao);

			if (opcao == 1) {
				viewMenuRegular();
				resultado= true;
				
			}

			else if (opcao == 2 || opcao == 3) {
				UsuarioView.atualizar(opcao);

			} else if (opcao == 4) {
				UsuarioView.excluir();

			} else if (opcao == 5) {
				UsuarioView.inserir();
			}

			else if (opcao == 6) {
				sair();
				break;
			}

			else {
				System.err.println("Opção Inválida! Digite uma opção válida.");

			}

		}
	}

	private static void sair() {
		System.out.println("\n\n****************************");
		System.out.println("Logout efetuado com sucesso!");
		System.out.println("****************************");
	}

}
