package modelo.eventos;

import controle.Simulador;
import modelo.distribuicoes.Distribuicao;
import modelo.mensagens.Mensagem;

public class EventoChegadaMensagemC2 extends Evento{ //REMOTO

	/**
	 * Construtor de evento de chegada no Centro 2.
	 * @param inicio	hor�rio em que este evento deve acontecer.
	 * @param m			mensagem que deve ir para o Centro 2 (destinos remotos).
	 */
	public EventoChegadaMensagemC2(double inicio, Mensagem m) {
		super(inicio, m);
	}

	/**
	 * Processamento de um evento de chegada de mensagem ao Centro 1 (destino local).
	 * As mensagens processadas podem resultar na ocorr�ncia de um sucesso,
	 * caso que corresponde � entrega ao destinat�rio.
	 * Podem, tamb�m, resultar em fracasso, caso em que a mensagem � devolvida ao remetente.
	 * Existem ainda situa��es em que uma mensagem adia sua sa�da do sistema,
	 * elas retornam para um novo processamento ap�s um tempo de espera que depende
	 * de quanto tempo j� transcorreu desde sua entrada no sistema
	 */
	public void processa_evento(Simulador s){
		//se tem servidor livre ent�o
		//gerar tempo servico = processa_XR
		//programar proxima saida = dentro do sucesso/fracasso/adiamento
		s.atualiza_ocupacao_recepcao_NOW(false, true);
		
		if(s.serv_ocupado_remoto < s.serv_total_remoto){
			s.atualiza_ocupacao_C2_NOW(true, false);
			
			s.atualiza_area_simulacao("PROCESSANDO EM C2 �S " + Simulador.TNOW_STRING() + "\n");
			s.atualiza_area_simulacao(toStringProcessando());
			
			double duracao = 0;
			switch(m.getDirecao()){
			case LR: 
				switch(m.getDesfecho()){
				case S:
					duracao = Distribuicao.norm(0.65, 0.04);
					m.add_tempo_no_sistema(duracao);
					EventoSucessoMensagem lrs = new EventoSucessoMensagem(Simulador.TNOW() + duracao, m);
					Simulador.addEvento(lrs);
					break;
				case F:
					duracao = Distribuicao.unif(0.16, 0.25);
					m.add_tempo_no_sistema(duracao);
					EventoFracassoMensagem lrf = new EventoFracassoMensagem(Simulador.TNOW() + duracao, m);
					Simulador.addEvento(lrf);
					break;
				case A:
					duracao = Distribuicao.tria(0.05, 0.07, 0.10);
					m.add_tempo_no_sistema(duracao);
					EventoAdiamentoMensagem lra = new EventoAdiamentoMensagem(Simulador.TNOW() + duracao, m);
					Simulador.addEvento(lra);
					break;
				}
				break;
				
			case RR: 
				switch(m.getDesfecho()){
				case S:
					duracao = Distribuicao.unif(0.09, 0.18);
					m.add_tempo_no_sistema(duracao);
					EventoSucessoMensagem rrs = new EventoSucessoMensagem(Simulador.TNOW() + duracao, m);
					Simulador.addEvento(rrs);
					break;
				case F:
					duracao = Distribuicao.tria(0.08, 0.15, 0.22);
					m.add_tempo_no_sistema(duracao);
					EventoFracassoMensagem rrf = new EventoFracassoMensagem(Simulador.TNOW() + duracao, m);
					Simulador.addEvento(rrf);
					break;
				case A:
					duracao = Distribuicao.norm(0.63, 0.04);
					m.add_tempo_no_sistema(duracao);
					EventoAdiamentoMensagem rra = new EventoAdiamentoMensagem(Simulador.TNOW() + duracao, m);
					Simulador.addEvento(rra);
					break;
				}
				break;
			default:
			}
		}
		else{
			for(Evento e : s.get_fila_remoto()){
				this.inicio += e.get_inicio();
			}
			s.add_fila_remoto(this);
		}
	}
	
	@Override
	public String toString() {
		return m.toString() + 
				"\nInicia em: " + get_inicio_STRING();
	}
	
	public String toStringProcessando() {
		return m.toString() + "\n\n"; 
	}
	
}