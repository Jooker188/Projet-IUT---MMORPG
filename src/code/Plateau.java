package code;

import java.util.Scanner;
import java.util.Random;

public class Plateau {
	private int maxLig = 25;
	private int maxCol = 30;
	private char[][] plateau = new char[maxLig][maxCol];
	private Player p;

	
	public Plateau() {
	}
	
	public void setItem(int lig, int col, String element) {
		if (element == "sword") {
			this.plateau[lig][col] = '\u0045'; // E
		}
		else if (element == "shield") {
			this.plateau[lig][col] = '\u0042'; // B
		}
		else if (element == "bow") {
			this.plateau[lig][col] = '\u0041';// A
		}
		else if (element == "armor") {
			this.plateau[lig][col] = '\u0043';
		}
		else if (element == "molotovPotion"){
			this.plateau[lig][col] = 'X';
		}
		else if (element == "healPotion") {
			this.plateau[lig][col] = 'H';
		}
		else {
			System.out.println("L'Item/entité que vous essayez de placer n'existe pas !");
		}
	}
	
	public void setPlayer1(int lig, int col) {
		this.plateau[lig][col] = '\u0031';
	}
	
	public void setPlayer2(int lig, int col) {
		this.plateau[lig][col] = '\u0032';
	}
	
	public void setMonster(int lig, int col, String element) {
		if (element == "monster") {
			this.plateau[lig][col] = '\u004D';
		}
		else {
			System.out.println("L'Item/entité que vous essayez de placer n'existe pas !");
		}
	}
	
	public void setWall(int lig, int col, String element) {
		if (element == "wall") {
			this.plateau[lig][col] = '\u002B';
		}
		else {
			System.out.println("L'Item/entité que vous essayez de placer n'existe pas !");
		}
	}
	
	public int getItem(int lig, int col) {
		if ((char) (this.plateau[lig][col]) == '\u0045') {
			return 1;
		}
		else {
			if ((char) (this.plateau[lig][col]) == '\u0042') {
				return 2;
			}
			else {
				if ((char) (this.plateau[lig][col]) == '\u0041') {	
					return 3;
				}
				else {
					if ((char)(this.plateau[lig][col]) == '\u265E') {
						return 4;
					}
					else {
						if ((char)(this.plateau[lig][col]) == 'X') {
							return 5;
						}
						else {
							if ((char) (this.plateau[lig][col]) == 'H') {
								return 6;
							}
						}
					}
				}
			}
			System.out.println("In n'y a pas d'item ici !" + "\n");	
			return 0;
		}
	}
	
	public void getStatsItem(int x, int y,Player p, Item i, Item i2, Item i3, Item i4, Item i5, Item i6) {
		switch(this.getItem(x,y)) {
		case(1):
			p.addInventaire(i3);
			this.disparait(x, y);
			break;
		case(2):
			p.addInventaire(i5);
			this.disparait(x, y);
			break;
		case(3):
			p.addInventaire(i4);
			this.disparait(x, y);
			break;
		case(4):
			p.addInventaire(i6);
			this.disparait(x, y);
			break;
		case(5):
			p.addInventaire(i2);
			this.disparait(x, y);
			break;
		case(6):
			p.addInventaire(i);
			this.disparait(x, y);
			break;
		default:
			break;
		}
	}
	
	public int getEnnemi(int lig, int col) {
		if ((char) (this.plateau[lig][col]) == '\u004D') {
			return 1;
		}
		else if((char) (this.plateau[lig][col]) == '\u0032') {
			return 2;
		}
		return 0;
	}
	
	public void getStatsEnnemi(int x, int y,Player p, Player p2, Monster m) throws InterruptedException {
		switch(this.getEnnemi(x,y)) {
		case(1):
			p.attackEnnemi(m);
			if	(m.checkLevelDmg() == "Mort") {
				this.disparait(x, y);
			}
			break;
		case(2):
			p.attackEnnemi(p2);
			break;
		default:
			System.out.println("In n'y a pas d'ennemi ici !" + "\n");
			break;
		}
	}
	
	public void disparait(int x, int y) {
		this.plateau[x][y] = ' ';
	}
	

	public int[] getPlayerPos() {
		int positionX = 0;
		int positionY = 0;
		
		boolean exit = false;
		
		for (int i=0; i<this.maxLig && exit == false; i++) {
			for (int j=0; j<this.maxCol && exit == false; j++) {
				if (this.plateau[i][j] == '\u0031'){
					exit = true;
				}
				positionY = j;
			}
			positionX = i;
		}
		return new int[] {positionX,positionY};
	}
	
