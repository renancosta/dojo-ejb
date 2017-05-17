package org.jcb.dojo.ejb.parser;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

import org.jcb.dojo.ejb.framework.Divisao;
import org.jcb.dojo.ejb.framework.Exponenciacao;
import org.jcb.dojo.ejb.framework.Multiplicacao;
import org.jcb.dojo.ejb.framework.Nodo;
import org.jcb.dojo.ejb.framework.Soma;
import org.jcb.dojo.ejb.framework.Subtracao;
import org.jcb.dojo.ejb.framework.Valor;

public class ParserExpressao {
	private static final char ADICAO = '+';
	private static final char SUBTRACAO = '-';
	private static final char MULTIPLICACAO = '*';
	private static final char DIVISAO = '/';
	private static final char EXPONENCIACAO = '^';
	private static final char ABRE_EXP = '(';
	private static final char FECHA_EXP = ')';

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

	private static boolean verificaPrecedencia(char op1, char op2) {

		switch (op1) {

		case ADICAO:
		case SUBTRACAO:
			return !(op2 == ADICAO || op2 == SUBTRACAO);

		case MULTIPLICACAO:
		case DIVISAO:
			return op2 == EXPONENCIACAO || op2 == ABRE_EXP;

		case EXPONENCIACAO:
			return op2 == ABRE_EXP;

		case ABRE_EXP:
			return true;

		default:
			return false;
		}
	}

	private static boolean ehOperador(char c) {
		return new String("+-*/^()").indexOf(c) >= 0;
	}

	private static String converterPosfixa(String infixa) {

		Stack<String> pilhaOperador = new Stack<String>();
		StringTokenizer parser = new StringTokenizer(infixa, "+-*/^() ", true);
		StringBuffer postfixa = new StringBuffer(infixa.length());
		char c;

		while (parser.hasMoreTokens()) {
			String token = parser.nextToken();
			c = token.charAt(0);

			if ((token.length() == 1) && ehOperador(c)) {
				while (!pilhaOperador.empty() && !verificaPrecedencia(((String) pilhaOperador.peek()).charAt(0), c))
					postfixa.append(" ").append((String) pilhaOperador.pop());

				if (c == FECHA_EXP) {
					String operator = (String) pilhaOperador.pop();
					while (operator.charAt(0) != ABRE_EXP) {
						postfixa.append(" ").append(operator);
						operator = (String) pilhaOperador.pop();
					}
				} else
					pilhaOperador.push(token);
			} else if ((token.length() == 1) && c == ' ') {
			} else {
				postfixa.append(" ").append(token);
			}
		}
		while (!pilhaOperador.empty())
			postfixa.append(" ").append((String) pilhaOperador.pop());

		return postfixa.toString();
	}

	public static Nodo parserExpressao(String expre) {

		String expr = converterPosfixa(expre);

		Stack<Integer> stack = new Stack<Integer>();
		Nodo num1, num2;
		String token;
		StringTokenizer tokenizer = new StringTokenizer(expr);

		while (tokenizer.hasMoreTokens()) {
			token = tokenizer.nextToken();
			char c = token.charAt(0);
			if (ehOperador(c)) {
				num2 = new Valor(stack.pop().intValue());
				num1 = new Valor(stack.pop().intValue());
				resultado = calculaOperacao(token.charAt(0), num1, num2);
				stack.push(resultado.calcula());
			} else
				stack.push(new Integer(Integer.parseInt(token)));
		}

		resultado = new Valor(stack.pop().intValue());
		return resultado;
	}

	private static Nodo calculaOperacao(char operation, Nodo num1, Nodo num2) {

		Nodo resultadoOperacao = new Valor(0);

		switch (operation) {
		case ADICAO:
			resultadoOperacao = new Soma(num1, num2);
			break;
		case SUBTRACAO:
			resultadoOperacao = new Subtracao(num1, num2);
			break;
		case MULTIPLICACAO:
			resultadoOperacao = new Multiplicacao(num1, num2);
			break;
		case DIVISAO:
			resultadoOperacao = new Divisao(num1, num2);
		case EXPONENCIACAO:
			resultadoOperacao = new Exponenciacao(num1, num2);
		}

		return resultadoOperacao;
	}
}
