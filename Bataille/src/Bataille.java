import java.util.ArrayList;
import java.util.Scanner;


public class Bataille {
	
	public static void main(String[] args) {

		
		new Joueur("Dalinda");
		new Joueur("Dadou"); 
		
		Pioche pioche = new Pioche();

        pioche.distribuerCartes(Joueur.getJoueurs());

        try (Scanner enter = new Scanner(System.in)) {
			Joueur joueurGagnant = null;
			ArrayList<Carte> cartes = new ArrayList<Carte>();
			ArrayList<Joueur> joueursEgaux = new ArrayList<Joueur>();
			ArrayList<Joueur> joueurs = new ArrayList<Joueur>();          //Liste des joueurs

			while(!Joueur.vainceurExiste()) {

			    if(!joueursEgaux.isEmpty()) {
			        joueurs = joueursEgaux;
			        joueursEgaux = new ArrayList<Joueur>();
			    }
			    else {
			        joueurs = Joueur.getJoueurs();
			    }
			    for(Joueur joueur: joueurs) {
			        System.out.println(joueur.devoilerCarte() + " pour " + joueur.toString());
			        
			        if(joueurGagnant != null) {
			           
			            if(joueur.comparer(joueurGagnant)) {
			        
			                if(joueursEgaux.isEmpty()) cartes.add(joueurGagnant.enleverCartes());
			                joueurGagnant = joueur;
			            }

			            else if(joueur.egales(joueurGagnant)) {

			                if(!joueursEgaux.contains(joueurGagnant)) {
			                    joueursEgaux.add(joueurGagnant);
			                   
			                    if(!joueurGagnant.quitteOuDouble()) cartes.add(joueurGagnant.enleverCartes());
			                }
			                joueursEgaux.add(joueur);
			                
			                if(!joueur.quitteOuDouble()) cartes.add(joueur.enleverCartes());
			            }
			            else {
			                if(joueursEgaux.isEmpty()) cartes.add(joueur.enleverCartes());
			            }
			        }
			        else {
			            joueurGagnant = joueur;
			        }
			    }


			    if(!joueursEgaux.isEmpty()) {
			        for(Joueur joueur : joueursEgaux) {
			            if(!joueur.quitteOuDouble()) {
			                cartes.add(joueur.enleverCartes());
			            }
			        }
			        System.out.println("Egalité !");
			    }
			    else {
			        joueurGagnant.nouvellesCartes(cartes);
			        System.out.println("Gagnant : " + joueurGagnant);
			        cartes = new ArrayList<Carte>();
			    }
			    System.out.println("\nAppuyez sur Entrée pour continuer...");
			    joueurGagnant = null;
			    enter.nextLine();
			}
		}
        System.out.println(Joueur.getVainceur() + " a gagné la partie !");


    }

}