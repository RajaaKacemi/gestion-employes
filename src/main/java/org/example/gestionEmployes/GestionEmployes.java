package org.example.gestionEmployes;

import java.util.Scanner;

public class GestionEmployes {
    static int max_size = 50;
    static Employe[] employes = new Employe[max_size];
    static int nombreEmployes = 0; // Pour suivre le nombre d’employés ajoutés

    // function printMenu pour afficher pour l'utilisateur
    static void printMenu() {
        System.out.println("1. Ajouter un employe ");
        System.out.println("2. Modifier un employe ");
        System.out.println("3. Supprimer un employe ");
        System.out.println("4. Afficher les employes ");
        System.out.println("5. Rechercher un employe ");
        System.out.println("6. Calculer le total des salaires ");
        System.out.println("7. Trier les employes par rapport aux salaire");
        System.out.println("8. Quitter");
    }

    // function pour ajouter un employe
    static void ajouterEmploye(Employe employe) {
        if (nombreEmployes >= max_size) {
            System.out.println("Erreur: Tableau plein.");
            return;
        }

        for (int i = 0; i < nombreEmployes; i++) {
            if (employes[i].getId() == employe.getId()) {
                System.out.println("Erreur: Un employe avec cet ID existe déjà.");
                return;
            }
        }

        employes[nombreEmployes++] = employe;
        System.out.println("Employe ajouté avec succès.");
    }

    //function pour modifier un employe d'après son ID
    static void modifierEmploye(int id, String nouveauNom, String nouveauPoste, double nouveauSalaire) {
        for (int i = 0; i < nombreEmployes; i++) {
            if (employes[i].getId() == id) {
                employes[i].setNom(nouveauNom);
                employes[i].setPoste(nouveauPoste);
                employes[i].setSalaire(nouveauSalaire);
                System.out.println("Employe modifié avec succès.");
                return;
            }
        }
        System.out.println("Erreur: Employe n'existe pas.");
    }

    //function pour supprimer un employe d'après son ID
    static void supprimerEmploye(int id) {
        for (int i = 0; i < nombreEmployes; i++) {
            if (employes[i].getId() == id) {
                // Déplacer les éléments vers la gauche afin de réduire l'écart
                for (int j = i; j < nombreEmployes - 1; j++) {
                    employes[j] = employes[j + 1];
                }
                employes[--nombreEmployes] = null; // Pour vider la dernière case du tableau
                System.out.println("Employe supprimé avec succès.");
                return;
            }
        }
        System.out.println("Erreur: Employe n'existe pas.");
    }
    // function pour afficher la liste des employes
    static void afficherEmployes() {
        if (nombreEmployes == 0) {
            System.out.println("Aucun employe trouvé.");
            return;
        }

        for (int i = 0; i < nombreEmployes; i++) {
            System.out.println(employes[i].toString());
        }
    }
    // function pour rechercher un employe d'après sans post ou nom
    static void rechercherEmploye(String critere) {
        boolean found = false;
        for (int i = 0; i < nombreEmployes; i++) {
            // en utilise equalsIgnoreCase pour comparer les string en ignorant les caractères majuscule et miniscule
            if (employes[i].getNom().equalsIgnoreCase(critere) || employes[i].getPoste().equalsIgnoreCase(critere)) {
                System.out.println(employes[i].toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Employe n'existe pas.");
        }
    }
    // function pour Calculer la somme des salaires des employés présents dans le tableau
    static double calculerMasseSalariale() {
        double masse = 0;
        for (int i = 0; i < nombreEmployes; i++) {
            masse += employes[i].getSalaire();
        }
        return masse;
    }
    //Trier et afficher les employés par salaire dans l’ordre choisi (croissant ou décroissant).
    static void trierEmployesParSalaire(boolean ordreCroissant) {
        if (nombreEmployes == 0) {
            System.out.println("Aucun employe à trier.");
            return;
        }

        // Utilisation du tri par bulles avec la méthode compareParSalaire
        for (int i = 0; i < nombreEmployes - 1; i++) {
            for (int j = 0; j < nombreEmployes - i - 1; j++) {
                int comparison = Employe.compareParSalaire(employes[j], employes[j + 1]);
                boolean condition = ordreCroissant ? comparison > 0 : comparison < 0;

                if (condition) {
                    // Swap employes[j] and employes[j + 1]
                    Employe temp = employes[j];
                    employes[j] = employes[j + 1];
                    employes[j + 1] = temp;
                }
            }
        }

        // Affichage des employes triées
        System.out.println("Employes triés par salaire (" + (ordreCroissant ? "croissant" : "décroissant") + ") :");
        afficherEmployes();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choix;

        do {
            printMenu();
            System.out.println("Entrez votre choix: ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    System.out.println("*** Ajouter un employe ***");
                    System.out.println("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Nom: ");
                    String nom = sc.nextLine();
                    System.out.println("Poste: ");
                    String poste = sc.nextLine();
                    System.out.println("Salaire: ");
                    double salaire = sc.nextDouble();
                    ajouterEmploye(new Employe(id, nom, poste, salaire));
                    break;
                case 2:
                    System.out.println("*** Modifier un employe ***");
                    System.out.println("ID: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Nouveau Nom: ");
                    nom = sc.nextLine();
                    System.out.println("Nouveau Poste: ");
                    poste = sc.nextLine();
                    System.out.println("Nouveau Salaire: ");
                    salaire = sc.nextDouble();
                    modifierEmploye(id, nom, poste, salaire);
                    break;
                case 3:
                    System.out.println("*** Supprimer un employe ***");
                    System.out.println("ID: ");
                    id = sc.nextInt();
                    supprimerEmploye(id);
                    break;
                case 4:
                    System.out.println("*** Liste des employes ***");
                    afficherEmployes();
                    break;
                case 5:
                    System.out.println("*** Rechercher un employe ***");
                    System.out.println("Saisir un critère pour chercher un employe (nom, poste): ");
                    String critere = sc.nextLine();
                    rechercherEmploye(critere);
                    break;
                case 6:
                    System.out.println("*** Total des salaires ***");
                    double totale = calculerMasseSalariale();
                    System.out.println("Total des salaires : " + totale);
                    break;
                case 7:
                    System.out.println("*** Trier les employes par salaire ***");
                    System.out.println("1. Croissant");
                    System.out.println("2. Décroissant");
                    int triChoix = sc.nextInt();
                    trierEmployesParSalaire(triChoix == 1);
                    break;
                case 8:
                    System.out.println("Quitter le programme");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 8);

        sc.close();
    }
}