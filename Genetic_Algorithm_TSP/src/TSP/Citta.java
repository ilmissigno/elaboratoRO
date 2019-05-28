package TSP;

public class Citta {
	private double Longitudine;
	private double Latitudine;
	private String Nome;
	
	private static final double CONVERT_DEG_TO_RAD = Math.PI/180D;
	private static final double EARTH_EQU_RAGGIO = 6378.1370D;
	private static final double CONVERT_KM_TO_MILES = 0.621371;
	
	public Citta(String Nome,double Latitudine,double Longitudine) {
		this.setNome(Nome);
		this.setLatitudine(Latitudine * CONVERT_DEG_TO_RAD);
		this.setLongitudine(Longitudine * CONVERT_DEG_TO_RAD);
	}
	
	public double getLongitudine() {
		return Longitudine;
	}

	public void setLongitudine(double longitudine) {
		Longitudine = longitudine;
	}

	public double getLatitudine() {
		return Latitudine;
	}

	public void setLatitudine(double latitudine) {
		Latitudine = latitudine;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}
	
	public double measureDistance(Citta citta){
		double deltaLong = citta.getLongitudine() - this.getLongitudine();
		double deltaLat = citta.getLatitudine() - this.getLatitudine();
		//METRICA UTILIZZATA LINEA D'ARIA
		double a = Math.pow(Math.sin(deltaLat/2D), 2D)+Math.cos(this.getLatitudine()) * Math.cos(citta.getLatitudine())*Math.pow(Math.sin(deltaLong/2D),2D);
		return CONVERT_KM_TO_MILES*EARTH_EQU_RAGGIO*2D*Math.atan2(Math.sqrt(a),Math.sqrt(1D-a));
	}
	
}
