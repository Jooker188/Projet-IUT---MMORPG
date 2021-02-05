package code;

import java.util.Random;

public class De {
	int de;
	
	public De() {
		de = 0;
	}
	
	public int lancerDe() {
		Random r = new Random();
		de = (r.nextInt(3)+1);
		return de;
	}
	
	public int lancerDes(int nb) {
		De de =  new De();
		int res;
		int compt = 0;
		for (int i=0;i<nb;i++) {
			res = de.lancerDe();
			compt = compt+res;
		}
		return compt;
	}
	
	public int convert(int expl) {
		int res;
		int compteur;
		if (expl%3==0) {
			compteur = expl/3;
			//System.out.println(compteur+"D");
			res = this.lancerDes(compteur);
			return res;
		}
		int bla = expl;
		int compt = 0;
		while (!(bla%3==0)) {
			bla -= 1;
		}
		compt = expl-bla;
		compteur = bla/3;
		//System.out.println(compteur+"D+"+compt);
		bla = this.lancerDes(compteur);
		return bla+compt;
	} 
	
	public String affichage(int res) {
		int compteur;
		if (res%3==0) {
			compteur = res/3;
			return compteur+"D";
		}
		int bla = res;
		int compt = 0;
		while (!(bla%3==0)) {
			bla -= 1;
		}
		compt = res-bla;
		compteur = bla/3;
		return compteur+"D+"+compt;
	}
}
