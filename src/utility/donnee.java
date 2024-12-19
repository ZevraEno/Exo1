package utility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class donnee {

    private static final String donner = "Exodonne";

    public static void sauvegarder(List<String> participants) {
        try (ObjectOutputStream S = new ObjectOutputStream(new FileOutputStream(donner))) {
            S.writeObject(participants);
            System.out.println("sauvegardés");
        } catch (IOException e) {
            System.out.println("erreur !" + e.getMessage());
        }
    }

    public static List<String> charger() {
        try (ObjectInputStream objdonne = new ObjectInputStream(new FileInputStream(donner))) {
            return (List<String>) objdonne.readObject();
        }catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé");
            sauvegarder(new ArrayList<>());
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur !" + e.getMessage());
            return new ArrayList<>();
        }
    }
}
