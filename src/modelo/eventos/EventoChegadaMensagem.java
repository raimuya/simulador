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
		if(s.serv_livre_recepcao > 0){
			System.out.println("\n **************** RECEPCIONANDO ****************  TNOW: " + Simulador.TNOW());
			System.out.println(toString());
		double duracao = 0;
		switch(m.getDirecao()){
		case LL:
			switch(m.getDesfecho()){
				case SUCESSO: duracao = 0.12; break;
				case FRACASSO: duracao = 0.14; break;
				case ADIAMENTO: duracao = 0.11; break;
			}
			this.m.add_tempo_no_sistema(duracao);
			EventoChegadaMensagemC1 e1 = new EventoChegadaMensagemC1(Simulador.TNOW()+duracao, this.m);
			Simulador.addEvento(e1);
			break;
		case LR:
			switch(m.getDesfecho()){
				case SUCESSO: duracao = 0.12; break;
				case FRACASSO: duracao = 0.13; break;
				case ADIAMENTO: duracao = 0.15; break;
			}
			this.m.add_tempo_no_sistema(duracao);
			EventoChegadaMensagemC2 e2 = new EventoChegadaMensagemC2(Simulador.TNOW()+duracao, this.m);
			Simulador.addEvento(e2);
			break;
		case RL:
			switch(m.getDesfecho()){
				case SUCESSO: duracao = 0.12; break;
				case FRACASSO: duracao = 0.14; break;
				case ADIAMENTO: duracao = 0.11; break;
			}
			this.m.add_tempo_no_sistema(duracao);
			EventoChegadaMensagemC1 e3 = new EventoChegadaMensagemC1(Simulador.TNOW()+duracao, this.m);
			Simulador.addEvento(e3);
			break;
		case RR:
			switch(m.getDesfecho()){
				case SUCESSO: duracao = 0.16; break;
				case FRACASSO: duracao = 0.13; break;
				case ADIAMENTO: duracao = 0.16; break;
			}
			m.add_tempo_no_sistema(duracao);
			EventoChegadaMensagemC2 e4 = new EventoChegadaMensagemC2(Simulador.TNOW()+duracao, m);
			Simulador.addEvento(e4);
			break;
		}
	} else{
		for(Evento e : s.get_fila_recepcao()){
			this.inicio += e.get_inicio();
		}
		s.add_fila_recepcao(this);
		s.add_qtd_pessoas_fila_recepcao_NOW();
		
		//fazer isso aqui ou numa "tabela de eventos"
		//gerar direcao = local | remota => TEC
		//gerar destino = direcao proporcao...
	}
	}

	@Override
	public String toString() {
		return "Evento CHEGADA " + m.toString() + 
				"\nInicio em: " + inicio;
	}
	
//	void proximo(Simulador s){
//		
//		Evento proximo = s.proximo_fila_recepcao();
//		if(proximo != null){
//			System.out.println("oioioioiiooi");
//			proximo.processa_evento(s);
//		}
//	}
	
}