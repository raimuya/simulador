package modelo.mensagens;

public enum DirecaoMensagem {

	//direcao da chamada:
	//LL = Local -> Local
	//LR = Local -> Remoto
	//RL = Remoto -> Local
	//RR = Remoto -> Remoto
	LL, RL, LR, RR;
	
	//retorna um array contendo as constantes do enum
	public static DirecaoMensagem[] valores(){
		return DirecaoMensagem.values();
	}
}
