package code;

public class Item {
	private int healPotion;
	private int molotovPotion;
	private int sword;
	private int arc;
	private int shield;
	private int armor;
	
//----------------------------------------------------Constructeurs------------------------------------------------

	public Item() {
		this.healPotion = 0;
		this.molotovPotion = 0;
		this.sword = 0;
		this.arc = 0;
		this.shield = 0;
		this.armor = 0;
	}
	
	public Item(int healPotion, int molotovPotion, int sword, int arc, int shield, int armor) {
		this.healPotion = healPotion;
		this.molotovPotion = molotovPotion;
		this.sword = sword;
		this.arc = arc;
		this.shield = shield;
		this.armor = armor;
	}
	
	public Item(Item i) {
		this.healPotion = i.healPotion;
		this.molotovPotion = i.molotovPotion;
		this.sword = i.sword;
		this.arc = i.arc;
		this.shield = i.shield;
		this.armor = i.armor;
	}
	
	//---------------------------------Getters et setters------------------------------------------------------------
	
	public int getHealPotion() {
		return this.healPotion;
	}
	
	public void setHealPotion(int healPotion){	
		this.healPotion = healPotion;
	}
	
	
	public int getMolotovPotion() {
		return this.molotovPotion;
	}
	
	public void setMolotovPotion(int molotovPotion){	
		this.molotovPotion = molotovPotion;
	}
	
	public int getSword() {
		return this.sword;
	}
	
	public void setSword(int sword){	
		this.sword = sword;
	}
	
	public int getArc() {
		return this.arc;
	}
	
	public void setArc(int arc){	
		this.arc = arc;
	}
	
	public int getShield() {
		return this.shield;
	}
	
	public void setShield(int shield){	
		this.shield = shield;
	}
	
	public int getArmor() {
		return this.armor;
	}
	
	public void setArmor(int armor){	
		this.armor = armor;
	}
	//---------------------------------METHODES------------------------------------------------------------
	
	public void useHealPotion(Player p) {				// rétablit l'etat du joueur (-2)
		if (p.getEtat()>0) {
			if (p.getEtat() == 1) {
				p.setEtat(p.getEtat()+1); 
			}
			p.setEtat(p.getEtat()-2);}
			else {
				System.out.println("Santé max");
			}
		System.out.println(p.checkLevelDmg());
	}
	
	public void useMolotovPotion(Player p) {
		if (p.getEtat()>0)
			p.setEtat(p.getEtat()+2);
		System.out.println(p.checkLevelDmg());    //test
	}
	
	public void useSword(Player p) {
		p.setbodyStrength(p.getbodyStrength()+2);
		System.out.println(p.getbodyStrength());
	}
	
	public void useArc(Player p) {
		p.setbodyStrength(p.getbodyStrength()+3);
		//System.out.println(p.getbodyStrength());
	}
	
	public void useShield(Player p) {
		p.setBodyDurability(p.getBodyDurability()+2);
		//System.out.println(bodyDurability);
	}
	
	public void useArmure(Player p) {
		p.setBodyDurability(p.getBodyDurability()+2);
		//System.out.println(p.getBodyDurability());
	}
	
	public String nomItem() {
		if (this.getHealPotion()!= 0) {
			return "healPotion";
		}
		else {
			if(this.getMolotovPotion()!=0) {
				return "molotov";
		}
			else {
				if(this.getSword()!=0) {
					return "sword";
				}
				else {
					if(this.getArc()!=0) {
						return "arc";
					}
					else {
						if(this.getShield()!=0) {
							return "shield";
						}
					}
				}
			}
			
		}
		return "Armor";
	}
	
	public int getByName() {
		if (this.nomItem() == "healPotion") {
			return getHealPotion();
		}
		else {
			if(this.nomItem() == "molotov") {
				return getMolotovPotion();
		}
			else {
				if(this.nomItem() == "sword") {
					return getSword();
				}
				else {
					if(this.nomItem()== "arc") {
						return getArc();
					}
					else {
						if(this.nomItem()== "shield") {
							return getShield();
						}
					}
				}
			}
			
		}
		return getArmor();
	}
	
	
	public static String toString(String chaine) {
		return new String(chaine);
	}
}