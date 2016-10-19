package modelo.eventos;

import controle.Simulador;
import modelo.mensagens.Mensagem;

public class EventoSucessoMensagem extends Evento {
	int sucessos;

	/**
	 * Construtor de evento de sucesso.
	 * @param inicio	horário em que este evento deve acontecer.
	 * @param m			mensagem que vai ser entregue.
	 */
	public EventoSucessoMensagem(double inicio, Mensagem m) {
		super(inicio, m);
	}
	
	public void processa_evento(Simulador s){
		Simulador.total_mensagens_despachadas++;
		
		switch(m.getDirecao()){
		case LL: Simulador.total_LLS++;
			break;
		case LR: Simulador.total_LRS++;
			break;
		case RL: Simulador.total_RLS++;
			break;
		case RR: Simulador.total_RRS++;
			break;
		}
		
		sucessos++;
		
		m.add_tempo_no_sistema(Simulador.TNOW() - inicio);
		s.atualiza_qtd_mensagens_sistema(false, true);
		s.adiciona_duracao_mensagem(m);
		
		s.atualiza_area_simulacao("ENTREGANDO ÀS " + Simulador.TNOW_STRING() + "\n");
		s.atualiza_area_simulacao(toStringProcessando() + "\n\n");
	}

	@Override
	public String toString() {
		return "Evento SUCESSO" + m.toString() + 
				"\nInicia em: " + get_inicio_STRING();
	}
	
	public String toStringProcessando() {
		return "Evento SUCESSO" + m.toString() + "|  Duração: " + m.get_tempo_no_sistema(); 
	}
	

}
