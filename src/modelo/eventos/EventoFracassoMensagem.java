package modelo.eventos;

import controle.Simulador;
import modelo.mensagens.Mensagem;

public class EventoFracassoMensagem extends Evento{
	int fracassos;
	
	/**
	 * Construtor de evento de fracasso.
	 * @param inicio	horário em que este evento deve acontecer.
	 * @param m			mensagem que deve fracassar.
	 */
	public EventoFracassoMensagem(double inicio, Mensagem m) {
		super(inicio, m);
	}
	
	public void processa_evento(Simulador s){
		Simulador.total_mensagens_despachadas++;
		
		switch(m.getDirecao()){
		case LL: Simulador.total_LLF++;
			break;
		case LR: Simulador.total_LRF++;
			break;
		case RL: Simulador.total_RLF++;
			break;
		case RR: Simulador.total_RRF++;
			break;
		}
		
		fracassos++;
		
		m.add_tempo_no_sistema(Simulador.TNOW() - inicio);
		s.atualiza_qtd_mensagens_sistema(false, true);
		s.adiciona_duracao_mensagem(m);
		
		s.atualiza_area_simulacao("\nFRACASSANDO ÀS " + Simulador.TNOW_STRING() + "\n");
		s.atualiza_area_simulacao(toStringProcessando() + "\n\n");
	}

	@Override
	public String toString() {
		return "Evento FRACASSO" + m.toString() + 
				"\nInicia em: " + get_inicio_STRING();
	}
	
	public String toStringProcessando() {
		return "Evento FRACASSO" + m.toString() + "|  Duração: " + m.get_tempo_no_sistema(); 
	}
}
