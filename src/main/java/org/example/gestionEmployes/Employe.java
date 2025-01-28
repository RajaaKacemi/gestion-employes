package org.example.gestionEmployes;

public class Employe {
    private int id;
    private String nom;
    private String poste;
    private double salaire;

    public Employe() {
     super();
    }

    public Employe(int id, String poste, String nom, double salaire) {
        this.id = id;
        this.poste = poste;
        this.nom = nom;
        this.salaire = salaire;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPoste() {
        return poste;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ("Employe " + id + " " + nom + " " + poste + " " + salaire);
    }
}
