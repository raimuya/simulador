package modelo.eventos;

import controle.Simulador;
import modelo.mensagens.Mensagem;

public class EventoSucessoMensagem extends Evento {
	int sucessos;

	public EventoSucessoMensagem(double inicio, Mensagem m) {
		super(inicio, m);
	}
	
	public void processa_evento(Simulador s){
		sucessos++;
	}

}
