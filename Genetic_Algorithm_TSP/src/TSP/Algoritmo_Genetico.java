package TSP;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Algoritmo_Genetico {
	public static final double MUTATION_RATE = 0.25; //Probabilità di mutazione
	public static final int DIM_POPOLAZIONE = 8; //Numero di soluzioni desiderate (8 cromosomi)
	public static final int TOURNAMENT_SELECTION_SIZE = 3; //Usato per il Crossover Selection (CrossPoint)
	public static final int NUMB_OF_ELITE_ROUTES = 1; //Usato per la mutazione
	public static final int NUMB_OF_GENERATIONS = 30; //Numero di Generazioni
	private ArrayList<Citta> initialRoute=null;
	
	public Algoritmo_Genetico(ArrayList<Citta> initialRoute) {
		this.initialRoute=initialRoute;
	}
	
	public ArrayList<Citta>getInitialRoute(){
		return initialRoute;
	}
	
	public Popolazione evoluzione(Popolazione popolazione) {
		return mutazionePopolazione(crossOverPopolazione(popolazione));
	}
	
	Popolazione crossOverPopolazione(Popolazione popolazione) {
		Popolazione crossOverPopolazione = new Popolazione(popolazione.getItinerari().size(),this);
		IntStream.range(0,NUMB_OF_ELITE_ROUTES).forEach(x->crossOverPopolazione.getItinerari().set(x,popolazione.getItinerari().get(x)));
		IntStream.range(NUMB_OF_ELITE_ROUTES, crossOverPopolazione.getItinerari().size()).forEach(x->{
			Itinerario itinerario1 = selezioneCrossOverPopolazione(popolazione).getItinerari().get(0);
			Itinerario itinerario2 = selezioneCrossOverPopolazione(popolazione).getItinerari().get(0);
			crossOverPopolazione.getItinerari().set(x, crossOverItinerario(itinerario1,itinerario2));
		});
		return crossOverPopolazione;
	}
	
	Popolazione mutazionePopolazione(Popolazione popolazione) {
		popolazione.getItinerari().stream().filter(x -> popolazione.getItinerari().indexOf(x)>=NUMB_OF_ELITE_ROUTES).forEach(x->mutazioneItinerario(x));
		return popolazione;
	}
	
	Itinerario crossOverItinerario(Itinerario itinerario1 , Itinerario itinerario2) {
		Itinerario crossOverItinerario = new Itinerario(this);
		Itinerario tempItinerario1 = itinerario1;
		Itinerario tempItinerario2 = itinerario2;
		if(Math.random()<0.5) {
			tempItinerario1 = itinerario2;
			tempItinerario2 = itinerario1;
		}
		for(int x=0;x<crossOverItinerario.getCitta().size()/2;x++) {
			crossOverItinerario.getCitta().set(x, tempItinerario1.getCitta().get(x));
		}
		return eliminaNullInCrossOverItinerario(crossOverItinerario,tempItinerario2);
	}
	
	private Itinerario eliminaNullInCrossOverItinerario(Itinerario crossOverItinerario,Itinerario itinerario) {
		itinerario.getCitta().stream().filter(x -> !crossOverItinerario.getCitta().contains(x)).forEach(cittaX -> {
			for(int y=0;y<itinerario.getCitta().size();y++) {
				if(crossOverItinerario.getCitta().get(y)==null) {
					crossOverItinerario.getCitta().set(y, cittaX);
					break;
				}
			}
		});
		return crossOverItinerario;
	}
	
	Itinerario mutazioneItinerario(Itinerario itinerario) {
		itinerario.getCitta().stream().filter(x->Math.random()<MUTATION_RATE).forEach(cittaX->{
			int y = (int)(itinerario.getCitta().size()*Math.random());
			Citta cittaY = itinerario.getCitta().get(y);
			itinerario.getCitta().set(itinerario.getCitta().indexOf(cittaX), cittaY);
			itinerario.getCitta().set(y, cittaX);
		});
		return itinerario;
	}
	
	Popolazione selezioneCrossOverPopolazione(Popolazione popolazione) {
		Popolazione tournamentPopolazione = new Popolazione(TOURNAMENT_SELECTION_SIZE,this);
		IntStream.range(0,TOURNAMENT_SELECTION_SIZE).forEach(x->tournamentPopolazione.getItinerari().set(
				x,popolazione.getItinerari().get((int)(Math.random()*popolazione.getItinerari().size()))));
		tournamentPopolazione.ordinamento_per_Fitness();
		return tournamentPopolazione;
	}
}


