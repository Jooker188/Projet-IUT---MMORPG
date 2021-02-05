package code;

public class Capacity {
	private int clothesBurdon;
	private  int weaponManiability;
	private int clothesDurability;
	private int weaponStrength;
	
	public Capacity() {
		this.clothesBurdon = 0;
		this.weaponManiability = 0;
		this.clothesDurability = 0;
		this.weaponStrength = 0;
	}
	
	public Capacity(int clothesBurdon, int weaponManiability, int clothesDurability, int weaponStrength) {
		this.clothesBurdon = clothesBurdon;
		this.weaponManiability = weaponManiability;
		this.clothesDurability = clothesDurability;
		this.weaponStrength = weaponStrength;
	}
	
	public Capacity(Capacity c) {
		this(c.clothesBurdon,c.weaponManiability,c.clothesDurability,c.weaponStrength);
	}
	
	public void setclothesBurdon(int clothesBurdon) {
		this.clothesBurdon = clothesBurdon;
	}
	
	public int getclothesBurdon() {
		return clothesBurdon;
	}
	
	public void setweaponManiability(int weaponManiability) {
		this.weaponManiability = weaponManiability;
	}
	
	public int getweaponManiability() {
		return weaponManiability;
	}
	
	public void setclothesDurability(int clothesDurability) {
		this.clothesDurability = clothesDurability;
	}
	
	public int getclothesDurability() {
		return clothesDurability;
	}
	
	public void setweaponStrength(int weaponStrength) {
		this.weaponStrength = weaponStrength;
	}
	
	public int getweaponStrength() {
		return weaponStrength;
	}
	
}
