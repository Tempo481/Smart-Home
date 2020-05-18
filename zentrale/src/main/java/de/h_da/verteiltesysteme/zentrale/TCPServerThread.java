package de.h_da.verteiltesysteme.zentrale;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServerThread extends Thread implements SensorData {

    public TCPServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            receiveDataFromClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Die Anfrage des Clients wird zeilenweise eingelesen in die ArrayList clientMessage bis Zeilenende
    * Dann wird die Funktion respondToClient aufgerufen, um die Anfrage zu interpretieren und zu bearbeiten
    */
    public static void receiveDataFromClient() throws IOException {
        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String line = reader.readLine();
        while( !line.isEmpty() ) {
            clientMessage.add(line);
//            System.out.println(line);
            line = reader.readLine();
        }
        respondToClient();
        clientMessage.clear();
        socket.close();
    }

    /*
     * Es sind 7 URIs definiert für 7 verschiedene GET Anfragen
     * Es wird entsprechend mittels switch/case untersucht, welche Anfrage der Client fordert
     * und entsprechend wird geantwortet.
     * Am ende des Quelltextes dieser Klasse steht eine beispielhafte GET Anfrage.
     */
    private static void respondToClient() throws IOException {
        outToClient = new DataOutputStream(socket.getOutputStream());

        if (! clientMessage.isEmpty()) {
            /*
             * Die erste Zeile, d.h. die GET Anfrage auslesen
             */
            switch (clientMessage.get(0)) {
                case URIForIndex:
                    printIndexPage();
                    break;
                case URIForAllBrightnessData:
                    printHistoryOfSensorData("Helligkeit");
                    break;
                case URIForAllTemperatureData:
                    printHistoryOfSensorData("Temperatur");
                    break;
                case URIForAllWindSpeedData:
                    printHistoryOfSensorData("Wind");
                    break;
                case URIForAllRainfallData:
                    printHistoryOfSensorData("Niederschlag");
                    break;
                case URIForRecentBrightnessData:
                    printSingleSensorData("Helligkeit");
                    break;
                case URIForRecentTemperatureData:
                    printSingleSensorData("Temperatur");
                    break;
                case URIForRecentWindSpeedData:
                    printSingleSensorData("Wind");
                    break;
                case URIForRecentRainfallData:
                    printSingleSensorData("Niederschlag");
                    break;
                case URIForAllData:
                    printAllSensorData();
                    break;
                default:
                    printBadRequestResponse();
            }
        }
    }

    static void printAllSensorData() throws IOException {
        outToClient.writeBytes(htmlResponseCodeOk);
        outToClient.writeBytes(htmlContentType);
        outToClient.writeBytes("\r\n");

        outToClient.writeBytes(HTML_START);

        for(Temperature t : TEMPERATURE_SENSOR_DATA) {
            outToClient.writeBytes(t.toString());
            outToClient.writeBytes("<br>");
        }

        for(Rainfall n : RAINFALL_SENSOR_DATA) {
            outToClient.writeBytes(n.toString());
            outToClient.writeBytes("<br>");
        }

        for(Brightness b : BRIGHTNESS_SENSOR_DATA) {
            outToClient.writeBytes(b.toString());
            outToClient.writeBytes("<br>");
        }

        for(Wind w : WINDSPEED_SENSOR_DATA) {
            outToClient.writeBytes(w.toString());
            outToClient.writeBytes("<br>");
        }

        outToClient.writeBytes(HTML_END);
        outToClient.close();
    }

    static void printBadRequestResponse() throws IOException {
        outToClient.writeBytes(htmlResponseCodeBadRequest);
        outToClient.writeBytes(htmlContentType);
        outToClient.writeBytes("\r\n");
        outToClient.close();
    }

    static void printIndexPage() throws IOException {
        outToClient.writeBytes(htmlResponseCodeOk);
        outToClient.writeBytes(htmlContentType);
        outToClient.writeBytes("\r\n");
        outToClient.writeBytes(HTML_INDEX);
        outToClient.close();
    }

    static void printHistoryOfSensorData(String typeofSensor) throws IOException {
        outToClient.writeBytes(htmlResponseCodeOk);
        outToClient.writeBytes(htmlContentType);
        outToClient.writeBytes("\r\n");

        switch (typeofSensor) {
            case "Temperatur":
                outToClient.writeBytes(HTML_START);
                for(Temperature t : TEMPERATURE_SENSOR_DATA) {
                    outToClient.writeBytes(t.toString());
                    outToClient.writeBytes("<br>");
                }
                outToClient.writeBytes(HTML_END);
                outToClient.close();
                break;
            case "Niederschlag":
                outToClient.writeBytes(HTML_START);
                for(Rainfall r : RAINFALL_SENSOR_DATA) {
                    outToClient.writeBytes(r.toString());
                    outToClient.writeBytes("<br>");
                }
                outToClient.writeBytes(HTML_END);
                outToClient.close();
                break;
            case "Helligkeit":
                outToClient.writeBytes(HTML_START);
                for(Brightness b : BRIGHTNESS_SENSOR_DATA) {
                    outToClient.writeBytes(b.toString());
                    outToClient.writeBytes("<br>");
                }
                outToClient.writeBytes(HTML_END);
                outToClient.close();
            case "Wind":
                outToClient.writeBytes(HTML_START);
                for(Wind w : WINDSPEED_SENSOR_DATA) {
                    outToClient.writeBytes(w.toString());
                    outToClient.writeBytes("<br>");
                }
                outToClient.writeBytes(HTML_END);
                outToClient.close();
            default:
                outToClient.close();
        }
    }

    static void printSingleSensorData(String typeofSensor) throws IOException {
        outToClient.writeBytes(htmlResponseCodeOk);
        outToClient.writeBytes(htmlContentType);
        outToClient.writeBytes("\r\n");

        outToClient.writeBytes(HTML_START);
        switch (typeofSensor) {
            case "Temperatur":
                outToClient.writeBytes(TEMPERATURE_SENSOR_DATA.get(TEMPERATURE_SENSOR_DATA.size()).toString());
                outToClient.writeBytes(HTML_END);
                outToClient.close();
                break;
            case "Niederschlag":
                outToClient.writeBytes(RAINFALL_SENSOR_DATA.get(RAINFALL_SENSOR_DATA.size()).toString());
                outToClient.writeBytes(HTML_END);
                outToClient.close();
                break;
            case "Helligkeit":
                outToClient.writeBytes(BRIGHTNESS_SENSOR_DATA.get(BRIGHTNESS_SENSOR_DATA.size()).toString());
                outToClient.writeBytes(HTML_END);
                outToClient.close();
                break;
            case "Wind":
                outToClient.writeBytes(WINDSPEED_SENSOR_DATA.get(WINDSPEED_SENSOR_DATA.size()).toString());
                outToClient.writeBytes(HTML_END);
                outToClient.close();
                break;
            default:
                outToClient.writeBytes(HTML_END);
                outToClient.close();
        }
    }

    private static ArrayList<String> clientMessage = new ArrayList<>();
    private static Socket socket;
    private static BufferedReader inFromClient = null;
    private static DataOutputStream outToClient = null;

    private static String htmlResponseCodeOk = "HTTP/1.1 200 Ok" + "\r\n";
    private static String htmlResponseCodeBadRequest = "HTTP/1.1 400 Bad Request" + "\r\n";
    private static String htmlContentType = "Content-Type: text/html" + "\r\n";
    private static String htmlContentLength = "Content-length: ";

    private static final String URIForIndex = "GET /index/ HTTP/1.1";

    private static final String URIForAllTemperatureData = "GET /TemperatureHistory/ HTTP/1.1";
    private static final String URIForAllWindSpeedData = "GET /WindHistory/ HTTP/1.1";
    private static final String URIForAllBrightnessData = "GET /BrightnessHistory/ HTTP/1.1";
    private static final String URIForAllRainfallData = "GET /RainfallHistory/ HTTP/1.1";

    private static final String URIForRecentTemperatureData = "GET /Temperature/ HTTP/1.1";
    private static final String URIForRecentWindSpeedData = "GET /Wind/ HTTP/1.1";
    private static final String URIForRecentBrightnessData = "GET /Brightness/ HTTP/1.1";
    private static final String URIForRecentRainfallData = "GET /Rainfall/ HTTP/1.1";

    private static final String URIForAllData = "GET /AllSensorData/ HTTP/1.1";

    private static final String HTML_START = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<body>\n" +
            "\n" +
            "<h1>Sensorwert:</h1>";

    private static final String HTML_END = "</body>\n" +
            "</html>";

    private static final String HTML_INDEX = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<body>\n" +
            "\n" +
            "<h2>Index Sensordaten</h2>\n" +
            "\n" +
            "<h3>Alle Daten von allen Sensoren:</h3>\n" +
            "<p>\n" +
            "    <a href=\"http://localhost:80/AllSensorData/\">Alle Sensordaten</a>\n" +
            "</p>\n" +
            "\n" +
            "<h3>Alle Daten eines Sensors (Historie):</h3>\n" +
            "<p>\n" +
            "    <a href=\"http://localhost:80/TemperatureHistory/\">Alle Temperaturdaten</a>\n" +
            "</p>\n" +
            "<p>\n" +
            "    <a href=\"http://localhost:80/WindHistory/\">Alle Windgeschwindigkeitsdaten</a>\n" +
            "</p>\n" +
            "<p>\n" +
            "    <a href=\"http://localhost/BrightnessHistory/\">Alle Helligkeitsdaten</a>\n" +
            "</p>\n" +
            "<p>\n" +
            "    <a href=\"http://localhost/RainfallHistory/\">Alle Niederschlagsdaten</a>\n" +
            "</p>\n" +
            "\n" +
            "<h3>Aktuellste Sensorwerte:</h3>\n" +
            "<p>\n" +
            "    <a href=\"http://localhost:80/Temperature/\">Temperatur</a>\n" +
            "</p>\n" +
            "<p>\n" +
            "    <a href=\"http://localhost:80/Wind/\">Windgeschwindigkeit</a>\n" +
            "</p>\n" +
            "<p>\n" +
            "    <a href=\"http://localhost:80/Brightness/\">Helligkeit</a>\n" +
            "</p>\n" +
            "<p>\n" +
            "    <a href=\"http://localhost:80/Rainfall/\">Niederschlag</a>\n" +
            "</p>\n" +
            "\n" +
            "</body>\n" +
            "</html>";
}

//    GET /test/ HTTP/1.1
//    Host: localhost:6868
//    Connection: keep-alive
//    Upgrade-Insecure-Requests: 1
//    User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36
//    Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3
//Sec-Fetch-Site: none
//Sec-Fetch-Mode: navigate
//Accept-Encoding: gzip, deflate, br
//Accept-Language: de-DE,de;q=0.9,en-US;q=0.8,en;q=0.7