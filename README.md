game-life
=========

Clients and servers for game Life



1. Prerequisites
1.1. Java version 1.7 or later.
1.2. Maven version 3.3 or later.
1.3. JBoss Application Server 7.



2. Simultaneous build and deploy

2.1. Start JBoss Application Server 7.
2.2. Build-deploy server part:
  cd   game-life/server-ejb-in-war
  mv  ~/.m2/settings.xml  ~/.m2/settings.xml-orig
  cp  ./settings.xml   ~/.m2/settings.xml
  mvn   clean install jboss-as:deploy   -s ./settings.xml
2.3. Build client part:
  cd   game-life/client-swing
  mvn   clean install
2.4. Run Swing client by command:
  cd   game-life/client-swing/target
  java -cp client-1.0-SNAPSHOT.jar   org.game.life.client.App



3. Separate build and deploy

3.1. Build 
3.1.1. Build server part:
  cd   game-life/server-ejb-in-war
  mv  ~/.m2/settings.xml  ~/.m2/settings.xml-orig
  cp  ./settings.xml   ~/.m2/settings.xml
  mvn   clean install jboss-as:deploy   -s ./settings.xml
3.1.2. Build client part:
  cd   game-life/client-swing
  mvn   clean install
3.2. Deploy jboss-ejb-in-war.war on JBoss Application Server 7. You can do this by Administration console http://localhost:9990/.
  Or you can use command:
    cd   game-life/server-ejb-in-war
    mvn   jboss-as:deploy   -s ./settings.xml
3.3. Enable jboss-ejb-in-war.war on JBoss Application Server 7. You can do this by Administration console http://localhost:9990/.
2.4. Run Swing client by command:
  cd   game-life/client-swing/target
  java -cp client-1.0-SNAPSHOT.jar   org.game.life.client.App



4. Undeploy and clean
4.1. Start JBoss Application Server 7.
4.2. In Administration console http://localhost:9990/ undeploy jboss-ejb-in-war.
    Or use command:
      cd   game-life/server-ejb-in-war
      mvn   jboss-as:undeploy   -s ./settings.xml
4.3. Restore maven global settings:
  cp   ~/.m2/settings.xml-orig   ~/.m2/settings.xml


