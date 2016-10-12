package modelo.eventos;

import controle.Simulador;
import modelo.mensagens.Mensagem;

public abstract class Evento {
	//tempo de inicio do evento
	double inicio;
	int id = 0;
	Mensagem m;
	
	
	public Evento(double inicio, Mensagem m){
		this.inicio = inicio;
		this.id = id++;
		this.m = m;
		System.out.println("\n **************** CRIADO ****************  TNOW: " + Simulador.TNOW());
		System.out.println(toString());
	}

	public int get_id(){
		return id;
	}

	public double get_inicio(){
		return inicio;
	}
	
	public abstract String toString();
	
	public abstract void processa_evento(Simulador s);
}