	public int[] getMonsterPos() {
		int positionX = 0;
		int positionY = 0;
		
		boolean exit = false;
		
		for (int i=0; i<this.maxLig && exit == false; i++) {
			for (int j=0; j<this.maxCol && exit == false; j++) {
				if (this.plateau[i][j] == '\u004D'){
					exit = true;
				}
				positionY = j;
			}
			positionX = i;
		}
		return new int[] {positionX,positionY};
	}
	
	
	public void movePlayer(char direction, int x, int y) {
		if (direction == 'b') {
			this.plateau[x][y] = ' '; //on efface l'ancienne position du player
			this.plateau[x+1][y] = '\u0031'; //on place le player au nouvel coordonnées
		}
		else if (direction == 'h'){
			this.plateau[x][y] = ' ';
			this.plateau[x-1][y] = '\u0031';
		}
		else if (direction == 'd'){
			this.plateau[x][y] = ' ';
			this.plateau[x][y+1] = '\u0031';
		}
		else if (direction == 'g'){
			this.plateau[x][y] = ' ';
			this.plateau[x][y-1] = '\u0031';
		}
	}

	
	public char[][] initPlateau() {
		for(int lig = 0; lig < this.maxLig; lig++){
			for(int col = 0; col < this.maxCol; col++) {
				if (lig == 0 || lig == this.maxLig-1 || col == 0 || col == this.maxCol-1) {
					plateau[lig][col] = '\u002B';
				}
				else {
					plateau[lig][col] = ' ';
				}
			}
		}
		return plateau;
	}
	
	
	public void dispPlateau() {
		for (int i=0; i<this.maxLig;i++) {
			for (int j=0;j<this.maxCol;j++) {
				System.out.print(this.plateau[i][j]);
			}
			System.out.println("");
		}
	}

