package code;

import java.util.Random;

public class Monster {
	private int bodyStrength;
	private int skill;
	private int BodyDurability;
	private int etat;
	private Capacity cap;
	
	public Monster() {
		De de = new De();
		this.bodyStrength = 3;// On initialise la force l'adresse et la résistance des monstres à 3D
		this.skill = 3;
		this.BodyDurability = 3;
		this.etat = 0;
		this.cap = new Capacity(1,1,1,1);
	}
	
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
	
	public void gsetBodyDurability(int BodyDurability) {
		this.BodyDurability = BodyDurability;
	}
	
	public int getBodyDurability() {
		return this.BodyDurability;
	}
	
	public int getEtat() {
		return this.etat;
	}
	
	public void setEtat(int etat) {
		this.etat = etat;
	}
	
	public Capacity getCapacite() {
		return this.cap;
	}
	
	// Capacités
	public int speed() {
		int speed = this.getSkill() - cap.getclothesBurdon();
		return speed;
	}
	
	public int attack() {
		int attack = this.getSkill() + cap.getweaponManiability();
		return attack;
	}
	
	public int dodge() {
		int dodge = this.getSkill() - cap.getclothesBurdon();
		return dodge;
	}
	
	public int defense() {
		int defense = this.getBodyDurability() + cap.getclothesDurability();
		return defense;
	}
	
	public int damage() {
		int damage = this.getbodyStrength() + cap.getweaponStrength();
		return damage;
	}
	
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
}
