package de.h_da.verteiltesysteme.anbieter;

import de.h_da.verteiltesysteme.anbieter.db.DatabaseTester;
import de.h_da.verteiltesysteme.anbieter.thrift.ThriftClient;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ThriftClient thriftClient = new ThriftClient();
        Thread.sleep(1000);
        thriftClient.start();

//        (new DatabaseTester()).main();

//        for (int i = 0; i < 10; i++){
//            System.out.println(String.format("Anbieter funktioniert %s", i));
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }
}
