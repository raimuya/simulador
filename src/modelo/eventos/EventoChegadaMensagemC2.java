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
			s.atualiza_ocupacao_C2_NOW(Simulador.TNOW());
			
			s.atualiza_area_simulacao("PROCESSANDO EM C2 ÀS " + Simulador.TNOW_STRING() + "\n");
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
			s.serv_livre_remoto++;
			s.atualiza_ocupacao_C2_NOW(Simulador.TNOW()+duracao);
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
		return "Evento C2 " + m.toString() + 
				"\nInicia em: " + get_inicio_STRING();
	}
	
	public String toStringProcessando() {
		return "Evento C2 " + m.toString() + "\n\n"; 
	}
	
}