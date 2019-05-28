package TSP;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Popolazione {
	private ArrayList<Itinerario> itinerari = new ArrayList<Itinerario>(Algoritmo_Genetico.DIM_POPOLAZIONE);
	public Popolazione(int dimPopolazione, Algoritmo_Genetico algoritmo_genetico) {
		IntStream.range( 0,dimPopolazione).forEach(x->itinerari.add(new Itinerario (algoritmo_genetico.getInitialRoute())));
	}
	public Popolazione(int dimPopolazione,ArrayList<Citta> citta) {
		IntStream.range(0,dimPopolazione).forEach(x->itinerari.add(new Itinerario(citta)));
	}
	
	public ArrayList<Itinerario> getItinerari(){
		return itinerari;
	}
	
	public void ordinamento_per_Fitness() {
		itinerari.sort((itinerario1,itinerario2)->{
			int flag=0;
			if(itinerario1.getFitness()>itinerario2.getFitness()) {
				flag=-1;
			}else if(itinerario1.getFitness()<itinerario2.getFitness()) {
				flag=1;
			}
			return flag;
		});
	}
	
}
