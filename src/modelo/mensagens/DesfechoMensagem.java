package modelo.mensagens;

public enum DesfechoMensagem {
	//direcao da chamada:
	//LL = Local -> Local
	//LR = Local -> Remoto
	//RL = Remoto -> Local
	//RR = Remoto -> Remoto
	S, F, A;
		
	//retorna um array contendo as constantes do enum
	public static DesfechoMensagem[] valores(){
		return DesfechoMensagem.values();
	}
}
