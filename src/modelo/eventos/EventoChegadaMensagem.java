package modelo.eventos;

import controle.Simulador;
import modelo.mensagens.Mensagem;

public class EventoChegadaMensagem extends Evento{

	/**
	 * Construtor de evento de chegada no Centro 1.
	 * @param inicio	horário em que este evento deve acontecer.
	 * @param m			mensagem que deve ir para o Centro 1 (destinos locais).
	 */
	public EventoChegadaMensagem(double inicio, Mensagem m) {
		super(inicio, m);
	}
	
	/**
	 * Processamento de um evento de chegada de mensagem ao Centro de Recepção.
	 * Após passar pela recepção, a mensagem é enviada a um dos Centros de Serviço,
	 *  de acordo com seu destino. Assim, todas as mensagens com destino local
	 *  são processadas no Centro de Serviço 1 e as com destino remoto vão ao Centro 2
	 */
	public void processa_evento(Simulador s){
		int pos = 0;
		s.atualiza_qtd_mensagens_sistema(true, false);
		
		double duracao = 0;
		if(s.serv_livre_recepcao > 0){
			s.serv_livre_recepcao--;
			s.atualiza_ocupacao_recepcao_NOW(Simulador.TNOW());
			
			s.atualiza_area_simulacao("PROCESSANDO NA RECEPÇÃO ÀS " + Simulador.TNOW_STRING() + "\n");
			s.atualiza_area_simulacao(toStringProcessando());
		switch(m.getDirecao()){
		case LL:
			switch(m.getDesfecho()){
				case S: duracao = 0.12; break;
				case F: duracao = 0.14; break;
				case A: duracao = 0.11; break;
			}
			this.m.add_tempo_no_sistema(duracao);
			EventoChegadaMensagemC1 e1 = new EventoChegadaMensagemC1(Simulador.TNOW()+duracao, this.m);
			pos = Simulador.addEvento(e1);
			
			s.atualiza_area_simulacao("REDIRECIONANDO PARA C1\n");
			s.atualiza_area_simulacao(e1.toString());
			s.atualiza_area_simulacao("\nAdicionado na posicao LEF: " + pos + "\n\n");
			break;
		case LR:
			switch(m.getDesfecho()){
				case S: duracao = 0.12; break;
				case F: duracao = 0.13; break;
				case A: duracao = 0.15; break;
			}
			this.m.add_tempo_no_sistema(duracao);
			EventoChegadaMensagemC2 e2 = new EventoChegadaMensagemC2(Simulador.TNOW()+duracao, this.m);
			pos = Simulador.addEvento(e2);
			
			s.atualiza_area_simulacao("REDIRECIONANDO PARA C2\n");
			s.atualiza_area_simulacao(e2.toString());
			s.atualiza_area_simulacao("\nAdicionado na posicao LEF: " + pos + "\n\n");
			break;
		case RL:
			switch(m.getDesfecho()){
				case S: duracao = 0.12; break;
				case F: duracao = 0.14; break;
				case A: duracao = 0.11; break;
			}
			this.m.add_tempo_no_sistema(duracao);
			EventoChegadaMensagemC1 e3 = new EventoChegadaMensagemC1(Simulador.TNOW()+duracao, this.m);
			pos = Simulador.addEvento(e3);
			
			s.atualiza_area_simulacao("REDIRECIONANDO PARA C1\n");
			s.atualiza_area_simulacao(e3.toString());
			s.atualiza_area_simulacao("\nAdicionado na posicao LEF: " + pos + "\n\n");
			break;
		case RR:
			switch(m.getDesfecho()){
				case S: duracao = 0.16; break;
				case F: duracao = 0.13; break;
				case A: duracao = 0.16; break;
			}
			m.add_tempo_no_sistema(duracao);
			EventoChegadaMensagemC2 e4 = new EventoChegadaMensagemC2(Simulador.TNOW()+duracao, m);
			pos = Simulador.addEvento(e4);
			
			s.atualiza_area_simulacao("REDIRECIONANDO PARA C2\n");
			s.atualiza_area_simulacao(e4.toString());
			s.atualiza_area_simulacao("\nAdicionado na posicao LEF: " + pos + "\n\n");
			break;
		}
		s.serv_livre_recepcao++;
		s.atualiza_ocupacao_recepcao_NOW(Simulador.TNOW()+duracao);
	} else{
		for(Evento e : s.get_fila_recepcao()){
			this.inicio += e.get_inicio();
		}
		s.add_fila_recepcao(this);
		}
	}

	@Override
	public String toString() {
		return "Evento CHEGADA " + m.toString() + 
				"\nInicia em: " + get_inicio_STRING();
	}
	
	public String toStringProcessando(){
		return "Evento CHEGADA " + m.toString() + "\n\n";
	}
}