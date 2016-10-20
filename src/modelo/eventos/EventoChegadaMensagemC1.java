package modelo.eventos;

import controle.Simulador;
import modelo.distribuicoes.Distribuicao;
import modelo.mensagens.Mensagem;

public class EventoChegadaMensagemC1 extends Evento{ //LOCAL

	/**
	 * Construtor de evento de chegada no Centro 1.
	 * @param inicio	horário em que este evento deve acontecer.
	 * @param m			mensagem que deve ir para o Centro 1 (destinos locais).
	 */
	public EventoChegadaMensagemC1(double inicio, Mensagem m) {
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
		//gerar tempo servico = processa_XL
		//programar proxima saida = dentro do sucesso/fracasso/adiamento
		if(s.serv_livre_local > 0){
			s.serv_livre_local--;
			s.atualiza_ocupacao_C1_NOW(Simulador.TNOW());
			
			s.atualiza_area_simulacao("PROCESSANDO EM C1 ÀS " + Simulador.TNOW_STRING() + "\n");
			s.atualiza_area_simulacao(toStringProcessando());
			
			double duracao = 0;			
			switch(m.getDirecao()){
			case LL: 
				switch(m.getDesfecho()){
				case S:
					duracao = (Distribuicao.norm(0.55, 0.05));
					m.add_tempo_no_sistema(duracao);
					Evento lls = new EventoSucessoMensagem(Simulador.TNOW()+duracao, m);
					Simulador.addEvento(lls);
					break;
				case F:
					duracao = (Distribuicao.tria(0.02, 0.05, 0.12));
					m.add_tempo_no_sistema(duracao);
					Evento llf = new EventoFracassoMensagem(Simulador.TNOW()+duracao, m);
					Simulador.addEvento(llf);
					break;
				case A:
					duracao = (Distribuicao.unif(0.06, 0.15));
					m.add_tempo_no_sistema(duracao);
					Evento lla = new EventoAdiamentoMensagem(Simulador.TNOW()+duracao, m);
					Simulador.addEvento(lla);
					break;
				}
				break;
			case RL: 
				switch(m.getDesfecho()){
				case S:
					duracao =(Distribuicao.unif(0.3, 0.11));
					m.add_tempo_no_sistema(duracao);
					Evento rls = new EventoSucessoMensagem(Simulador.TNOW()+duracao, m);
					Simulador.addEvento(rls);
					break;
				case F:
					duracao =(Distribuicao.norm(0.46, 0.05));
					m.add_tempo_no_sistema(duracao);
					Evento rlf = new EventoSucessoMensagem(Simulador.TNOW()+duracao, m);
					Simulador.addEvento(rlf);
					break;
				case A:
					duracao = (Distribuicao.norm(0.72, 0.09));
					m.add_tempo_no_sistema(duracao);
					Evento rla = new EventoAdiamentoMensagem(Simulador.TNOW()+duracao, m);
					Simulador.addEvento(rla);
					break;
				}
				
				break;
			default: break;
			}
			s.serv_livre_local++;
			s.atualiza_ocupacao_C1_NOW(Simulador.TNOW()+duracao);
		} else{
			for(Evento e : s.get_fila_local()){
				this.inicio += e.get_inicio();
			}
			s.add_fila_local(this);
		}
	}


	@Override
	public String toString() {
		return "Evento C1 " + m.toString() + 
				"\nInicia em: " + get_inicio_STRING();
	}
	
	public String toStringProcessando() {
		return "Evento C1 " + m.toString() + "\n\n";
	}
}