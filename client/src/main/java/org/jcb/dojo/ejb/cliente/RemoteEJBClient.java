
package org.jcb.dojo.ejb.cliente;

import java.util.Hashtable;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jcb.dojo.ejb.server.HelloWorld;
import org.jcb.dojo.ejb.server.StatefulHelloWorld;

public class RemoteEJBClient {

	public static void main(String[] args) throws Exception {
		System.out.println("###########################\nCalculadora com EJB");

		invokeHelloWorld();
		invokeStatefulHelloWorld();
	}

	private static void invokeHelloWorld() throws NamingException {
		final HelloWorld hw = lookupRemoteHelloWorld();
		System.out.println("############## Executando Calculadora !!!");
		String expressao = "";
		Scanner input = new Scanner(System.in);
		System.out.println("Informe uma expressão: ");
		expressao = input.next();
		System.out.println(hw.hello(expressao));
		System.out.println("Lista de expressões");
		System.out.println(hw.historico());
	}
	
	private static void invokeStatefulHelloWorld() throws NamingException {
		final StatefulHelloWorld hw = lookupRemoteStatefulHelloWorld();
		System.out.println("############## Executando Calculadora com Precedência!!!");
		String expressao = "";
		Scanner input = new Scanner(System.in);
		System.out.println("Informe uma expressão: ");
		expressao = input.next();
		System.out.println(hw.hello(expressao));
		System.out.println("Lista de expressões");
		System.out.println(hw.historico());
	}

	private static HelloWorld lookupRemoteHelloWorld() throws NamingException {
		final Hashtable<String, String> jndiProperties = new Hashtable<>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);

		// The JNDI lookup name for a stateless session bean has the syntax of:
		// ejb:<appName>/<moduleName>/<distinctName>/<beanName>!<viewClassName>
		//
		// <appName> The application name is the name of the EAR that the EJB is
		// deployed in
		// (without the .ear). If the EJB JAR is not deployed in an EAR then
		// this is
		// blank. The app name can also be specified in the EAR's
		// application.xml
		//
		// <moduleName> By the default the module name is the name of the EJB
		// JAR file (without the
		// .jar suffix). The module name might be overridden in the ejb-jar.xml
		//
		// <distinctName> : EAP allows each deployment to have an (optional)
		// distinct name.
		// This example does not use this so leave it blank.
		//
		// <beanName> : The name of the session been to be invoked.
		//
		// <viewClassName>: The fully qualified classname of the remote
		// interface. Must include
		// the whole package name.

		// let's do the lookup
		return (HelloWorld) context
				.lookup("ejb:/wildfly-ejb-remote-server-side/HelloWorldBean!" + HelloWorld.class.getName());
	}

	private static StatefulHelloWorld lookupRemoteStatefulHelloWorld() throws NamingException {
		final Hashtable<String, String> jndiProperties = new Hashtable<>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);

		// The JNDI lookup name for a stateless session bean has the syntax of:
		// ejb:<appName>/<moduleName>/<distinctName>/<beanName>!<viewClassName>
		//
		// <appName> The application name is the name of the EAR that the EJB is
		// deployed in
		// (without the .ear). If the EJB JAR is not deployed in an EAR then
		// this is
		// blank. The app name can also be specified in the EAR's
		// application.xml
		//
		// <moduleName> By the default the module name is the name of the EJB
		// JAR file (without the
		// .jar suffix). The module name might be overridden in the ejb-jar.xml
		//
		// <distinctName> : EAP allows each deployment to have an (optional)
		// distinct name.
		// This example does not use this so leave it blank.
		//
		// <beanName> : The name of the session been to be invoked.
		//
		// <viewClassName>: The fully qualified classname of the remote
		// interface. Must include
		// the whole package name.

		// let's do the lookup
		return (StatefulHelloWorld) context.lookup("ejb:/wildfly-ejb-remote-server-side/StatefulHelloWorldBean!"
				+ StatefulHelloWorld.class.getName() + "?stateful");
	}

}