package modelo.eventos;

import controle.Simulador;
import modelo.mensagens.DirecaoMensagem;
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
		if(m.getDirecao() == DirecaoMensagem.LL | m.getDirecao() == DirecaoMensagem.RL){
			s.serv_ocupado_local++;
			s.atualiza_ocupacao_C1_NOW(false, true);
		}
		if(m.getDirecao() == DirecaoMensagem.LR | m.getDirecao() == DirecaoMensagem.RR){
			s.atualiza_ocupacao_C2_NOW(false, true);
		}
		
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
		return "SUCESSO " + m.toString() + "|  Duração: " + Simulador.tres_digitos.format(m.get_tempo_no_sistema()); 
	}
	

}
