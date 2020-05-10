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
            System.out.println(line);
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
        OutputStream output = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        if (! clientMessage.isEmpty()) {

            switch (clientMessage.get(0)) {
                case URIForAllBrightnessData:
                    writer.println(clientMessage.get(2));
                    for(Brightness b : BRIGHTNESS_SENSOR_DATA) {
                        writer.println(b.toString());
                    }
                    break;

                case URIForAllTemperatureData:
                    writer.println(clientMessage.get(2));
                    for(Temperature t : TEMPERATURE_SENSOR_DATA) {
                        writer.println(t.toString());
                    }
                    break;

                case URIForAllWindSpeedData:
                    writer.println(clientMessage.get(2));
                    for(WindSpeed w : WINDSPEED_SENSOR_DATA) {
                        writer.println(w.toString());
                    }
                    break;

                case URIForAllData:
                    writer.println(clientMessage.get(2));
                    for(Brightness b : BRIGHTNESS_SENSOR_DATA) {
                        writer.println(b.toString());
                    }
                    for(Temperature t : TEMPERATURE_SENSOR_DATA) {
                        writer.println(t.toString());
                    }
                    for(WindSpeed w : WINDSPEED_SENSOR_DATA) {
                        writer.println(w.toString());
                    }
                    break;

                case URIForRecentBrightnessData:
                    writer.println(clientMessage.get(2));
                    writer.println(BRIGHTNESS_SENSOR_DATA.get(BRIGHTNESS_SENSOR_DATA.size()).toString());
                    break;

                case URIForRecentTemperatureData:
                    writer.println(clientMessage.get(2));
                    writer.println(TEMPERATURE_SENSOR_DATA.get(TEMPERATURE_SENSOR_DATA.size()).toString());
                    break;

                case URIForRecentWindSpeedData:
                    writer.println(clientMessage.get(2));
                    writer.println(WINDSPEED_SENSOR_DATA.get(WINDSPEED_SENSOR_DATA.size()).toString());
                    break;

                default:
                    writer.println("Default response ...");
            }
        }
    }


    private static ArrayList<String> clientMessage = new ArrayList<>();
    private static Socket socket;

    private static final String URIForAllTemperatureData = "GET /TemperatureAll/ HTTP/1.1";
    private static final String URIForAllWindSpeedData = "GET /WindSpeedAll/ HTTP/1.1";
    private static final String URIForAllBrightnessData = "GET /BrightnessAll/ HTTP/1.1";

    private static final String URIForRecentTemperatureData = "GET /Temperature/ HTTP/1.1";
    private static final String URIForRecentWindSpeedData = "GET /WindSpeed/ HTTP/1.1";
    private static final String URIForRecentBrightnessData = "GET /Brightness/ HTTP/1.1";

    private static final String URIForAllData = "GET /All/ HTTP/1.1";
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