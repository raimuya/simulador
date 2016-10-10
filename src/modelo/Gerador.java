package modelo;

import controle.Simulador;
import modelo.distribuicoes.Distribuicao;
import modelo.eventos.Evento;
import modelo.eventos.EventoChegadaMensagem;
import modelo.mensagens.DesfechoMensagem;
import modelo.mensagens.DirecaoMensagem;
import modelo.mensagens.Mensagem;

public class Gerador {
	
	double LL, LR, RL, RR;
	
	double LLS, LLF, LLA;
	double LRS, LRF, LRA;
	double RLS, RLF, RLA;
	double RRS, RRF, RRA;
	
	double tec_local, tec_remota;
	
	public Gerador() {
	// ---- exemplo volume de tráfego ----
	//direcao     | LL | LR | RL | RR
	//proporcao   | 50 | 25 | 15 | 10
	//acumulada   | 50 | 75 | 90 | 100
		LL = 50;
		LR = 75;
		RL = 90;
		RR = 100;
	// ---- sucesso/fracasso/adiamento ----
	// direcao | sucesso | fracasso | adiamento
	//    LL   |    87   |    0.5   |   12.5
	//    LR   |    96   |    1.5   |    2.5
	//    RL   |    96   |    3.    |    1
	//    RR   |    90   |    1.    |    9
		LLS = 87;   LLF = 0.5;   LLA = 12.5;
		LRS = 96;   LRF = 1.5;   LRA = 2.5;
		RLS = 96;   RLF = 3;     RLA = 1;
		RRS = 90;   RRF = 1;     RRA = 9;
		
	// origem |    TEC
	// local  | EXPO (0.5)
	// remota | EXPO (0.6)
		tec_local = Distribuicao.expo(0.5);
		tec_remota = Distribuicao.expo(0.6);
	}

	
	public Evento gera_um_evento(){
		Mensagem m = gera_uma_mensagem();
		if(m.getDirecao() == DirecaoMensagem.LL || m.getDirecao() == DirecaoMensagem.LR){
			tec_local = Distribuicao.expo(0.5);
			System.out.println("Mensagem " + m.toString() +" criada!");
			return new EventoChegadaMensagem(tec_local, m);
		} else {
			tec_remota = Distribuicao.expo(0.6);
			System.out.println("Mensagem " + m.toString() +" criada!");
			return new EventoChegadaMensagem(tec_remota, m);
		}
	}
	
	public Mensagem gera_uma_mensagem(){
		DirecaoMensagem dir = gera_direcao_mensagem();
		DesfechoMensagem desf = gera_desfecho_mensagem(dir);
		return new Mensagem(dir, desf);
	}
	
	DirecaoMensagem gera_direcao_mensagem(){
		DirecaoMensagem dir;
		int prop_dir = (int) (Math.random()*100);
		if(prop_dir < LL)
			dir = DirecaoMensagem.LL;
		 else if(prop_dir < LR)
			dir = DirecaoMensagem.LR;
		 else if (prop_dir > RL)
			dir = DirecaoMensagem.RL;
		 else 
			dir = DirecaoMensagem.RR;
		
		return dir;
	}
	
	DesfechoMensagem gera_desfecho_mensagem(DirecaoMensagem dir){
		DesfechoMensagem desf = null;
		int prop_desf =  (int) (Math.random()*100);
		switch (dir){
		case LL:
			if(prop_desf < LLS)
				desf = DesfechoMensagem.SUCESSO;
			else if(prop_desf < LLF)
				desf = DesfechoMensagem.FRACASSO;
			else
				desf = DesfechoMensagem.ADIAMENTO;
			break;
		case LR:
			if(prop_desf < LRS)
				desf = DesfechoMensagem.SUCESSO;
			else if(prop_desf < LRF)
				desf = DesfechoMensagem.FRACASSO;
			else
				desf = DesfechoMensagem.ADIAMENTO;
			break;
		case RL:
			if(prop_desf < RLS)
				desf = DesfechoMensagem.SUCESSO;
			else if(prop_desf < RLF)
				desf = DesfechoMensagem.FRACASSO;
			else
				desf = DesfechoMensagem.ADIAMENTO;
			break;
		case RR:
			if(prop_desf < RRS)
				desf = DesfechoMensagem.SUCESSO;
			else if(prop_desf < RRF)
				desf = DesfechoMensagem.FRACASSO;
			else
				desf = DesfechoMensagem.ADIAMENTO;
			break;
		}
	return desf;
	}
}
