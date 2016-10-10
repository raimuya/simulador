package modelo;

public class Relogio {

	double tempo;
	
	public Relogio(double tempo){
		this.tempo = tempo;
	}
	
	public double getTempo(){
		return tempo;
	}
	
	public void avanca(double tempo){
		System.out.println("TEMPO: " + tempo);
		this.tempo += tempo;
	}
	
	public void reset(){
		tempo = new Double("0.0");
	}
}
