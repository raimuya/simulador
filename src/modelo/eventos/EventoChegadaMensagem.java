package modelo.eventos;

import controle.Simulador;
import modelo.mensagens.Mensagem;

public class EventoChegadaMensagem extends Evento{

	public EventoChegadaMensagem(double inicio) {
		super(inicio);
	}
	
	//dúvida:
	/*se eu tenho 3 filas (recepcao, c1, c2)
	 * após despachar da recepcao eu gero uma nova mensagem ja?
	 * ou ela é gerado somente após passar pelo c1, ter seu resultado concluido?
	 * 
	 */
	void processa_evento(Simulador s, Mensagem m){
		if(s.serv_livre_recepcao > 0){
			switch(m.getDirecao()){
			case LL:
				switch(m.getDesfecho()){
				case SUCESSO:
					m.addDuracao(0.12);
				case FRACASSO:
					m.addDuracao(0.14);
				case ADIAMENTO:
					m.addDuracao(0.11);
				}
				
				//TODO arrumar o tempo
				EventoChegadaMensagemC1 e1 = new EventoChegadaMensagemC1(inicio);
				s.addEvento(e1);
				
				break;
				
			case LR:
				switch(m.getDesfecho()){
				case SUCESSO:
					m.addDuracao(0.12);
				case FRACASSO:
					m.addDuracao(0.13);
				case ADIAMENTO:
					m.addDuracao(0.15);
				}
				//TODO arrumar o tempo
				EventoChegadaMensagemC2 e2 = new EventoChegadaMensagemC2(inicio);
				s.addEvento(e2);
				
				break;

			case RL:
				switch(m.getDesfecho()){
				case SUCESSO:
					m.addDuracao(0.12);
				case FRACASSO:
					m.addDuracao(0.14);
				case ADIAMENTO:
					m.addDuracao(0.11);
				}
				
				//TODO arrumar o tempo
				EventoChegadaMensagemC1 e3 = new EventoChegadaMensagemC1(inicio);
				s.addEvento(e3);
				
				break;
				
			case RR:
				switch(m.getDesfecho()){
				case SUCESSO:
					m.addDuracao(0.16);
				case FRACASSO:
					m.addDuracao(0.13);
				case ADIAMENTO:
					m.addDuracao(0.16);
				}
				
				//TODO arrumar o tempo
				EventoChegadaMensagemC2 e4 = new EventoChegadaMensagemC2(inicio);
				s.addEvento(e4);
				
				break;
			}
		}
		else{
			s.add_fila_recepcao(m);
			s.add_qtd_pessoas_fila_recepcao(inicio);
			
			//fazer isso aqui ou numa "tabela de eventos"
			//gerar direcao = local | remota => TEC
			//gerar destino = direcao proporcao...
		}
	}
}