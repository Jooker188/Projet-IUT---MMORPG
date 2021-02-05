package code;
import java.util.Scanner;
import java.util.Random;

public class Test {
	public static void main(String[] args) {
		Inventaire i = new Inventaire();
		Item popo = new Item(1,0,0,0,0,0);
		Item fire = new Item(0,1,0,0,0,0);
		Item shield = new Item(0,0,0,0,1,0);
		Capacity c = new Capacity(1,1,1,1);
		i.addInventaire(popo);
		i.addInventaire(shield);
		i.addObjetsEquipes(popo);
		Player p = new Player(9,8,9,i,c);
		Player pl = new Player(1,1,1,i,c);
		popo.useHealPotion(p);
		Item item = new Item(0,0,1,0,0,0);
		p.setInventaire(item);
		char ijs = '\u0045';
		Random r = new Random();
		int newX = r.nextInt(3)-1;
		System.out.println(newX);
	}
}
