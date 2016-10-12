package modelo.eventos;

import controle.Simulador;
import modelo.mensagens.Mensagem;

public class EventoSucessoMensagem extends Evento {
	int sucessos;

	public EventoSucessoMensagem(double inicio, Mensagem m) {
		super(inicio, m);
	}
	
	public void processa_evento(Simulador s){
		System.out.println(" **************** ENTREGANDO ****************  =====  TNOW: " + Simulador.TNOW());
		System.out.println(toString());
		sucessos++;
	}

	@Override
	public String toString() {
		return "Evento SUCESSO" + m.toString() + 
				"\nInicio em: " + inicio;
	}

}
