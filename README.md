# Verteilte Systeme Praktikum SS20/21
### Allgemein:
Bearbeiter:
- Matthias Adrian  (Mtrk. 752237)
- Jan Zipprich     (Mtrk. 757956)

Version: 2 ([Milestone B](https://code.fbi.h-da.de/istjazipp/verteilte_systeme_mo5y-3/-/milestones/2) | Abgabe 11.05.20)

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
- anbieter: Anbieter
  - IP-Addr.: 172.20.0.3
- [portainer](https://www.portainer.io/): Weboberfläche um ua die Logs und Status 
der Container zu sehen
  - IP-Addr.: 172.20.0.10
- mongodb: Datenbank
  - IP-Addr.: 172.20.0.20
