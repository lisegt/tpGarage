package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage 
         * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
		// Et si la voiture est déjà dans un garage ?
		if (this.estDansUnGarage()){
			throw new Exception("La voiture est déjà dans un garage.");
		}
		Stationnement s = new Stationnement(this, g);
		myStationnements.add(s);
	}

	/**
	 * Fait sortir la voiture du garage 
         * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
		if (this.estDansUnGarage()){
			// Trouver le dernier stationnement de la voiture
			Stationnement lastStationnement = myStationnements.get(myStationnements.size()-1);
			// Terminer ce stationnement
			lastStationnement.terminer();
		}
		else {
			throw new Exception("La voiture n'est pas dans un garage.");
		}
	}

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		Set<Garage> myGarages = new HashSet<>();
		for (Stationnement s : myStationnements){
			myGarages.add(s.getGarage());
		}
		return myGarages;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
		boolean res=false;
		if (myStationnements.size()==0){
			res=false;
		} else
		// Vrai si le dernier stationnement est en cours
		if (myStationnements.get(myStationnements.size()-1).estEnCours()){
			return true;
		}
		return res;
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des dates d'entrée / sortie dans ce
	 * garage
	 * <br>Exemple :
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
		for (Garage garage : this.garagesVisites()){
			out.println("Garage "+garage.getName()+":");

			for (Stationnement stationnement : myStationnements){
				if (stationnement.getGarage().getName().equals(garage.getName())){
					out.println("	"+stationnement);
				}
			}
			
		}
	}

	/*
	System.out.println("comment");
	System : classe
	System.out : propriété statique, "sortie standard" de classe PrintStream
	println() : méthode de PrintStream
	*/

}
