package code;
import java.util.Scanner;

public class Main {
	
	public static void dispChoixItem(String i) {
		System.out.println("1-Equiper "+i+System.getProperty("line.separator")+"2-Déséquiper "+i+System.getProperty("line.separator")+"3-Quitter");
	}
	public static void dispChoixItemSpecial(String i) {
		System.out.println("1-Boire "+i+System.getProperty("line.separator")+"2-Quitter");
	}
	
	public static int test(int somme){
		Scanner sc = new Scanner(System.in);
		int res = sc.nextInt();
		somme += res;
		if(somme > 18) {
			System.out.println("Le nombre de dégrés maximale est dépassé, choisissez un nombre qui correspond (votre nombre actuelle est de "+(somme-res)+"degrés)");
			res = sc.nextInt();
		}
		return res;
	}
	
	
	public static void main(String args[]) throws InterruptedException {
	
	//INITIALISATION DE LA PARTIE EN COURS
	System.out.println("Quelles sont vos stats : ");
	int somme = 0;
	Scanner sc = new Scanner(System.in);
	int bodyStrength = test(somme);
	somme += bodyStrength;
	int skill = test(somme);
	somme += bodyStrength;
	int bodyDurability = test(somme);;
	
	Plateau p = new Plateau();
	Inventaire inventaire = new Inventaire();
	Capacity capacities = new Capacity(2,6,7,8);
	Player player = new Player(bodyStrength,skill,bodyDurability,inventaire,capacities);
	Player player2 = new Player(5,5,5,inventaire,capacities);
	Monster monster = p.setRandomMonster();
	
	Item healPotion = new Item(2,0,0,0,0,0);
	Item molotovPotion = new Item(0,2,0,0,0,0);
	Item sword = new Item(0,0,3,0,0,0);
	Item arc = new Item(0,0,0,2,0,0);
	Item shield = new Item(0,0,0,0,2,0);
	Item armor = new Item(0,0,0,0,0,3);

	p.initPlateau();
	
	//PLACEMENT ALEATOIRES DU JOUEURS, DES MONSTRES ET DES ITEMS
	
	p.setRandomWall(p);
	p.setRandomPlayer(p);
	p.setRandomItem(p);
	p.setRandomMonster();
	p.setItem(15, 15, "healPotion");
	p.setItem(16, 14, "sword");
	p.setItem(14, 14, "shield");
	p.setItem(14,15,"molotovPotion");
	
	player.addInventaire(healPotion);
	player.addInventaire(molotovPotion);
	player.addInventaire(sword);
	player.addInventaire(arc);
	player.addInventaire(molotovPotion);
	
	//CORPS PRINCIPAL DU PROGRAMME
	
	int pointAction = 6;
	int tour = 0;
	De de = new De();
	
	Scanner reader = new Scanner(System.in);
	while (true && player.getEtat() != 6) {
		p.dispPlateau();
		player.dispContenuInventaire();
		System.out.println();
		player.dispObjetsEquipes();
		System.out.println();
		System.out.println("Vos caractéristiques : "+"\n"+"- force : "+de.affichage(player.getbodyStrength())+"\n"+
													      "- adresse : "+ de.affichage(player.getSkill())+"\n"+
													      "- endurance : "+ de.affichage(player.getBodyDurability())+"\n"+
													      "+ initiative : "+de.affichage(player.speed())+"\n"+
													      "+ attaque : "+ de.affichage(player.attack())+"\n"+
													      "+ esquive : "+ de.affichage(player.dodge())+"\n"+
													      "+ défense : "+ de.affichage(player.defense())+"\n"+
													      "+ dégâts : "+ de.affichage(player.damage()));
		System.out.println();
		System.out.println("Votre niveau de blessures : " + player.checkLevelDmg()+"\n");
		System.out.println("Votre nombre de points d'expérience : " + player.getXp());
		System.out.println(System.getProperty("line.separator")+"Il vous reste : "+pointAction+" PA"+System.getProperty("line.separator"));
		System.out.println("Vous pouvez : "+System.getProperty("line.separator")+"1 - vous déplacer (2PA)"+System.getProperty("line.separator")
																				+"2 - attaquer (3PA)"+System.getProperty("line.separator")
																				+"3 - utiliser un objet (Variable)"+System.getProperty("line.separator")
																				+"4 - ramasser/déposer un objet (2PA)"+System.getProperty("line.separator")
																				+"5 - utiliser vos points d'expériences (1PA)"+"\n"
																				+"6 - finir et garder les PA restants"+System.getProperty("line.separator"));
		System.out.println("Votre choix :");																		
		int choixAction = reader.nextInt();
		
		if (choixAction == 1 && pointAction >= 2) {
			pointAction -= 2;
			System.out.println("Mouvement (h,b,d,g) : ");
			char playerInput = reader.next().charAt(0);
			p.movePlayer(playerInput,p.getPlayerPos()[0],p.getPlayerPos()[1]);
			p.dispPlateau();
		}
		
		else if(choixAction == 2 && pointAction >= 3) {
			pointAction -= 3;
			monster.checkLevelDmg();
			p.playerRangeEnnemi(p.getPlayerPos()[0],p.getPlayerPos()[1], player, player2, monster);
			monster.checkLevelDmg();
		}
		
		else if(choixAction == 3 && pointAction >= 3) {
			System.out.println();
			player.dispContenuInventaire();
			System.out.println(System.getProperty("line.separator"));
			System.out.println("Quel Item souhaitez-vous sélectionner ?");
			String choixActionObjet = reader.next();
			
			switch(choixActionObjet) {
				
			case "arc":
				dispChoixItem("arc");
				int choix0 = reader.nextInt();
				if (choix0 == 1) {
					player.addObjetsEquipes(arc);
					arc.useArc(player);
				}
				else if (choix0 == 2){
					if (player.contientItem(arc)) {
						player.desequiper(arc);
					}
					else {
						System.out.println("Vous n'avez pas d'Arc équipé.");
						break;
					}
				}
				break;
				
			
			case "healPotion":
				dispChoixItemSpecial("healPotion");
				int choix1 = reader.nextInt();
				if (choix1 == 1) {
					healPotion.useHealPotion(player);
				
				break;
				} 
			
			case "molotovPotion":
				dispChoixItem("molotovPotion");
				int choix2 = reader.nextInt();
				if (choix2 == 1) {
					player.addObjetsEquipes(molotovPotion);
				}
				else if (choix2 == 2){
					molotovPotion.useMolotovPotion(player);
				}
				else {
					break;
				} 
				
			case "sword":
				dispChoixItem("sword");
				int choix3 = reader.nextInt();
				if (choix3 == 1) {
					player.addObjetsEquipes(sword);
					sword.useSword(player);
					break;
				}
				else if (choix3 == 2){
					if (player.contientItem(sword)) {
						player.desequiper(sword);
					}
					else {
						System.out.println("Vous n'avez pas de Sword équipé.");
						break;
					}
				}
				else {
					break;
				} 
				
			case "shield":
				dispChoixItem("shield");
				int choix4 = reader.nextInt();
				if (choix4 == 1) {
					player.addObjetsEquipes(shield);
					shield.useShield(player);
					break;
				}
				else if (choix4 == 2){
					if (player.contientItem(shield)) {
						player.desequiper(shield);
					}
					else {
						System.out.println("Vous n'avez pas de Shield équipé.");
						break;
					}
				}
				else {
					break;
				} 
				
			case "armor":
				dispChoixItem("armor");
				int choix5 = reader.nextInt();
				if (choix5 == 1) {
					player.addObjetsEquipes(armor);
					armor.useArmure(player);
					break;
				}
				else if (choix5 == 2){
					if (player.contientItem(armor)) {
						player.desequiper(armor);
					}
					else {
						System.out.println("Vous n'avez pas d'Armor équipé.");
						break;
					}
				}
				else {
					break;
				} 
			}			
		}
		
		else if (choixAction == 4 && pointAction >= 2) {
			pointAction -= 2;
			p.playerRangeItem(p.getPlayerPos()[0],p.getPlayerPos()[1], player, healPotion, molotovPotion, sword, arc, shield, armor);
			
		}
		else if (choixAction == 5 && pointAction >=1) {
			pointAction -= 1;
			if(player.getXp() >= 10)
				player.setXp(player.getXp()-10);
				player.modifItem();
		}
		
	       if (tour%2==0 && tour!= 0) {
                pointAction++;
                if (player.getEtat() > 0) {
                	if (player.getEtat() == 1) {
                		player.setEtat(player.getEtat()+1);
                	}
                	player.setEtat(player.getEtat()-2);
                }
                //p.randomMove(p.getMonsterPos()[0],p.getMonsterPos()[1]);
            }
            tour++;
		}
	
	
	System.out.println("Vous êtes mort, essayez à nouveau !");
	
	}
}