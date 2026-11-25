# Persönliche CV-Website (Java)

Diese Website läuft mit dem eingebauten Java-Webserver und benötigt keine externen Java-Bibliotheken.

## Starten

Voraussetzung: ein **JDK ab Version 11** (nicht nur eine Java Runtime).

In diesem Ordner ausführen:

```powershell
javac -d build src\Main.java
java -cp "build;src" Main
```

Danach im Browser öffnen: `http://localhost:8080`

## Inhalt anpassen

Die Texte stehen in `src/public/index.html`. Farben und das responsive Design sind in `src/public/style.css`.

## Online veröffentlichen

Die Website ist jetzt für einen Java-fähigen Hoster vorbereitet. Der enthaltene `Dockerfile` startet die Anwendung mit dem Port, den der Hoster bereitstellt.

### Veröffentlichung mit GitHub und Render

1. Erstelle auf GitHub ein neues Repository, zum Beispiel `meine-website`.
2. Lade alle Dateien aus diesem Ordner in das Repository hoch. Der `Dockerfile` muss im obersten Ordner liegen.
3. Erstelle ein Konto bei [Render](https://render.com/) und verbinde dein GitHub-Konto.
4. Wähle **New** → **Web Service** → dein Repository.
5. Bei *Language* bzw. *Runtime* wähle **Docker**. Einen Build- und Start-Befehl musst du nicht eintragen.
6. Klicke auf **Deploy Web Service**. Nach dem Start zeigt Render eine öffentliche Adresse ähnlich wie `https://meine-website.onrender.com`.

Diese Adresse kannst du auf jedem Handy oder Windows-Computer öffnen. Bei einem kostenlosen Web-Service kann der erste Aufruf nach einer Pause etwas länger dauern.

Vor der Veröffentlichung: E-Mail-Adresse und Telefonnummer auf der Seite sind dann weltweit sichtbar.
