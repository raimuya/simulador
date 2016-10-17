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
		
		m.add_tempo_no_sistema(s.TNOW() - inicio);
		s.atualiza_qtd_mensagens_sistema(false, true);
		
		s.atualiza_area_simulacao("\nFRACASSANDO ÀS " + Simulador.TNOW_STRING() + "\n");
		s.atualiza_area_simulacao(toStringProcessando() + "\n\n");
	}

	@Override
	public String toString() {
		return "Evento FRACASSO" + m.toString() + 
				"\nInicia em: " + get_inicio_STRING();
	}
	
	public String toStringProcessando() {
		return "Evento FRACASSO" + m.toString(); 
	}
}
