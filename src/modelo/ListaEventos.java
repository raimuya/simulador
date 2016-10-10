package modelo;

import java.util.ArrayList;

import modelo.eventos.Evento;

public class ListaEventos {
	
	static ArrayList<Evento> lista;
	int atual = 0;
	
	public ListaEventos() {
		lista = new ArrayList<Evento>();
	}
	
	//adiciona um novo evento a lista
	//os eventos são adicionados em ordem de 'startTime1
	public void add(Evento evento){
		lista.add(evento);
	}
	
	//retorna o próximo evento eminente da lista de eventos
	public Evento proximo(){
		return lista.get(atual++);
	}
	
	//retorna a quantidade de eventos na lista de eventos
	public int total_eventos(){
		return lista.size();
	}
}
