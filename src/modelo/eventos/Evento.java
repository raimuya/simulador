package modelo.eventos;

public abstract class Evento {
	//tempo de inicio do evento
	double inicio;
	int id;
	
	public Evento(double inicio){
		this.inicio = inicio;
	}

	
	public Evento(double inicio,
			int id){
		this.inicio = inicio;
		this.id = id;
	}
	
	public int get_id(){
		return id;
	}
}
