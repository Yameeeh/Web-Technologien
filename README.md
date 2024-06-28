# Web-Technologien<br/>
Gruppe 6:<br/>
Lucas Bockholt - Louis Ranck - Michel Path<br/>

Idee:<br/>
Website zum Informationserhalt und Austausch über die Deutschland Fußball EM 2024<br/>

Frontend:<br/>
  HTML + CSS + JS<br/>

Backend:<br/>
  Java 17 + Springboot<br/>

Installationsanleitung:<br/>
  1: Git Clone<br/>
  2: In der IDE "import project" -> "existing maven project" (Ich hab es nur mit eclipse getestet)<br/>
  3: Lombok-Plugin für IDE installieren. Hier für Eclipse: https://projectlombok.org/download (.jar ausführen und installieren)<br/>
  4: MySQL Server installieren. https://dev.mysql.com/downloads/mysql/<br/>
  5: MySQL Configurator ausführen, Port auf 3306 lassen.<br/>
  6: MySQL Workbench installieren https://dev.mysql.com/downloads/workbench/<br/>
  7: In Workbench neue DB anlegen. Name = WebDB; username = root; password = webtech<br/>
  8: WebDB öffnen -> "Schemas" Tab öffnen -> Rechtsklick -> "Create Schema" -> Name: webdb<br/>
  9: In src/main/java -> com.webtech.football -> FootballApplication.java ausführen<br/>
  10: Über localhost:8080 kann im Browser die Anwendung geöffnet werden. Einmal starten und wieder beenden.<br/>
  11: Datenbank aktualisieren<br/>
  12: Roles Tabelle -> Einträge (id=1, name=ADMIN); (id=0, name=USER) einfügen.<br/>
  13: Es muss ein default UserIcon mit der ID 999 eingefügt werden. Ein beliebiges Bild kann genommen werden.<br/>
  File Tabelle -> Eintrag (id = 999, name = "Filename und Format", path = src/main/resources/static/uploads/"Filename und Endung", type = image/Format)<br/>
  bsp: (id = 999, name = user.png, path = src/main/resources/static/uploads/user.png, type = image/png)<br/>
  Unter static/assets/user.png liegt das angedachte default image.
