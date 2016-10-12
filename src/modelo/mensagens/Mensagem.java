package modelo.mensagens;

public class Mensagem {

	DirecaoMensagem direcao;
	DesfechoMensagem desfecho;
	double tempo_no_sistema;
	double tempo_na_fila; 
	String conteudo;
	static int contador_mensagens;

	public Mensagem(DirecaoMensagem direcao, DesfechoMensagem desfecho){
		this.direcao = direcao;
		this.desfecho = desfecho;
		this.tempo_no_sistema = 0;
		this.tempo_na_fila = 0;
		conteudo = "\"Mensagem " + (contador_mensagens++) + "\""; 
	}
	
	public DirecaoMensagem getDirecao(){
		return direcao;
	}
	 
	public DesfechoMensagem getDesfecho(){
		return desfecho;
	}
	 
	double getDuracao(){
		return tempo_no_sistema;
	}
	 
	public String getConteudo(){
		return conteudo;
	}
	
	public void add_tempo_no_sistema(double tempo_no_sistema){
		this.tempo_no_sistema += tempo_no_sistema;
	}
	 
	public String toString(){
		return "| " + conteudo + " | " + direcao + " | " + desfecho + " | Tempo na fila: " + tempo_na_fila;
	}
}
