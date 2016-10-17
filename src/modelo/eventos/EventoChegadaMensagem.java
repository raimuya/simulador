package modelo.eventos;

import controle.Simulador;
import modelo.mensagens.Mensagem;

public class EventoChegadaMensagem extends Evento{

	public EventoChegadaMensagem(double inicio, Mensagem m) {
		super(inicio, m);
	}
	
	//dúvida:
	/*se eu tenho 3 filas (recepcao, c1, c2)
	 * após despachar da recepcao eu gero uma nova mensagem ja?
	 * ou ela é gerado somente após passar pelo c1, ter seu resultado concluido?
	 */
	public void processa_evento(Simulador s){
		int pos = 0;
		s.atualiza_qtd_mensagens_sistema(true, false);
		
		if(s.serv_livre_recepcao > 0){
			s.serv_livre_recepcao--;
			s.atualiza_ocupacao_recepcao_NOW();
			
			s.atualiza_area_simulacao("PROCESSANDO NA RECEPÇÃO ÀS " + Simulador.TNOW_STRING() + "\n");
			s.atualiza_area_simulacao(toStringProcessando());
		double duracao = 0;
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
	} else{
		for(Evento e : s.get_fila_recepcao()){
			this.inicio += e.get_inicio();
		}
		s.add_fila_recepcao(this);
	}
		m.add_tempo_no_sistema(s.TNOW() - inicio);
		s.serv_livre_recepcao++;
		s.atualiza_ocupacao_recepcao_NOW();
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