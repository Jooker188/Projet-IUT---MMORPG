package code;

import java.util.ArrayList;
import java.util.Scanner;

public class Inventaire extends Item{
	private ArrayList<Item> inventaire;
	private ArrayList<Item> objetsEquipes;
	
	public Inventaire() {
		this.inventaire = new ArrayList();
		this.objetsEquipes = new ArrayList();
	} //utiliser les mthodes pour ajouter et r�tirer des objets (add et remove) avec les geteppe etc
	
	public void addInventaire(Item i) {
		this.inventaire.add(i);
	}
	
	public void addObjetsEquipes(Item i) {
		this.objetsEquipes.add(i);
	}
	public void desequiper(Item i) {
		this.objetsEquipes.remove(i);
	}
	
	public boolean contientItem(Item i) {
		if (this.objetsEquipes.contains(i)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int typeItem(Item item) {
		if (item.nomItem() == "sword") {
			return item.getSword();
		}
		else if(item.nomItem() == "shield") {
			return item.getShield();
		}
		else if(item.nomItem() == "arc") {
			return item.getArc();
		}
		else if(item.nomItem() == "healPotion") {
			return item.getHealPotion();
		}
		else if (item.nomItem() == "molotov") {
			return item.getMolotovPotion();
		}
		return item.getArmor();
	}
	
	public void dispContenuInventaire() {	
		System.out.println("Votre sac : ");
		for (Item item : inventaire){
			System.out.println("                     -"+item.nomItem() + " : "+ this.typeItem(item));
		}
	}

	public void dispObjetsEquipes() {	
		System.out.println("Vos objets équipés : ");
		for (Item item : objetsEquipes){
			System.out.println("                     -"+item.nomItem()+ " : "+ this.typeItem(item));
		}
	}
	
	public void modifItem() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Quelle objets voulez-vous améliorer ? : ");
		this.dispObjetsEquipes();
		for(Item item : objetsEquipes) {
			String str = sc.nextLine();
			switch(str) {
			case("sword"):
				item.setSword(item.getSword()+2);
				break;
			case("shield"):
				item.setShield(item.getShield()+2);
				break;
			case("arc"):
				item.setArc(item.getArc()+2);
				break;
			case("armor"):
				item.setArmor(item.getArmor()+2);
				break;
			}
		}
	}
}
