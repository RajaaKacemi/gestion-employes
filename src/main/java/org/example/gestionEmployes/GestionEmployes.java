package org.example.gestionEmployes;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class GestionEmployes {
    static int max_size = 50;
    static Employe[] employes = new Employe[max_size];

    static void printMenu() {
        System.out.println("1. Ajouter un employe ");
        System.out.println("2. Modifier un employe ");
        System.out.println("3. Supprimer un employe ");
        System.out.println("4. Afficher les employes ");
        System.out.println("5. rechercher un employe ");
        System.out.println("6. Calculer le total des salaires ");
        System.out.println("7. Trier les emoloyes par rapport aux salaire");
        System.out.println("8. Quitter");
    }
    static void ajouterEmploye(Employe employe){
        for(int i = 0; i < employes.length; i++){
            if(employes[i] != null && employes[i].getId() == employe.getId()){
                System.out.println("Erreur: Un employe avec ce id existe déja.");
                return;
            }
        }
        for(int i = 0; i < employes.length; i++) {
            if(employes[i] == null){
                employes[i] = employe;
                System.out.println("Employe ajouté avec succès.");
                return;
            }
        }
        System.out.println("Error: Tableau plein");
    }

    static void modifierEmploye(int id, String nouveauNom, String nouveauPoste, double nouveauSalaire){
        for(int i = 0; i < employes.length; i++){
            if(employes[i] != null && employes[i].getId() == id){
                employes[i].setNom(nouveauNom);
                employes[i].setPoste(nouveauPoste);
                employes[i].setSalaire(nouveauSalaire);
                System.out.println("Employe modifié avec succès.");
                return;
            }
        }
        System.out.println("Erreur: Employe n'exite pas.");
    }

    static void supprimerEmploye(int id){
        for(int i = 0; i < employes.length; i++){
            if(employes[i] != null && employes[i].getId() == id){
                employes[i] = null;
                System.out.println("Employe supprimé avec succès.");
                return;
            }
        }
        System.out.println("Error: Employe n'existe pas.");
    }

    static void afficherEmployes(){
        boolean found = false;
        for(int i = 0; i < employes.length; i++){
            if(employes[i] != null){
                System.out.println(employes[i].toString());
                found = true;
            }
        }
        if(!found){
            System.out.println("Aucun employe.");
        }
    }

    static void rechercherEmploye(String critere){
        boolean found = false;
        for(int i = 0; i < employes.length; i++){
            if(employes[i].getNom().equals(critere) || employes[i].getPoste().equals(critere)){
                System.out.println(employes[i].toString());
                found = true;
            }
        }
        if(!found){
            System.out.println("Employe n'existe pas.");
        }
    }

    static double calculerMasseSalariale(){
        double masse = 0;
        for(int i = 0; i < employes.length; i++){
            if(employes[i] != null){
                masse += employes[i].getSalaire();
            }
        }
        return masse;
    }

    static void trierEmployesParSalaire(boolean ordreCroissant){
        Employe[] resultes = new Employe[max_size];
        if(ordreCroissant){
        for(int i = 1; i < employes.length; i++){
            if(employes[i].getSalaire() < employes[i-1].getSalaire() ){
                resultes[i-1] = employes[i];
            }
        }
            System.out.println("les employes triés dans l'odre croissant: ");
            for(int i = 0; i < resultes.length; i++){
                System.out.println(resultes[i].toString());
            }
        }else if(!ordreCroissant){
            for(int i = 1; i < employes.length; i++){
                if(employes[i].getSalaire() > employes[i-1].getSalaire() ){
                    resultes[i-1] = employes[i];
                }
            }
            System.out.println("les employes triés dans l'odre décroissant: ");
            for(int i = 0; i < resultes.length; i++){
                System.out.println(resultes[i].toString());
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int choix;

        // Declare variables outside the switch statement
        int id;
        String nom, poste;
        double salaire;

        do {
            printMenu();
            System.out.println("Entrez votre choix: ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    System.out.println("*** Ajouter un employe ***");
                    System.out.println("ID: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Nom: ");
                    nom = sc.nextLine();
                    System.out.println("Poste: ");
                    poste = sc.nextLine();
                    System.out.println("Salaire: ");
                    salaire = sc.nextDouble();
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
                    System.out.println("La liste des employes: ");
                    afficherEmployes();
                    break;
                case 5:
                    System.out.println("*** Rechercher un employe ***");
                    System.out.println("Saisir une critère pour chercher un employe (nom, poste). : ");
                    String critere = sc.nextLine();
                    rechercherEmploye(critere);
                    break;
                case 6:
                    System.out.println("*** Total de salaires ***");
                    double totale = calculerMasseSalariale();
                    System.out.println("Total de salaires : " + totale);
                    break;
                case 7:
                    trierEmployesParSalaire(false);
                    break;
                case 8:
                    System.out.println("Quitter le programme");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 8);

        sc.close(); // Close the scanner
    }
}
