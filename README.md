# Verteilte Systeme Praktikum SS20/21
### Allgemein:
Bearbeiter:
- Matthias Adrian  (Mtrk. 752237)
- Jan Zipprich     (Mtrk. 757956)

Version: 2 ([Milestone C](https://code.fbi.h-da.de/istjazipp/verteilte_systeme_mo5y-3/-/milestones/3) | Abgabe 15.06.20)

### Systemanforderung:

- [docker](https://docs.docker.com/get-docker/)
- [docker-compose](https://docs.docker.com/compose/install/)

### Aufbau des Repository:
- start.cmd: Zum starten der Dockerumgebung
- build.cmd: Zum neubauen der Container (wenn Dateien geändert wurden)
- docker-compose: Informationen wie die Umgebung Aufgesetzt werden soll
- /anbieter: Alle Anbieter Daten
- /sensor: Alle Sensor(Programm) Daten
- /zentrale: Alle Zentrale(Programm) Daten

### Container die gestartet werden:
- sensor_(wind,temperatur,niederschlag,helligkeit): Jeweiliger Sensor
  - Jeweilge ENV Variablen:
    - SENSOR_NAME: Name des Sensors
    - SENSOR_TYPE: Typ des Senors (Helligkeit, Temperatur, Niederschlag, Wind)
    - ZENTRALE_IP: IP-Addresse der Zentrale
    - ZENTRALE_PORT: PORT der Zentrale
  - IP-Addr.: 172.20.0.11-14
- zentrale: Zentrale
  - IP-Addr.: 172.20.0.2
  - Ports: 50000 für UDP (SensorDaten) & 80 für TCP (Weboberfläche)
  - Webschnittstelle: http://127.0.0.1:80
- anbieter: Anbieter
  - IP-Addr.: 172.20.0.3
  - Environment:
    - DB_USERNAME: Username für die MongoDB
	- DB_PASSWORD: Password für die MongoDB
	- DB_HOST: IP für die MongoDB
	- DB_PORT: Port für die MongoDB
- [portainer](https://www.portainer.io/): Weboberfläche um ua die Logs und Status 
der Container zu sehen
  - IP-Addr.: 172.20.0.10
  - Webschnittstelle: http://127.0.0.1:81
- mongodb: Datenbank
  - IP-Addr.: 172.20.0.20
- [mongoku](https://github.com/huggingface/Mongoku): Simples Datenbank Visualisierungstool
  - IP-Addr.: 172.20.0.9
  - Webschnittstelle: http://127.0.0.1:82
