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

		new EventoChegadaMensagem(s.TNOW(), m);
	}


}
