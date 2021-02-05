package code;

public class PointAction {
	private int pa;
	
	public PointAction() {
		this.pa = 0;
	}
	
	public PointAction(int pa) {
		this.pa = pa;
	}
	
	public PointAction(PointAction point) {
		this.pa = point.pa;
	}
	
	public void setPa(int pa){
		this.pa = pa;
	}
	
	public int getPa() {
		return this.pa;
	}
	
	public boolean checkAttack(int pa) { //ennemi est une instance de Monstre ou de Player
		if  (pa >= 3) { //si les points d'attaque sont supérieur ou éguale à 3 on peux attaque
			return true;
		}
		else {
			System.out.println("Le nombre de pa est insuffisant");
			return false;
		}
		
	}
	
	public int move(){
		if (this.pa >= 2) {
			System.out.println("Vous vous déplacez");
			this.pa  = this.pa-2;
			return this.pa;
		}
		else {
			System.out.println("Le nombre de pa est insuffisant");
			return this.pa;
		}
	}
}
