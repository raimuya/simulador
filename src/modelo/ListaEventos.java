package modelo;

import java.util.ArrayList;

import modelo.eventos.Evento;

public  class ListaEventos {
	
	static ArrayList<Evento> lista;
	
	public ListaEventos() {
		lista = new ArrayList<Evento>();
	}
	
	//adiciona um novo evento a lista
	//os eventos são adicionados em ordem de 'startTime1
	public int add(Evento evento){
		int index = 0;
		//toString();
		for(Evento e : lista){
			if(e.get_inicio() < evento.get_inicio())
				++index;
		}
		lista.add(index, evento);
		return index;
	}
	
	//retorna o próximo evento eminente da lista de eventos
	public Evento iminente(){
		return lista.remove(0);
	}

	public String toString(){
		String s = " --- Lista --- \n";
		for(Evento e : lista){
			s += e.toString();
		}
		return s;
	}
}
