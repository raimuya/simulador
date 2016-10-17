package modelo.mensagens;

public class Mensagem {

	DirecaoMensagem direcao;
	DesfechoMensagem desfecho;
	double tempo_no_sistema;
	String conteudo;
	static int contador_mensagens;

	public Mensagem(DirecaoMensagem direcao, DesfechoMensagem desfecho){
		this.direcao = direcao;
		this.desfecho = desfecho;
		this.tempo_no_sistema = 0;
		conteudo = "\"Mensagem " + (contador_mensagens++) + "\""; 
	}
	
	public DirecaoMensagem getDirecao(){
		return direcao;
	}
	 
	public DesfechoMensagem getDesfecho(){
		return desfecho;
	}
	
	//para o caso de antes ser ADIADA e agora ter um novo desfecho
	public void setDesfecho(DesfechoMensagem desfecho){
		this.desfecho = desfecho;
	}
	 
	public double get_tempo_no_sistema(){
		return tempo_no_sistema;
	}
	 
	public String getConteudo(){
		return conteudo;
	}
	
	public static void init(){
		contador_mensagens = 0;
	}
	
	public void add_tempo_no_sistema(double tempo_no_sistema){
		this.tempo_no_sistema += tempo_no_sistema;
	}
	 
	public String toString(){
		return "| " + conteudo + " | Tipo: " + direcao + desfecho;
	}
	
	public String toStringProcessando(){
		return "| " + conteudo + " | Tipo: " + direcao + desfecho;
	}
}
