package br.edu.unibratec.autenticacao.view;


public class App {
	public static void main(String[] args) {

		String resultado = "";

		while (resultado.equals("")) {
			resultado = LoginView.viewLogin();
		}

		if (resultado.equalsIgnoreCase("Administrador"))
			MenuView.viewMenuAdm();
		else
			MenuView.viewMenuRegular();



	}
}
