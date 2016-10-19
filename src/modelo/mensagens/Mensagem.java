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
	
	/**
	 * Retorna a direção de uma mensagem.
	 */
	public DirecaoMensagem getDirecao(){
		return direcao;
	}
	 
	/**
	 * Retorna se a mensagem resultará em SUCESSO, FRACASSO ou ADIAMENTO.
	 */
	public DesfechoMensagem getDesfecho(){
		return desfecho;
	}
	
	/**
	 * Atribui SUCESSO, FRACASSO ou ADIAMENTO à uma mensagem.
	 * É usado para o caso de a mensagem ter sido ADIADA e após isto ter um novo desfecho.
	 */
	public void setDesfecho(DesfechoMensagem desfecho){
		this.desfecho = desfecho;
	}
	 
	/**
	 * Retorna o tempo que uma mensagem está no sistema.
	 */
	public double get_tempo_no_sistema(){
		return tempo_no_sistema;
	}
	 
	/**
	 * Retorna o conteúdo de uma mensagem.
	 *
	 */
	public String getConteudo(){
		return conteudo;
	}
	
	/**
	 * Configura para uma nova simulação.
	 */
	public static void init(){
		contador_mensagens = 0;
	}
	
	/**
	 * Adiciona um valor ao tempo que a mensagem está no sistema.
	 * @param tempo_no_sistema valor a ser adicionado.
	 */
	public void add_tempo_no_sistema(double tempo_no_sistema){
		this.tempo_no_sistema += tempo_no_sistema;
	}
	
	/**
	 * Transforma o objeto mensagem para uma string entendível.
	 */
	public String toString(){
		return "| " + conteudo + " | Tipo: " + direcao + desfecho;
	}
	
}
