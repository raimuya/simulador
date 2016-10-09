package modelo.eventos;

import controle.Simulador;
import modelo.distribuicoes.Distribuicao;
import modelo.mensagens.Mensagem;

public class EventoChegadaMensagemC2 extends Evento{ //REMOTO

	public EventoChegadaMensagemC2(double inicio) {
		super(inicio);
	}

	void processa_evento(Simulador s, Mensagem m){
		//se tem servidor livre então
		//gerar tempo servico = processa_XR
		//programar proxima saida = dentro do sucesso/fracasso/adiamento
		if(s.serv_livre_remoto > 0){
			s.serv_livre_remoto--;
			switch(m.getDirecao()){
			case LR:
				processaLR(m);
				break;
			case RR:
				processaRR(m);
				break;
			default:
				break;
			}
		}

		else{
			s.add_fila_remoto(m);
			s.add_qtd_pessoas_fila_remoto(inicio);
			
			//fazer isso aqui ou numa "tabela de eventos"
			//gerar direcao = local | remota => TEC
			//gerar destino = direcao proporcao...
		}
	}
	
	void processaLR(Mensagem m){
		switch(m.getDesfecho()){
		case SUCESSO:
			m.addDuracao(Distribuicao.norm(0.65, 0.04));
			//TODO: gerar evento sucesso
			break;
		case FRACASSO:
			m.addDuracao(Distribuicao.unif(0.16, 0.25));
			//TODO: gerar evento fracasso
			break;
		case ADIAMENTO:
			m.addDuracao(Distribuicao.tria(0.05, 0.07, 0.10));
			//TODO: gerar evento adiamento
			break;
		}
	}

	void processaRR(Mensagem m){
		switch(m.getDesfecho()){
		case SUCESSO:
			m.addDuracao(Distribuicao.unif(0.09, 0.18));
			//TODO: gerar evento sucesso
			break;
		case FRACASSO:
			m.addDuracao(Distribuicao.tria(0.08, 0.15, 0.22));
			//TODO: gerar evento fracasso
			break;
		case ADIAMENTO:
			m.addDuracao(Distribuicao.norm(0.63, 0.04));
			//TODO: gerar evento adiamento
			break;
		}
	}	
}