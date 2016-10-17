package modelo.eventos;

import java.text.DecimalFormat;

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
//		System.out.println("\n **************** CRIADO ****************  TNOW: " + Simulador.TNOW());
//		System.out.println(toString());
	}

	public int get_id(){
		return id;
	}

	public String get_inicio_STRING(){
		DecimalFormat dois_digitos = new DecimalFormat("#######.##");
		return dois_digitos.format(inicio);
	}
	
	public double get_inicio(){
		return inicio;
	}
	
	public Mensagem get_mensagem(){
		return m;
	}
	
	public abstract String toString();
	
	public abstract void processa_evento(Simulador s);
}
