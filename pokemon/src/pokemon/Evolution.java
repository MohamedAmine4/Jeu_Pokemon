package pokemon;

import java.io.Serializable;
import java.util.ArrayList;

public class Evolution implements Serializable {

	private String evolution1, evolution2;
	ArrayList<String> evolutionList = new ArrayList<>();

	public Evolution(String evolution1, String evolution2) {
		super();
		this.evolution1 = evolution1;
		this.evolution2 = evolution2;
		evolutionList.add(evolution1);
		evolutionList.add(evolution2);
	}

	public Evolution(String evolution1) {
		super();
		this.evolution1 = evolution1;
		evolutionList.add(evolution1);

	}

	public String getEvolution1() {
		return evolution1;
	}

	public String getEvolution2() {
		return evolution2;
	}

	public ArrayList<String> getEvolutionList() {
		return evolutionList;
	}

	public String Afficher() {
		if (evolution2 == null)
			return "evolution1=" + evolution1 + "";
		return "evolution1=" + evolution1 + ", evolution2=" + evolution2 + "";

	}

	@Override
	public String toString() {
		if (evolution2 == null)
			return " [evolution1=" + evolution1 + "]";
		return " [evolution1=" + evolution1 + ", evolution2=" + evolution2 + "]";

	}

}
