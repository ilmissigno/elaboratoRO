package TSP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Itinerario {
	private boolean isFitnessChanged = true;
	private double fitness = 0;
	private ArrayList<Citta> Luoghi = new ArrayList<Citta>();
	
	
	public Itinerario(Algoritmo_Genetico algoritmo_genetico) {
		algoritmo_genetico.getInitialRoute().forEach(x->Luoghi.add(null));		
		
	}
	public Itinerario(ArrayList<Citta> citta) {
		this.Luoghi.addAll(citta);
		Collections.shuffle(this.Luoghi);
	}
	
	public ArrayList<Citta> getCitta(){
		isFitnessChanged = true;
		return Luoghi;
	}
	
	public double getFitness() {
		if(isFitnessChanged) {
			//Simulazione MonteCarlo
			fitness = (1/calcolaPercorsoTotale())*10000;
			isFitnessChanged = false;
		}
		return fitness;
	}
	
	/*Distanza di un Percorso*/
	public double calcolaPercorsoTotale() {
		int numCitta = this.Luoghi.size();
		return(int)(this.Luoghi.stream().mapToDouble(x->{
			int indiceCitta = this.Luoghi.indexOf(x);
			double returnValue = 0;
			if(indiceCitta<numCitta-1) {
				returnValue = x.measureDistance(this.Luoghi.get(indiceCitta+1));
			}
			return returnValue;
		}).sum() + this.Luoghi.get(0).measureDistance(this.Luoghi.get(numCitta-1)));
	}
	
	public String toString() {
		return Arrays.toString(Luoghi.toArray());
	}
	
}
