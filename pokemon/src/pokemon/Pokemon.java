package pokemon;

import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon implements Serializable {
	private static final long serialVersionUID = 3962266605274622183L;

	private String nom, type;
	int pc;
	int pv;
	ArrayList<String> evolution;

	public Pokemon(String nom, String type, int pc, int pv, ArrayList<String> evolution) {
		super();
		this.nom = nom;
		this.type = type;
		this.pc = pc;
		this.pv = pv;
		this.evolution = evolution;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc += pc;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv += pv;
	}
    public void PV0() {
    	this.pv=0;
    }
	public void DiminuerPv(int pv) {
		this.pv -= pv;
	}

	public String getType() {
		return type;
	}

	public ArrayList<String> getEvolution() {
		return evolution;
	}

	public void ajouerEvolution(String p) {
		getEvolution().add(p);

	}
	public String Afficher() {
		return " \n nom=" + nom + ", type=" + type + ", pc=" + pc + ", pv=" + pv + " evolution=" + evolution + "\n";
	}

	@Override
	public String toString() {
		return "Pokemon [nom=" + nom + ", type=" + type + ", pc=" + pc + ", pv=" + pv + ", evolution=" + evolution
				+ "]";
	}

}
