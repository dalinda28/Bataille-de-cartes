public class Carte {
	
	private String colors[] = { "PIQUE", "COEUR", "CARREAU", "TREFLE"};
	private String values[] = { "DEUX", "TROIS", "QUATRE", "CINQ", "SIX", "SEPT", "HUIT", "NEUF", "DIX", "VALET", "DAME", "ROI", "AS" };
	private int color;
	private int value;
	
	public Carte(int color,int value) {
		this.color =  color;
		this.value = value;
	}
	
	public int getCouleur() {
		return color;
	}
	
	public int getValeur() {
		return value;
	}
	
	 @Override
	 public String toString(){
		return values[value] + " de " + colors[color];
	}
}
