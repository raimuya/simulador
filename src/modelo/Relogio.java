package modelo;

public class Relogio {

	double tempo;
	
	public Relogio(double tempo){
		this.tempo = tempo;
	}
	
	public double getTempo(){
		return tempo;
	}
	
	public void setTempo(double tempo){
		this.tempo = tempo;
	}
	
	public void reset(){
		tempo = 0;
	}
}
