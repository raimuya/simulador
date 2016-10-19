package modelo.eventos;

import controle.Simulador;
import modelo.distribuicoes.Distribuicao;
import modelo.mensagens.Mensagem;

public class EventoChegadaMensagemC2 extends Evento{ //REMOTO

	/**
	 * Construtor de evento de chegada no Centro 2.
	 * @param inicio	horário em que este evento deve acontecer.
	 * @param m			mensagem que deve ir para o Centro 2 (destinos remotos).
	 */
	public EventoChegadaMensagemC2(double inicio, Mensagem m) {
		super(inicio, m);
	}

	/**
	 * Processamento de um evento de chegada de mensagem ao Centro 1 (destino local).
	 * As mensagens processadas podem resultar na ocorrência de um sucesso,
	 * caso que corresponde à entrega ao destinatário.
	 * Podem, também, resultar em fracasso, caso em que a mensagem é devolvida ao remetente.
	 * Existem ainda situações em que uma mensagem adia sua saída do sistema,
	 * elas retornam para um novo processamento após um tempo de espera que depende
	 * de quanto tempo já transcorreu desde sua entrada no sistema
	 */
	public void processa_evento(Simulador s){
		//se tem servidor livre então
		//gerar tempo servico = processa_XR
		//programar proxima saida = dentro do sucesso/fracasso/adiamento
		if(s.serv_livre_remoto > 0){
			s.serv_livre_remoto--;
			s.atualiza_ocupacao_C2_NOW();
			s.atualiza_area_simulacao("PROCESSANDO EM C2 ÀS " + Simulador.TNOW_STRING() + "\n");
			s.atualiza_area_simulacao(toStringProcessando());
			
			switch(m.getDirecao()){
			case LR: processaLR(s); break;
			case RR: processaRR(s); break;
			default:
			}
		}
		else{
			for(Evento e : s.get_fila_remoto()){
				this.inicio += e.get_inicio();
			}
			s.add_fila_remoto(this);
		}
		m.add_tempo_no_sistema(s.TNOW() - inicio);
		s.serv_livre_remoto++;
		s.atualiza_ocupacao_C2_NOW();
	}
	
	void processaLR(Simulador s){
		double duracao = 0;
		int pos = 0;
		switch(m.getDesfecho()){
		case S:
			duracao = Distribuicao.norm(0.65, 0.04);
			m.add_tempo_no_sistema(duracao);
			EventoSucessoMensagem lrs = new EventoSucessoMensagem(Simulador.TNOW(), m);
			pos = Simulador.addEvento(lrs);
			
//			s.atualiza_area_simulacao("\n\nEVENTO CRIADO ÀS " + Simulador.TNOW_STRING() + "\n");
//			s.atualiza_area_simulacao(lrs.toString());
//			s.atualiza_area_simulacao("\nAdicionado na posicao LEF: " + pos + "\n\n");
			break;
		case F:
			duracao = Distribuicao.unif(0.16, 0.25);
			m.add_tempo_no_sistema(duracao);
			EventoFracassoMensagem lrf = new EventoFracassoMensagem(Simulador.TNOW(), m);
			pos = Simulador.addEvento(lrf);
			
//			s.atualiza_area_simulacao("\n\nEVENTO CRIADO ÀS " + Simulador.TNOW_STRING() + "\n");
//			s.atualiza_area_simulacao(lrf.toString());
//			s.atualiza_area_simulacao("\nAdicionado na posicao LEF: " + pos + "\n\n");
			break;
		case A:
			duracao = Distribuicao.tria(0.05, 0.07, 0.10);
			m.add_tempo_no_sistema(duracao);
			EventoAdiamentoMensagem lra = new EventoAdiamentoMensagem(Simulador.TNOW(), m);
			pos = Simulador.addEvento(lra);
			
//			s.atualiza_area_simulacao("\n\nEVENTO CRIADO ÀS " + Simulador.TNOW_STRING() + "\n");
//			s.atualiza_area_simulacao(lra.toString());
//			s.atualiza_area_simulacao("\nAdicionado na posicao LEF: " + pos + "\n\n");
			break;
		}
	}

	void processaRR(Simulador s){
		double duracao = 0;
		int pos = 0;
		switch(m.getDesfecho()){
		case S:
			duracao = Distribuicao.unif(0.09, 0.18);
			m.add_tempo_no_sistema(duracao);
			EventoSucessoMensagem rrs = new EventoSucessoMensagem(Simulador.TNOW(), m);
			pos = Simulador.addEvento(rrs);
			
//			s.atualiza_area_simulacao("\n\nEVENTO CRIADO ÀS " + Simulador.TNOW_STRING() + "\n");
//			s.atualiza_area_simulacao(rrs.toString());
//			s.atualiza_area_simulacao("\nAdicionado na posicao LEF: " + pos + "\n\n");
			break;
		case F:
			duracao = Distribuicao.tria(0.08, 0.15, 0.22);
			m.add_tempo_no_sistema(duracao);
			EventoFracassoMensagem rrf = new EventoFracassoMensagem(Simulador.TNOW(), m);
			pos = Simulador.addEvento(rrf);
			
//			s.atualiza_area_simulacao("\n\nEVENTO CRIADO ÀS " + Simulador.TNOW_STRING() + "\n");
//			s.atualiza_area_simulacao(rrf.toString());
//			s.atualiza_area_simulacao("\nAdicionado na posicao LEF: " + pos + "\n\n");
			break;
		case A:
			duracao = Distribuicao.norm(0.63, 0.04);
			m.add_tempo_no_sistema(duracao);
			EventoAdiamentoMensagem rra = new EventoAdiamentoMensagem(Simulador.TNOW(), m);
			pos = Simulador.addEvento(rra);

//			s.atualiza_area_simulacao("\n\nEVENTO CRIADO ÀS " + Simulador.TNOW_STRING() + "\n");
//			s.atualiza_area_simulacao(rra.toString());
//			s.atualiza_area_simulacao("\nAdicionado na posicao LEF: " + pos + "\n\n");
			break;
		}
	}

	@Override
	public String toString() {
		return "Evento C2 " + m.toString() + 
				"\nInicia em: " + get_inicio_STRING();
	}
	
	public String toStringProcessando() {
		return "Evento C2 " + m.toString() + "\n\n"; 
	}

	
	
}