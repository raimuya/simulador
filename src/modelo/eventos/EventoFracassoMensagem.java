package modelo.eventos;

import controle.Simulador;
import modelo.mensagens.Mensagem;

public class EventoFracassoMensagem extends Evento{
	int fracassos;
	
	public EventoFracassoMensagem(double inicio, Mensagem m) {
		super(inicio, m);
	}
	
	public void processa_evento(Simulador s){
		System.out.println("FRACASSANDO   =====  TNOW: " + Simulador.TNOW());
		System.out.println(toString());
		fracassos++;
	}

	@Override
	public String toString() {
		return "Evento FRACASSO" + m.toString() + 
				"\nInicio em: " + inicio;
	}
}
