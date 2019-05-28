package TSP;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class mainAlgoritmo {
	
	public ArrayList<Citta> initialRoute=new ArrayList<Citta>(Arrays.asList(
			//ho citta- latitudine e logitudine
			new Citta("Boston",42.3601,-71.0589),
			new Citta("Houston",29.7604,-95.3698),
			new Citta("Austin",29.7604,-95.3698),
			new Citta("San Francisco",37.7749,-122.4194),
			new Citta("Denver",39.7392,-104.9903),
			new Citta("Los Angeles",34.0522,-118.2437),
			new Citta("Chicago", 41.8781,-87.6298),
			new Citta("New York", 40.7128,-74.0059),
			new Citta("Dallas",32.7767,-96.7970),
			new Citta("Seattle",47.6062,-122.3321)
			));
	
	/*
	public static void getInput() {
		ArrayList<String> result = new ArrayList<>();
		ArrayList<Citta> percorsoIniziale = null;
		Scanner s;
		try {
			s = new Scanner(new FileReader("C:\\Users\\Raffaele\\Desktop\\Eclipse Workspace Java\\Genetic_Algorithm_TSP\\src\\TSP\\ali535.txt"));
			while(s.hasNext()) {
				result.add(s.nextLine());
			}
			for(int i=0;i<result.size();i++) {
				String[] stringa = result.get(i).split("_");
				percorsoIniziale.add(new Citta(Integer.parseInt(stringa[0]),Double.parseDouble(stringa[1]),Double.parseDouble(stringa[2])));
			}
			System.out.println(percorsoIniziale.get(2));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	public static void main(String[] args) {
		
	}

}
