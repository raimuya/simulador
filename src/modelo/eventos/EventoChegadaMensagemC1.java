package modelo.eventos;

import controle.Simulador;
import modelo.distribuicoes.Distribuicao;
import modelo.mensagens.Mensagem;

public class EventoChegadaMensagemC1 extends Evento{ //LOCAL

	public EventoChegadaMensagemC1(double inicio, Mensagem m) {
		super(inicio, m);
	}

	public void processa_evento(Simulador s){
		
		//se tem servidor livre então
		//gerar tempo servico = processa_XL
		//programar proxima saida = dentro do sucesso/fracasso/adiamento
		if(s.serv_livre_local > 0){
			System.out.println("\n **************** C1 ****************  =====  TNOW: " + Simulador.TNOW());
			System.out.println(toString());
			s.serv_livre_local--;
			switch(m.getDirecao()){
			case LL: processa_ll(s); break;
			case RL: processa_rl(s); break;
			default: break;
			}
		} else{
			for(Evento e : s.get_fila_local()){
				this.inicio += e.get_inicio();
			}
			s.add_fila_local(this);
			s.add_qtd_pessoas_fila_local_NOW();
		}
	}

	void processa_ll(Simulador s) {
		double duracao = 0;
		switch(m.getDesfecho()){
		case SUCESSO:
			duracao = (Distribuicao.norm(0.55, 0.05));
			m.add_tempo_no_sistema(duracao);
			Evento lls = new EventoSucessoMensagem(Simulador.TNOW()+duracao, m);
			Simulador.addEvento(lls);
			break;
		case FRACASSO:
			duracao = (Distribuicao.tria(0.02, 0.05, 0.12));
			m.add_tempo_no_sistema(duracao);
			Evento llf = new EventoFracassoMensagem(Simulador.TNOW()+duracao, m);
			Simulador.addEvento(llf);
			break;
		case ADIAMENTO:
			duracao = (Distribuicao.unif(0.06, 0.15));
			m.add_tempo_no_sistema(duracao);
			Evento lla = new EventoAdiamentoMensagem(Simulador.TNOW()+duracao, m);
			Simulador.addEvento(lla);
			break;
		}
	}
	
	void processa_rl(Simulador s) {
		double duracao = 0;
		switch(m.getDesfecho()){
		case SUCESSO:
			duracao =(Distribuicao.unif(0.3, 0.11));
			m.add_tempo_no_sistema(duracao);
			Evento rls = new EventoSucessoMensagem(Simulador.TNOW()+duracao, m);
			Simulador.addEvento(rls);
			break;
		case FRACASSO:
			duracao =(Distribuicao.norm(0.46, 0.05));
			m.add_tempo_no_sistema(duracao);
			Evento rlf = new EventoSucessoMensagem(Simulador.TNOW()+duracao, m);
			Simulador.addEvento(rlf);
			break;
		case ADIAMENTO:
			duracao = (Distribuicao.norm(0.72, 0.09));
			m.add_tempo_no_sistema(duracao);
			Evento rla = new EventoAdiamentoMensagem(Simulador.TNOW()+duracao, m);
			Simulador.addEvento(rla);
			break;
		}
	}

	@Override
	public String toString() {
		return "Evento C1 " + m.getConteudo() + 
				"\nInicio em: " + inicio;
	}
	
//	void proximo(Simulador s){
//		Evento proximo = s.proximo_fila_local();
//		if(proximo != null){
//			proximo.processa_evento(s);
//		}
//	}
	
}