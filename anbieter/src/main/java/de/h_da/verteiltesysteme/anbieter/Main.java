package de.h_da.verteiltesysteme.anbieter;

public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            System.out.println(String.format("Anbieter funktioniert %s", i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
