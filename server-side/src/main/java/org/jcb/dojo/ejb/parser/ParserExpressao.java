package org.jcb.dojo.ejb.parser;

import java.util.Scanner;

import org.jcb.dojo.ejb.framework.Divisao;
import org.jcb.dojo.ejb.framework.Exponenciacao;
import org.jcb.dojo.ejb.framework.Multiplicacao;
import org.jcb.dojo.ejb.framework.Nodo;
import org.jcb.dojo.ejb.framework.Soma;
import org.jcb.dojo.ejb.framework.Subtracao;
import org.jcb.dojo.ejb.framework.Valor;

public class ParserExpressao {

	public static Nodo resultado;

	public static Nodo parser(String expressao) {
		resultado = new Valor(0);
		if (expressao.isEmpty() || expressao == "") {
			System.out.println("Favor informar uma expressão");
		} else {
			try {

				Scanner scanner = new Scanner(expressao);
				Scanner scannerOperacao = new Scanner(expressao);
				scanner.useDelimiter("[/^+-/*//]");
				scannerOperacao.useDelimiter("\\d+");

				resultado = new Valor(Integer.parseInt(scanner.next().trim()));
				while (scanner.hasNext()) {
					Nodo esquerda = resultado;
					String opcao = scannerOperacao.next().trim();
					Nodo direita = new Valor(Integer.parseInt(scanner.next().trim()));
					switch (opcao) {
					case "+":
						resultado = new Soma(esquerda, direita);
						break;
					case "-":
						resultado = new Subtracao(esquerda, direita);
						break;
					case "*":
						resultado = new Multiplicacao(esquerda, direita);
						break;
					case "/":
						resultado = new Divisao(esquerda, direita);
						break;
					case "^":
						resultado = new Exponenciacao(esquerda, direita);
						break;
					default:
						System.out.println("Operação inválida");
						break;

					}
				}
			} catch (Exception e) {
				System.out.println("Informar expressão válida");
			}
		}
		return resultado;
	}
}