    public void playerRangeItem(int x, int y, Player p, Item i, Item i2, Item i3, Item i4, Item i5, Item i6) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Où voulez vous ramasser l'objet : "+"\n"+"1-En haut ?"+"\n"+
        													 "2-En bas ?"+"\n"+
        													 "3-A gauche?"+"\n"+
        													 "4-A droite ?"+"\n"+
        													 "5-En haut, à gauche ?"+"\n"+
        													 "6-En haut, à droite ?"+"\n"+
        													 "7-En bas, à gauche ?"+"\n"+
        													 "8-En bas, à droite ?");
        int choix = reader.nextInt();
        if (choix == 1) {
	        int h = x-1;
	        this.getStatsItem(h,y,p,i,i2,i3,i4,i5,i6);
	        
        }
        else if (choix == 2) {
        	int b = x+1;
        	this.getStatsItem(b,y,p,i,i2,i3,i4,i5,i6);
	    
        }
        else if (choix == 3) {
	        int g = y-1;
	        this.getStatsItem(x,g,p,i,i2,i3,i4,i5,i6);
	        
        }
        else if (choix == 4) {
        	int d = y+1;
        	this.getStatsItem(x,d,p,i,i2,i3,i4,i5,i6);
        }
        
        else if(choix == 5){
        	int diag1 = x-1;
        	int diag2 = y-1;
        	this.getStatsItem(diag1,diag2,p,i,i2,i3,i4,i5,i6);
        }
        
        else if(choix == 6){
        	int diag1 = x-1;
        	int diag2 = y+1;
        	this.getStatsItem(diag1,diag2,p,i,i2,i3,i4,i5,i6);
        }
        
        else if(choix == 7){
        	int diag1 = x+1;
        	int diag2 = y+1;
        	this.getStatsItem(diag1,diag2,p,i,i2,i3,i4,i5,i6);
        }
        
        else if(choix == 8){
        	int diag1 = x+1;
        	int diag2 = y+1;
        	this.getStatsItem(diag1,diag2,p,i,i2,i3,i4,i5,i6);
        }
        
        else {
        	System.out.println("Erreur");
        }
    }
        
        public void playerRangeEnnemi(int x, int y, Player p, Player p2, Monster m) throws InterruptedException {
        	Scanner sc = new Scanner(System.in);
            System.out.println("Où se situe le monstre : "+"\n"+"1-En haut ?"+"\n"+
            													 "2-En bas ?"+"\n"+
            													 "3-A gauche?"+"\n"+
            													 "4-A droite ?"+"\n"+
            													 "5-En haut, à gauche ?"+"\n"+
            													 "6-En haut, à droite ?"+"\n"+
            													 "7-En bas, à gauche ?"+"\n"+
            													 "8-En bas, à droite ?");
            int choix = sc.nextInt();
            if (choix == 1) {
            	int h = x-1;
            	this.getStatsEnnemi(h,y, p, p2, m);
    	        
            }
            else if (choix == 2) {
            	int b = x+1;
            	this.getStatsEnnemi(b,y, p, p2, m);
    	    
            }
            else if (choix == 3) {
    	        int g = y-1;
    	        this.getStatsEnnemi(g,y, p, p2, m);
    	        
            }
            else if (choix == 4) {
            	int d = y+1;
            	this.getStatsEnnemi(d,y, p, p2, m);
            }
            
            else if(choix == 5){
            	int diag1 = x-1;
            	int diag2 = y-1;
            	this.getStatsEnnemi(diag1,diag2, p, p2, m);
            }
            
            else if(choix == 6){
            	int diag1 = x-1;
            	int diag2 = y+1;
            	this.getStatsEnnemi(diag1,diag2, p, p2, m);
            }
            
            else if(choix == 7){
            	int diag1 = x+1;
            	int diag2 = y+1;
            	this.getStatsEnnemi(diag1,diag2, p, p2, m);
            }
            
            else if(choix == 8){
            	int diag1 = x+1;
            	int diag2 = y+1;
            	this.getStatsEnnemi(diag1,diag2, p, p2, m);
            }
            
            else {
            	System.out.println("Erreur");
            }
   
        }
        
        public void randomMove(int x, int y) {
        	Random r = new Random();
        	this.plateau[x][y] = ' ';
        	int newX = r.nextInt(3)-1;
        	int newY = r.nextInt(3)-1;
        	this.plateau[x+newX][y+newY] = '\u004D';
        }
        
        public boolean isCaseEmpty(int x, int y) {
        	if (this.plateau[x][y] == ' ') {
        		return true;
        	}
        	else {
        		return false;
        	}
        }
        public void setRandomWall(Plateau p) {
        	for (int i=0; i<6; i++) {
        		int x = (int) ((((this.maxLig)-3)-3)*Math.random()) + 3;  //(int) ((max-min)*Math.random()) + min;
                int y = (int) ((((this.maxCol)-3)-3)*Math.random()) + 3;
                
        		int x2 = (int) ((((this.maxLig)-3)-3)*Math.random()) + 3;  //(int) ((max-min)*Math.random()) + min;
                int y2 = (int) ((((this.maxCol)-3)-3)*Math.random()) + 3;
                
                if (isCaseEmpty(x,y)) {
                	
                	//HORIZONTALEMENT
                	p.setWall(x, y, "wall");
                	p.setWall(x, y+1, "wall");
                	p.setWall(x, y+2, "wall");
                	p.setWall(x, y+3, "wall");
                	
                	//VERTICALEMENT
                	p.setWall(x2, y2, "wall");
                	p.setWall(x2+1, y2, "wall");

                }	
        	}     
        }
        
        public void setRandomPlayer(Plateau p) {
            int x = (int) ((((this.maxLig)-1)-1)*Math.random()) + 1;  //(int) ((max-min)*Math.random()) + min;
            int y = (int) ((((this.maxCol)-1)-1)*Math.random()) + 1;
        	
            if (isCaseEmpty(x,y)) {
            	p.setPlayer1(x, y);
            }
        }
        
        public void setRandomItem(Plateau p) {
        	String item[] = {"sword","shield","bow","armor","molotovPotion","healPotion","sword","shield","bow","armor","molotovPotion","healPotion"};
        	
        	for (int i=0; i<10; i++) {
            	int x = (int) ((((this.maxLig)-1)-1)*Math.random()) + 1;  //(int) ((max-min)*Math.random()) + min;
            	int y = (int) ((((this.maxCol)-1)-1)*Math.random()) + 1;
            	
            	if (isCaseEmpty(x,y)) {
            		p.setItem(x, y, item[i]);
            	}
        	} 	
        }
        
        public Monster setRandomMonster() {
        	
            	int x = (int) ((((this.maxLig)-1)-1)*Math.random()) + 1;  //(int) ((max-min)*Math.random()) + min;
            	int y = (int) ((((this.maxCol)-1)-1)*Math.random()) + 1;
            	
            	if (isCaseEmpty(x,y)) {
            		this.setMonster(x, y, "monster");
            	}
            	return new Monster();
        	 	
        }
}