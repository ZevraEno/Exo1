package Inscription;

import ExceptionInscription.doublonException;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class VueInscription extends participant{

    public static void ajouter() throws doublonException {
        Scanner scanner = new Scanner(System.in);
        boolean entrer = true;

        while (entrer) {
            doublon = false;
            System.out.print("prénom: ");
            String prenom = scanner.nextLine();
            System.out.print("nom: ");
            String nom = scanner.nextLine();
            System.out.print("email: ");
            String email;
            while (true) {
                email = scanner.nextLine();
                if (email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                    break;
                } else {
                    System.out.println("réessayer.");
                }
            }
            String club = choisirClub();
            for (String participantInfo : listeParticipant) {
                if (participantInfo.contains("prenom : " + prenom) && participantInfo.contains("nom : " + nom)) {
                    doublon = true;
                    break;
                }
            }
            if (doublon) {
                throw new doublonException("Erreur404");
            } else {
                String participantInfo = "prenom : " + prenom + " | nom : " + nom + " | email: " + email + " | club: " + club;
                listeParticipant.add(participantInfo);
                System.out.println("Participant ajouté : " + participantInfo);
            }
            System.out.print("Continuer ? (oui/non) : ");
            String stop = scanner.nextLine();
            if (stop.equalsIgnoreCase("non")) {
                entrer = false;
            }
        }
    }

    public static void modifier() {
        Scanner scanner = new Scanner(System.in);
        if (listeParticipant.isEmpty()) {
            System.out.println("Liste vide. ajouter ?");
            return;
        }

        System.out.print("Entrez le prénom à modifier : ");
        String prenomRecherche = scanner.nextLine();
        System.out.print("Entrez le nom à modifier : ");
        String nomRecherche = scanner.nextLine();

        boolean participantTrouve = false;
        for (int i = 0; i < listeParticipant.size(); i++) {
            String participantInfo = listeParticipant.get(i);
            if (participantInfo.contains("prenom : " + prenomRecherche) && participantInfo.contains("nom : " + nomRecherche)) {
                participantTrouve = true;
                System.out.println("Participant trouvé : " + participantInfo);
                System.out.println("Que souhaitez-vous modifier ?");
                System.out.println("1) Prénom");
                System.out.println("2) Nom");
                System.out.println("3) Email");
                System.out.println("4) Club");
                System.out.println("5) Annuler");
                String choix = scanner.nextLine();
                String[] infos = participantInfo.split(" \\| ");
                String nouveauPrenom = infos[0].split(" : ")[1];
                String nouveauNom = infos[1].split(" : ")[1];
                String nouvelEmail = infos[2].split(" : ")[1];
                String nouveauClub = infos[3].split(" : ")[1];
                switch (choix) {
                    case "1":
                        System.out.print("entrez le nouveau prénom : ");
                        nouveauPrenom = scanner.nextLine();
                        break;
                    case "2":
                        System.out.print("entrez le nouveau nom : ");
                        nouveauNom = scanner.nextLine();
                        break;
                    case "3":
                        while (true) {
                            System.out.print("entrez le nouvel email : ");
                            nouvelEmail = scanner.nextLine();
                            if (nouvelEmail.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                                break;
                            } else {
                                System.out.println("Erreur :format '@gmail.com'.");
                            }
                        }
                        break;
                    case "4":
                        System.out.println("clubs disponibles :");
                        for (int j = 0; j < listeClubs.size(); j++) {
                            System.out.println((j + 1) + ") " + listeClubs.get(j));
                        }
                        System.out.println((listeClubs.size() + 1) + ") nouveau club");
                        System.out.print("choix : ");
                        int choixClub = scanner.nextInt();
                        scanner.nextLine();

                        if (choixClub == listeClubs.size() + 1) {
                            System.out.print("entrez le nom du nouveau club : ");
                            nouveauClub = scanner.nextLine();
                            listeClubs.add(nouveauClub);
                            System.out.println("club ajouté : " + nouveauClub);
                        } else if (choixClub > 0 && choixClub <= listeClubs.size()) {
                            nouveauClub = listeClubs.get(choixClub - 1);
                        } else {
                            System.out.println("Modification annulée.");
                            return;
                        }
                        break;
                    case "5":
                        System.out.println("modification annulée.");
                        return;
                    default:
                        System.out.println("modification annulée.");
                        return;
                }
                String nouveauParticipantInfo = "prenom : " + nouveauPrenom + " | nom : " + nouveauNom + " | email: " + nouvelEmail + " | club: " + nouveauClub;
                listeParticipant.set(i, nouveauParticipantInfo);
                System.out.println("Participant modifié : " + nouveauParticipantInfo);
                break;
            }
        }
        if (!participantTrouve) {
            System.out.println("Participant non trouvé.");
        }
    }

    public static void supprimer() {
        Scanner scanner = new Scanner(System.in);

        if (listeParticipant.isEmpty()) {
            System.out.println("Liste vide.");
            return;
        }
        System.out.print("prénom du participant a supprimer : ");
        String prenomRecherche = scanner.nextLine();
        System.out.print("nom du participant aà supprimer : ");
        String nomRecherche = scanner.nextLine();

        boolean participantTrouve = false;
        for (int i = 0; i < listeParticipant.size(); i++) {
            String participantInfo = listeParticipant.get(i);
            if (participantInfo.contains("prenom : " + prenomRecherche) && participantInfo.contains("nom : " + nomRecherche)) {
                participantTrouve = true;
                System.out.println("Participant trouvé : " + participantInfo);
                System.out.print("Supprimer ce participant ? (oui/non) : ");
                String confirmation = scanner.nextLine();
                if (confirmation.equalsIgnoreCase("oui")) {
                    listeParticipant.remove(i);
                    System.out.println("Participant supprimé.");
                } else {
                    System.out.println("Suppression annulée.");
                }
                break;
            }
        }
        if (!participantTrouve) {
            System.out.println("Participant non trouvé");
        }
    }

    public static void liste() {
        if (listeParticipant.isEmpty()) {
            System.out.println("Liste des participants vide.");
            return;
        }
        System.out.println("Liste des participants :");
        for (int i = 0; i < listeParticipant.size(); i++) {
            System.out.println((i + 1) + ") " + listeParticipant.get(i));
        }
    }

    private static String choisirClub() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("club disponibles :");
            for (int i = 0; i < listeClubs.size(); i++) {
                System.out.println((i + 1) + ") " + listeClubs.get(i));
            }
            System.out.println((listeClubs.size() + 1) + ") Ajouter un club");

            System.out.print("Choisir ou ajoutez-en un club (1-" + (listeClubs.size() + 1) + ") : ");
            String choix = scanner.nextLine();

            try {
                int choixInt = Integer.parseInt(choix);
                if (choixInt > 0 && choixInt <= listeClubs.size()) {
                    return listeClubs.get(choixInt - 1);
                } else if (choixInt == listeClubs.size() + 1) {
                    System.out.print("nouveau club : ");
                    String nouveauClub = scanner.nextLine();
                    listeClubs.add(nouveauClub);
                    System.out.println("ajouté : " + nouveauClub);
                    return nouveauClub;
                } else {
                    System.out.println("réessayer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("entrer un nombre valide.");
            }
        }
    }

}
