ejb-remote: Cliente EJB remoto
=====================================
Autor: jáder belarmino  
nível: básico  
Tecnologias: EJB, JNDI  
Resumo: Como executar um EJB remoto.  
Target Product: WildFly  
Source: <https://github.com/jcbelarmino/dojo-ejb>  

Construir e implantar o projeto
-------------------------

1. Iniciar o servidor WildFly.
2. Abrir um prompt de comando na pasta dojo-ejb
3. Construir e instalar a cama servidor:

            cd server-side
            mvn clean install        
            mvn wildfly:deploy
4. Construir e executar o código Cliente

            cd ../client
            mvn clean compile
            mvn exec:exec


Desinstalar o EJB:
--------------------

        cd ../server-side

        mvn wildfly:undeploy