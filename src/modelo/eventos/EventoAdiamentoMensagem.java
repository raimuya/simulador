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
		
		System.out.println("**************** ADIANDO ****************  =====  TNOW: " + Simulador.TNOW());
		System.out.println(toString());

		new EventoChegadaMensagem(Simulador.TNOW(), m);
	}

	@Override
	public String toString() {
		return "Evento ADIAMENTO " + m.toString() + 
				"\nInicio em: " + inicio;
	}


}
