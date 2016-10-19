package modelo.eventos;

import controle.Simulador;
import modelo.mensagens.Mensagem;

public class EventoAdiamentoMensagem extends Evento{

	int adiadas;
	
	/**
	 * Construtor de evento de adiamento.
	 * @param inicio	hor�rio em que este evento deve acontecer.
	 * @param m			mensagem que vai ser adiada.
	 */
	public EventoAdiamentoMensagem(double inicio, Mensagem m) {
		super(inicio, m);
	}

	/**
	 * Processamento de um evento de adiamento.
	 * As mensagens adiadas retornam para um novo processamento 
	 * ap�s um tempo de espera que depende de quanto tempo
	 * j� transcorreu desde sua entrada no sistema.
	 * Antes do prazo m�ximo de perman�ncia ser excedido,
	 * as mensagens podem fazer v�rias passagens at�
	 * sa�rem do sistema. Os adiamentos ocorrem em n�mero
	 * reduzido na maioria das dire��es, mas afetam as m�dias
	 * das medidas de desempenho.
	 * 
	 */
	public void processa_evento(Simulador s){
		adiadas++;
		
		switch(m.getDirecao()){
		case LL: Simulador.total_LLA++;
			break;
		case LR: Simulador.total_LRA++;
			break;
		case RL: Simulador.total_RLA++;
			break;
		case RR: Simulador.total_RRA++;
			break;
		}
		
		s.atualiza_area_simulacao("ADIANDO �S " + Simulador.TNOW_STRING() + "\n");
		s.atualiza_area_simulacao(toString() + "\n\n");

		Evento e = s.get_gerador().gera_um_evento_apos_adiamento(this.m);
		int pos = Simulador.addEvento(e);
		
//		s.atualiza_area_simulacao("EVENTO CRIADO �S " + Simulador.TNOW_STRING() + "\n");
//		s.atualiza_area_simulacao(e.toString());
//		s.atualiza_area_simulacao("\nAdicionado na posicao LEF: " + pos + "\n\n");
		
	}

	@Override
	public String toString() {
		return "Evento ADIAMENTO " + m.toString();
	}


}
