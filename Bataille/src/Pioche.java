import java.util.ArrayList;

public class Pioche {

    boolean[][] pioche = new boolean[4][13];

    public Pioche() {
        for(int i=0; i<4; i++) {
            for(int y=0; y<13; y++) {
                pioche[i][y] = true;
            }
        }
    }

    // Distribuer les cartes 
    public void distribuerCartes(ArrayList<Joueur> joueurs) {
        int cpt = 0;
        Carte carte;
        while (cpt <= 52) {
            for(Joueur joueur: joueurs) {
                carte = donnerCarte();
                if(carte != null) joueur.nouvelleCarte(carte);
                cpt++;
            }
        }
    }
    
    // Piocher une carte
    private Carte donnerCarte() {
        int randomFamille = (int)(Math.random() * (4-0)) + 0;
        Carte carte = piocherDansFamille(randomFamille);
        if(carte != null) {
            return carte;
        }
        carte = this.parcourirFamilles(randomFamille+1, 4);
        if(carte == null) return this.parcourirFamilles(0, randomFamille);
        return carte;
    }

    private Carte parcourirFamilles(int i, int limite) {
        Carte carte;
        for(; i<limite; i++) {
            carte = piocherDansFamille(i);
            if(carte != null) return carte;
        }
        return null;
    }

    // Piocher une carte aléatoire parmi une famille
    private Carte piocherDansFamille(int idFamille) {
        int randomCarte = (int)(Math.random() * (13-0)) + 0;
        if(pioche[idFamille][randomCarte]) {
            pioche[idFamille][randomCarte] = false;
            return new Carte(idFamille, randomCarte);
        }
        Carte carte = this.parcourirFamille(randomCarte+1, 13, idFamille);
        if(carte == null) return this.parcourirFamille(0, randomCarte, idFamille);
        return carte;
    }

    // Parcourir la famille par tranche pour voir si une carte est disponible dans la famille
    private Carte parcourirFamille(int i, int limite, int idFamille) {
        for(; i<limite; i++) {
            if(pioche[idFamille][i]) {
                pioche[idFamille][i] = false;
                return new Carte(idFamille, i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String pioche = "";
        for(int i=0; i<4; i++) {
            for(int y=0; y<13; y++) {
                pioche += this.pioche[i][y] + " ";
            }
            pioche += "\n";
        }
        return pioche;
    }
}