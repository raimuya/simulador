package modelo.eventos;

import controle.Simulador;
import modelo.mensagens.Mensagem;

public class EventoFracassoMensagem extends Evento{
	int fracassos;
	
	public EventoFracassoMensagem(double inicio, Mensagem m) {
		super(inicio, m);
	}
	
	public void processa_evento(Simulador s){
		fracassos++;
	}


}
