package modelo;

public final class Relogio {

	double tempo;
	
	public Relogio(double tempo){
		this.tempo = tempo;
	}
	
	public double getTempo(){
		return tempo;
	}
	
	public void avanca(double tempo){
		this.tempo = tempo;
	}
	
	public void reset(){
		tempo = new Double("0.0");
	}
}
