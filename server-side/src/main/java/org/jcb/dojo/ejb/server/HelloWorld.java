package org.jcb.dojo.ejb.server;

import javax.ejb.Remote;

@Remote
public interface HelloWorld {

	public void hello(String nome);
}
