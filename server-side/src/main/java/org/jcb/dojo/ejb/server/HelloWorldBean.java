package org.jcb.dojo.ejb.server;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(HelloWorld.class)
public class HelloWorldBean implements HelloWorld {
	
	public void hello(String nome){
		System.out.println("Alo "+ nome);
	}

}
