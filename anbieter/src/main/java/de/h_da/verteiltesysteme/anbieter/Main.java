package de.h_da.verteiltesysteme.anbieter;

import de.h_da.verteiltesysteme.anbieter.db.DatabaseTester;

public class Main {

    public static void main(String[] args) {

        (new DatabaseTester()).main();

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
