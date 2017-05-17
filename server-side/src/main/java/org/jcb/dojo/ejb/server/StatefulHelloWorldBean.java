package org.jcb.dojo.ejb.server;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remote;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import org.jcb.dojo.ejb.framework.Nodo;
import org.jcb.dojo.ejb.framework.Valor;
import org.jcb.dojo.ejb.parser.ParserExpressao;

@Stateful
@Remote(StatefulHelloWorld.class)
public class StatefulHelloWorldBean implements StatefulHelloWorld {
	
List<String> nomes;
	
	@PostConstruct
	private void incia(){
		System.out.println("Inicia Stateful com @PostConstruct");
		nomes = new ArrayList<>();
	}
	
	public String hello(String nome){
		nomes.add(nome);
		Nodo resultado = new Valor(0);
		resultado = ParserExpressao.parserExpressao(nome);
		System.out.println("Calculando expressão: " + nome);
		return "Resultado "+ resultado.calcula();
	}
	
	public List<String> historico(){
		return nomes;
	}

	@PreDestroy
	private void finaliza(){
		System.out.println("finaliza stateful com @PreDestroy");
		nomes = null;
	}
	
	@PrePassivate
	private void passivando(){
		System.out.println("passivando o EJB");
	}

	@PostActivate
	private void ativando(){
		System.out.println("ativando o EJB");
	}

}
