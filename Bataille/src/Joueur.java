import java.util.ArrayList;

public class Joueur {

	private static ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private ArrayList<Carte> cartes = new ArrayList<Carte>();
    private String nom;

	
	public Joueur(String nom) {
        this.nom = nom;
        joueurs.add(this);
    }
	
	// Insérer une nouvelle carte dans le jeu
	public void nouvelleCarte(Carte carte) {							
		cartes.add(carte);
	}
	
	// Insérer des nouvelles cartes dans le jeu
	public void nouvellesCartes(ArrayList<Carte> cartes) {
		for (Carte carte: cartes) {
			this.nouvelleCarte(carte);
		}
		// Mettre la carte qui a permis de gagner en dernier
		Carte carte = this.cartes.get(0);
		this.cartes.remove(0);
		this.nouvelleCarte(carte);
	}
	
	//Retourne la première carte du paquet
	public Carte devoilerCarte() {
		return cartes.get(0);
	}
	
	//Enlève la première carte du paquet
	public Carte enleverCartes() {
		Carte carte = cartes.get(0);
		cartes.remove(0);
		return carte;
	}
	
	//Retourner la liste des joueurs
	public static ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

	// Vérifier si quelqu'un a les 52 cartes ou bien si quelqu'un a perdu
	public static boolean vainceurExiste() {
        int size;
        for(int i=0; i<joueurs.size(); i++) {
            size = joueurs.get(i).cartes.size();
            if(size == 52) return true;
            else if(size <= 0) joueurs.remove(i);
        }
        return false;
    }
	
	// Si le joueur n'a plus qu'une carte
	public boolean quitteOuDouble() {
        return cartes.size() <= 1;
    }

	// Comparer si un joueur a une carte plus forte qu'un autre joueur
    public boolean comparer(Joueur joueur) {
        return this.cartes.get(0).getValeur() > joueur.cartes.get(0).getValeur();
    }

    // Voir si deux joueurs ont la même carte
    public boolean egales(Joueur joueur) {
        return this.cartes.get(0).getValeur() == joueur.cartes.get(0).getValeur();
    }

    // Obtenir le seul joueur qui reste dans la liste
    public static String getVainceur() {
        return joueurs.get(0).nom;
    }
	
	@Override
    public String toString() {
        return nom + " (" + cartes.size() + " cartes)";
    }
}
