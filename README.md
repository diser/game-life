game-life
=========

Clients and servers for game Life

1. Build 
1.1. Build server part:
	cd   game-life/server-ejb-in-war
	mvn   clean install -s ./settings.xml
1.2. Build client part:
	cd   game-life/client-swing
	mvn   clean install
2. Deploy jboss-ejb-in-war.war on JBoss Application Server 7.1.
3. Enable jboss-ejb-in-war.war on JBoss Application Server 7.1.
4. Test application by loading in webbrowser url: http://localhost:8080/jboss-ejb-in-war/.
5. Run Swing client by command:
	cd   game-life/client-swing/target
	java -cp client-1.0-SNAPSHOT.jar   org.game.life.client.App

