package modelo.eventos;

import controle.Simulador;
import modelo.distribuicoes.Distribuicao;
import modelo.mensagens.Mensagem;

public class EventoChegadaMensagemC2 extends Evento{ //REMOTO

	public EventoChegadaMensagemC2(double inicio, Mensagem m) {
		super(inicio, m);
	}

	public void processa_evento(Simulador s){
		
		//se tem servidor livre então
		//gerar tempo servico = processa_XR
		//programar proxima saida = dentro do sucesso/fracasso/adiamento
		if(s.serv_livre_remoto > 0){
			System.out.println("\n **************** C2 ****************  =====  TNOW: " + Simulador.TNOW());
			System.out.println(toString());
			s.serv_livre_remoto--;
			switch(m.getDirecao()){
			case LR: processaLR(s); break;
			case RR: processaRR(s); break;
			default:
			}
			proximo(s);
		}
		else{
			for(Evento e : s.get_fila_remoto()){
				this.inicio += e.get_inicio();
			}
			s.add_fila_remoto(this);
			s.add_qtd_pessoas_fila_remoto_NOW();
		}
	}
	
	void processaLR(Simulador s){
		double duracao = 0;
		switch(m.getDesfecho()){
		case SUCESSO:
			duracao = Distribuicao.norm(0.65, 0.04);
			m.add_tempo_no_sistema(duracao);
			EventoSucessoMensagem lrs = new EventoSucessoMensagem(Simulador.TNOW(), m);
			Simulador.addEvento(lrs);
			break;
		case FRACASSO:
			duracao = Distribuicao.unif(0.16, 0.25);
			m.add_tempo_no_sistema(duracao);
			EventoFracassoMensagem lrf = new EventoFracassoMensagem(Simulador.TNOW(), m);
			Simulador.addEvento(lrf);
			break;
		case ADIAMENTO:
			duracao = Distribuicao.tria(0.05, 0.07, 0.10);
			m.add_tempo_no_sistema(duracao);
			EventoAdiamentoMensagem lra = new EventoAdiamentoMensagem(Simulador.TNOW(), m);
			Simulador.addEvento(lra);
			break;
		}
	}

	void processaRR(Simulador s){
		double duracao = 0;
		switch(m.getDesfecho()){
		case SUCESSO:
			duracao = Distribuicao.unif(0.09, 0.18);
			m.add_tempo_no_sistema(duracao);
			EventoSucessoMensagem rrs = new EventoSucessoMensagem(Simulador.TNOW(), m);
			Simulador.addEvento(rrs);
			break;
		case FRACASSO:
			duracao = Distribuicao.tria(0.08, 0.15, 0.22);
			m.add_tempo_no_sistema(duracao);
			EventoFracassoMensagem rrf = new EventoFracassoMensagem(Simulador.TNOW(), m);
			Simulador.addEvento(rrf);
			break;
		case ADIAMENTO:
			duracao = Distribuicao.norm(0.63, 0.04);
			m.add_tempo_no_sistema(duracao);
			EventoAdiamentoMensagem rra = new EventoAdiamentoMensagem(Simulador.TNOW(), m);
			Simulador.addEvento(rra);
			break;
		}
	}
	
	
	void proximo(Simulador s){
		Evento proximo = s.proximo_fila_remoto();
		if(proximo != null){
			proximo.processa_evento(s);
		}
	}

	@Override
	public String toString() {
		return "Evento C2 " + m.getConteudo() + 
				"\nInicio em: " + inicio;
	}
	
}