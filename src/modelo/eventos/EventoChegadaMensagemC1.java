package modelo.eventos;

import controle.Simulador;
import modelo.distribuicoes.Distribuicao;
import modelo.mensagens.Mensagem;

public class EventoChegadaMensagemC1 extends Evento{ //LOCAL

	public EventoChegadaMensagemC1(double inicio) {
		super(inicio);
	}

	void processa_evento(Simulador s, Mensagem m){
		//se tem servidor livre então
		//gerar tempo servico = processa_XL
		//programar proxima saida = dentro do sucesso/fracasso/adiamento
		if(s.serv_livre_local > 0){
			s.serv_livre_local--;
			switch(m.getDirecao()){
			case LL:
				processa_ll(m);
				break;
			case RL:
				processa_rl(m);
				break;
			default:
				break;
			}
		}
		//ir para a fila
		//gerar tempo de chegada
		//programar próxima chegada
		else{
			s.add_fila_local(m);
			s.add_qtd_pessoas_fila_local(inicio);
			
			//fazer isso aqui ou numa "tabela de eventos"
			//gerar direcao = local | remota => TEC
			//gerar destino = direcao proporcao...
		}
	}

	void processa_ll(Mensagem m) {
		switch(m.getDesfecho()){
		case SUCESSO:
			m.addDuracao(Distribuicao.norm(0.55, 0.05));
			//TODO: gerar evento sucesso
			break;
		case FRACASSO:
			m.addDuracao(Distribuicao.tria(0.02, 0.05, 0.12));
			//TODO: gerar evento fracasso
			break;
		case ADIAMENTO:
			m.addDuracao(Distribuicao.unif(0.06, 0.15));
			//TODO: gerar evento adiamento
			break;
		}
	}
	
	void processa_rl(Mensagem m) {
		switch(m.getDesfecho()){
		case SUCESSO:
			m.addDuracao(Distribuicao.unif(0.3, 0.11));
			//TODO: gerar evento sucesso
			break;
		case FRACASSO:
			m.addDuracao(Distribuicao.norm(0.46, 0.05));
			//TODO: gerar evento fracasso
			break;
		case ADIAMENTO:
			m.addDuracao(Distribuicao.norm(0.72, 0.09));
			//TODO: gerar evento adiamento
			break;
		}
	}
}