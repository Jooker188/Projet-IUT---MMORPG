package code;

public class Player extends Inventaire{
	private int bodyStrength;
	private int skill;
	private int bodyDurability;
	private int etat;
	private int xp;
	private Inventaire inventaire;
	private Capacity capacite;
	
	//Constructeurs
	public Player() {
		this.bodyStrength = 0;
		this.skill = 0;
		this.bodyDurability = 0;
		this.etat = 0;
		this.xp =0;
	}
	
	public Player(int strength, int attak, int health,Inventaire i, Capacity c) {
		this.bodyStrength = strength;
		this.skill = attak;
		this.bodyDurability = health;
		this.etat = 0;
		this.xp = 0;
		this.inventaire = i;
		this.capacite = c;
	}
		
	public Player(Player p) {
		this(p.bodyStrength,p.skill,p.bodyDurability,p.inventaire,p.capacite);
	}
	
	//Getters et setters
	public void setbodyStrength(int bodyStrength) {
		this.bodyStrength = bodyStrength;
	}
	
	public int getbodyStrength() {
		return this.bodyStrength;
	}
	
	public void setSkill(int skill) {
		this.skill = skill;
	}
	
	public int getSkill() {
		return this.skill;
	}
	
	public void setBodyDurability(int bodyDurability) {
		this.bodyDurability = bodyDurability;
	}
	
	public int getBodyDurability() {
		return this.bodyDurability;
	}
	
	public int getEtat() {
		return this.etat;
	}
	
	public void setEtat(int etat) {
		this.etat = etat;
	}
	
	public int getXp() {
		return this.xp;
	}
	
	public void setXp(int xp) {
		this.xp = xp;
	}
	
	public Inventaire getInventaire() {
		return this.inventaire;
	}
	
	public Capacity getCapacite() {
		return this.capacite;
	}
	
	public void setInventaire(Item i) {
		this.inventaire.addInventaire(i);
	}
	
	public void setCapacite(Capacity cap) {
		this.capacite = cap;
	}
	
	//-------------------------------Capacités-----------------------------------------------------------------
	
	public int speed() {
		int speed = this.getSkill() - capacite.getclothesBurdon();
		return speed;
	}
	
	public int attack() {
		int attack = this.getSkill() + capacite.getweaponManiability();
		return attack;
	}
	
	public int dodge() {
		int dodge = this.getSkill() - capacite.getclothesBurdon();
		return dodge;
	}
	
	public int defense() {
		int defense = this.getBodyDurability() + capacite.getclothesDurability();
		return defense;
	}
	
	public int damage() {
		int damage = this.getbodyStrength() + capacite.getweaponStrength();
		return damage;
	}
	
	//---------------------------------METHODES------------------------------------------------------------
	/*
	public boolean checkEnnemi(Object e) { //Cette méthode sert a réperer si l'ennemi est un autre joueur
    	if (e==null) return false;
    	if (!(e instanceof Player)) return false; //si e n'est pas une instance de Player on return false
    	return true;
	}
	
	public boolean checkItem(Object e) { //Cette méthode sert a réperer si l'ennemi est un autre joueur
    	if (e==null) return false;
    	if (!(e instanceof Item)) return false; //si e n'est pas une instance de Player on return false
    	return true;
	} */
	
	public String checkLevelDmg() { //sert a vérifier l'état physique de son personnage
		switch(this.getEtat()) { //pour chaque etat on print une phrase indiquant l'état
		case 0:
			return "Etat normale";
		case 1:
			return "Blessures superficielles";
		case 2:
			return "Légèrement blessé";
		case 3:
			return "Blessé";
		case 4:
			return "Gravement blessé";
		case 5:
			return "Inconscient";
		case 6:
			return "Mort";
		}
		return "Erreur";
	}
	
	public int dammage(int dmg) {
		this.setEtat(dmg);
		return this.etat;
	}
	
	/*public int addXp(Monster ennemi, int nb) {
		int a;
		a = 10 * nb;
		return a;
	} */

	public void attackEnnemi(Monster ennemi) throws InterruptedException {
		System.out.println("Vous attaquez !!!"+"\n");
		Thread.sleep(1000);
		if (this.attack() > ennemi.dodge()) {
			this.setXp(this.getXp()+1);
			System.out.println("Coup réussit !!!"+"\n");
			Thread.sleep(1000);
			if (this.damage()>ennemi.defense()) {
				System.out.println("Dégats infligés !!!"+"\n");
				Thread.sleep(1000);
				if (this.damage() >= 2*ennemi.defense()) {
					ennemi.setEtat(ennemi.getEtat()+2);
					ennemi.checkLevelDmg();
					Thread.sleep(1000);
					if (ennemi.getEtat() >= 6) {
						ennemi.setEtat(6);
					}
				}	
				else {
					ennemi.setEtat(ennemi.getEtat()+1);
					System.out.println("Etat actuel de l'ennemi : "+ennemi.checkLevelDmg()+"\n");
					Thread.sleep(1000);
					if (ennemi.getEtat() >= 6) {
						this.setXp(this.getXp()+10);
					}

				}
				
			}
			else {
				System.out.println("L'ennemi n'a reçu aucun dégats");
			}
		}
		else {
			System.out.println("L'ennemi à esquivé le coup");
		}
	}
	
	public void attackEnnemi(Player ennemi) {
		System.out.println("Vous attaquez !!!");
		if (this.attack() > ennemi.dodge()) {
			this.setXp(this.getXp()+1);
			System.out.println("Coup réussit !!!");
			if (this.damage()>ennemi.defense()) {
				System.out.println("Dégats infligés !!!");
				if (this.damage() == 2*ennemi.defense()) {
					ennemi.setEtat(ennemi.getEtat()+2);
				}
				else {
					ennemi.setEtat(ennemi.getEtat()+1);
				}
			}
			else {
				System.out.println("L'ennemi n'a reçu aucun dégats");
			}
		}
		else {
			System.out.println("L'ennemi à esquivé le coup");
		}
	}
	
	public String toString() {
		this.getInventaire();
		return "vie : "+this.getbodyStrength()+"\n"+"skill : "+this.getSkill()+"\n"+"bodyDurability : "+ this.getBodyDurability()+"\n"+"etat : "+this.getEtat()+"\n" ;	
		}
}
	
