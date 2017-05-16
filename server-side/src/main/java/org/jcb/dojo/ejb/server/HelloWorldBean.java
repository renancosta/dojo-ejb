package org.jcb.dojo.ejb.server;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.jcb.dojo.ejb.framework.Nodo;
import org.jcb.dojo.ejb.framework.Valor;
import org.jcb.dojo.ejb.parser.ParserExpressao;

@Stateless
@Remote(HelloWorld.class)
public class HelloWorldBean implements HelloWorld {
	
List<String> nomes;
	
	@PostConstruct
	private void incia(){
		System.out.println("Inicia com @PostConstruct");
		nomes = new ArrayList<>();
	}
	public String hello(String nome){
		nomes.add(nome);
		Nodo resultado = new Valor(0);
		resultado = ParserExpressao.parser(nome);
		System.out.println("Calculando expressão: " + nome);
		return "Resultado "+ resultado.calcula();
	}
	
	public List<String> historico(){
		return nomes;
	}

	@PreDestroy
	private void finaliza(){
		System.out.println("finaliza com @PreDestroy");
		nomes = null;
	}

}
