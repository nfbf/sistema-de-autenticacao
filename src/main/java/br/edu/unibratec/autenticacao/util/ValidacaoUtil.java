package br.edu.unibratec.autenticacao.util;

import java.util.Scanner;
import java.util.regex.Pattern;



public class ValidacaoUtil {
	
	public static Scanner input;
	
	/**
	 * Validação de String
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean isValidString(String value) {
		value = value.replaceAll(" ", "");
		return (value.isEmpty() || value.equalsIgnoreCase("") || value == null) ? false : true;
	}

	/**
	 * Verifica se a String contém caracteres especiais e/ou acentos
	 * 
	 * @param value
	 * @return boolean
	 */

	public static boolean isPermitidoUsername(String value) {
		Pattern p = Pattern.compile("[^a-zA-Z]");
		return p.matcher(value).find();

	}

	/**
	 * Verifica se o perfil informado é válido
	 * 
	 * @param value
	 * @return boolean
	 */

	public static boolean isValidProfile(String value) {
		return (value.equalsIgnoreCase("Administrador") || value.equalsIgnoreCase("Regular")) ? true : false;

	}

	/**
	 * Verifica se o valor informado é um número
	 * 
	 * @param value
	 * @return boolean
	 */
	@SuppressWarnings("finally")
	public static int isNumber(String value) {
		int valueEscolhido = 0;
		try {
			valueEscolhido = Integer.parseInt(value);
		} catch (NumberFormatException e) {
		} finally {
			return valueEscolhido;
		}
	}

	public static boolean isAdmin(String username) {
		return Messages.ADMIN.equals(username);
	}
	
	public static Scanner getScanner() {
		
		if (input == null) {
			input = new Scanner(System.in);
		}
		
		return input;
	}
}
