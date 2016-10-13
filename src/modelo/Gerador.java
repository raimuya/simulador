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
	
	public Gerador(double LL, double LR, double RL, double RR,
			double LLS, double LLF, double LLA,
			double LRS, double LRF, double LRA,
			double RLS, double RLF, double RLA,
			double RRS, double RRF, double RRA,
			double tec_local, double tec_remota) {
		// ---- exemplo volume de tráfego ----
		//direcao     | LL | LR | RL | RR
		//proporcao   | 50 | 25 | 15 | 10
		//acumulada   | 50 | 75 | 90 | 100
		this.LL = LL;
		this.LR = LR;
		this.RL = RL;
		this.RR = RR;
		// ---- sucesso/fracasso/adiamento ----
		// direcao | sucesso | fracasso | adiamento
		//    LL   |    87   |    0.5   |   12.5
		//    LR   |    96   |    1.5   |    2.5
		//    RL   |    96   |    3.    |    1
		//    RR   |    90   |    1.    |    9
		this.LLS = LLS;   this.LLF = LLF;   this.LLA = LLA;
		this.LRS = LRS;   this.LRF = LRF;   this.LRA = LRA;
		this.RLS = RLS;   this.RLF = RLF;   this.RLA = RLA;
		this.RRS = RRS;   this.RRF = RRF;   this.RRA = RRA;
		
		// origem |    TEC
		// local  | EXPO (0.5)
		// remota | EXPO (0.6)
		this.tec_local = Distribuicao.expo(tec_local);
		this.tec_remota = Distribuicao.expo(tec_remota);
	}

	
	public Evento gera_um_evento(){
		Mensagem m = gera_uma_mensagem();
		Evento e;
		if(m.getDirecao() == DirecaoMensagem.LL || m.getDirecao() == DirecaoMensagem.LR){
			tec_local = Distribuicao.expo(0.5) + Simulador.TNOW();
			e = new EventoChegadaMensagem(tec_local, m);
		} else {
			tec_remota = Distribuicao.expo(0.6);
			e = new EventoChegadaMensagem(tec_remota  + Simulador.TNOW(), m);
		}
		return e;
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
