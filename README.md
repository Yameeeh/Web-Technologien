# Web-Technologien
Gruppe 6:
Lucas Bockholt - Louis Ranck - Michel Path

Idee:
Website zum Informationserhalt und Austausch über die Deutschland Fußball EM 2024

Frontend:
  HTML + CSS + JS

Backend:
  Java 17 + Springboot

Installationsanleitung:
  1: Git Clone
  2: In der IDE "import project" -> "existing maven project" (Ich hab es nur mit eclipse getestet)
  3: Lombok-Plugin für IDE installieren. Hier für Eclipse: https://projectlombok.org/download (.jar ausführen und installieren)
  4: MySQL Server installieren. https://dev.mysql.com/downloads/mysql/
  5: MySQL Configurator ausführen, Port auf 3306 lassen.
  6: MySQL Workbench installieren https://dev.mysql.com/downloads/workbench/
  7: In Workbench neue DB anlegen. Name = WebDB; username = root; password = webtech
  8: WebDB öffnen -> "Schemas" Tab öffnen -> Rechtsklick -> "Create Schema" -> Name: webdb
  9: In src/main/java -> com.webtech.football -> FootballApplication.java ausführen
  10: Über localhost:8080 kann im Browser die Anwendung geöffnet werden. Einmal starten und wieder beenden.
  11: Datenbank aktualisieren
  12: Roles Tabelle -> Einträge (id=1, name=ADMIN); (id=0, name=USER) einfügen.
  13: Es muss ein default UserIcon mit der ID 999 eingefügt werden. Ein beliebiges Bild kann genommen werden.
  File Tabelle -> Eintrag (id = 999, name = "Filename und Format", path = src/main/resources/static/uploads/"Filename und Endung", type = image/Format)
  bsp: (id = 999, name = user.png, path = src/main/resources/static/uploads/user.png, type = image/png)
  Unter static/assets/user.png liegt das angedachte default image.
