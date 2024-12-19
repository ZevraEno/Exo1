package principale;

import ExceptionInscription.doublonException;
import Inscription.VueInscription;
import Inscription.participant;
import utility.donnee;

import java.util.Scanner;

public class controller{

    public void start() throws doublonException {
        participant.listeParticipant = donnee.charger();
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            participant.liste();
            System.out.println("1) Ajouter");
            System.out.println("2) Modifier");
            System.out.println("3) Supprimer");
            System.out.println("4) Listes");
            System.out.println("5) Quitter");
            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    participant.ajouter();
                    break;
                case "2":
                    participant.modifier();
                    break;
                case "3":
                    participant.supprimer();
                    break;
                case "4":
                    participant.liste();
                    break;
                case "5":
                    donnee.sauvegarder(participant.listeParticipant);
                    System.out.println("Sayonara");
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix non valide.");
            }
        }
        scanner.close();
    }
}

