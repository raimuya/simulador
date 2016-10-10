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
		System.out.println("Processando " + m.getConteudo());
		if(s.serv_livre_recepcao > 0){
			System.out.println("oi");
		double duracao = 0;
		switch(m.getDirecao()){
		case LL:
			switch(m.getDesfecho()){
				case SUCESSO: duracao = 0.12; break;
				case FRACASSO: duracao = 0.14; break;
				case ADIAMENTO: duracao = 0.11; break;
			}
			m.addDuracao(duracao);
			EventoChegadaMensagemC1 e1 = new EventoChegadaMensagemC1(s.TNOW()+duracao, m);
			s.addEvento(e1);
			break;
		case LR:
			switch(m.getDesfecho()){
				case SUCESSO: duracao = 0.12; break;
				case FRACASSO: duracao = 0.13; break;
				case ADIAMENTO: duracao = 0.15; break;
			}
			m.addDuracao(duracao);
			EventoChegadaMensagemC2 e2 = new EventoChegadaMensagemC2(s.TNOW()+duracao, m);
			s.addEvento(e2);
			break;
		case RL:
			switch(m.getDesfecho()){
				case SUCESSO: duracao = 0.12; break;
				case FRACASSO: duracao = 0.14; break;
				case ADIAMENTO: duracao = 0.11; break;
			}
			m.addDuracao(duracao);
			EventoChegadaMensagemC1 e3 = new EventoChegadaMensagemC1(s.TNOW()+duracao, m);
			s.addEvento(e3);
			break;
		case RR:
			switch(m.getDesfecho()){
				case SUCESSO: duracao = 0.16; break;
				case FRACASSO: duracao = 0.13; break;
				case ADIAMENTO: duracao = 0.16; break;
			}
			m.addDuracao(duracao);
			EventoChegadaMensagemC2 e4 = new EventoChegadaMensagemC2(s.TNOW()+duracao, m);
			s.addEvento(e4);
			break;
		}
		proximo(s);
	} else{
		s.add_fila_recepcao(this);
		s.add_qtd_pessoas_fila_recepcao_NOW();
		
		//fazer isso aqui ou numa "tabela de eventos"
		//gerar direcao = local | remota => TEC
		//gerar destino = direcao proporcao...
	}
	}
	
	void proximo(Simulador s){
		Evento proximo = s.proximo_fila_recepcao();
		if(proximo != null){
			proximo.processa_evento(s);
		}
	}
	
}