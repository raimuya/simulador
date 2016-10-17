package modelo.eventos;

import controle.Simulador;
import modelo.mensagens.Mensagem;

public class EventoAdiamentoMensagem extends Evento{

	int adiadas;
	
	public EventoAdiamentoMensagem(double inicio, Mensagem m) {
		super(inicio, m);
	}

	//mensagens adiadas retornam para um novo processamento
	//ap�s um tempo de espera que depende de quanto tempo
	//j� transcorreu desde sua entrada no sistema.
	//Antes do prazo m�ximo de perman�ncia ser excedido,
	//as mensagens podem fazer v�rias passagens at�
	//sa�rem do sistema. Os adiamentos ocorrem em n�mero
	//reduzido na maioria das dire��es, mas afetam as m�dias
	//das medidas de desempenho.
	public void processa_evento(Simulador s){
		adiadas++;
		
		m.add_tempo_no_sistema(s.TNOW() - inicio);
		
		s.atualiza_area_simulacao("\nADIANDO �S " + Simulador.TNOW_STRING() + "\n");
		s.atualiza_area_simulacao(toString());

		Evento e = s.get_gerador().gera_um_evento_apos_adiamento(this.m);
		int pos = Simulador.addEvento(e);
		
//		s.atualiza_area_simulacao("EVENTO CRIADO �S " + Simulador.TNOW_STRING() + "\n");
//		s.atualiza_area_simulacao(e.toString());
//		s.atualiza_area_simulacao("\nAdicionado na posicao LEF: " + pos + "\n\n");
		
	}

	@Override
	public String toString() {
		return "Evento ADIAMENTO " + m.toString() + 
				"\nInicia em: " + get_inicio_STRING();
	}


}
