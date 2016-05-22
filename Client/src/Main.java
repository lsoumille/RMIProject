import demormi.ClientDemoRMI;
import purchasingcars.ClientPurchaseCars;
import synonym.ClientSynonym;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * @author Lucas Martinez
 * @version 22/05/16.
 */
public class Main {

    public static void main(String[] args) {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void init() throws RemoteException, InterruptedException {
        System.out.println("Choisissez le client que vous voulez lancer.");
        System.out.println("Tapez n'importe quoi d'autre pour quitter.");
        System.out.println("1. ClientDemoRMI");
        System.out.println("2. ClientSynonym");
        System.out.println("3. ClientPurchaseCars");

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nChoix: ");

        while (scanner.hasNext()){
            String choice = scanner.nextLine();

            if (choice.equals("1")){
                new ClientDemoRMI().start();
            } else if (choice.equals("2")){
                new ClientSynonym().start();
            } else if (choice.equals("3")){
                new ClientPurchaseCars(true).start();
            } else {
                System.out.println("Au revoir.");
                return;
            }
            System.out.println("\nChoix: ");
        }

    }
}
