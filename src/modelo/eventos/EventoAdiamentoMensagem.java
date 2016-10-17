package modelo.eventos;

import controle.Simulador;
import modelo.mensagens.Mensagem;

public class EventoAdiamentoMensagem extends Evento{

	int adiadas;
	
	public EventoAdiamentoMensagem(double inicio, Mensagem m) {
		super(inicio, m);
	}

	//mensagens adiadas retornam para um novo processamento
	//após um tempo de espera que depende de quanto tempo
	//já transcorreu desde sua entrada no sistema.
	//Antes do prazo máximo de permanência ser excedido,
	//as mensagens podem fazer várias passagens até
	//saírem do sistema. Os adiamentos ocorrem em número
	//reduzido na maioria das direções, mas afetam as médias
	//das medidas de desempenho.
	public void processa_evento(Simulador s){
		adiadas++;
		
		m.add_tempo_no_sistema(s.TNOW() - inicio);
		
		s.atualiza_area_simulacao("\nADIANDO ÀS " + Simulador.TNOW_STRING() + "\n");
		s.atualiza_area_simulacao(toString());

		Evento e = s.get_gerador().gera_um_evento_apos_adiamento(this.m);
		int pos = Simulador.addEvento(e);
		
//		s.atualiza_area_simulacao("EVENTO CRIADO ÀS " + Simulador.TNOW_STRING() + "\n");
//		s.atualiza_area_simulacao(e.toString());
//		s.atualiza_area_simulacao("\nAdicionado na posicao LEF: " + pos + "\n\n");
		
	}

	@Override
	public String toString() {
		return "Evento ADIAMENTO " + m.toString() + 
				"\nInicia em: " + get_inicio_STRING();
	}


}
