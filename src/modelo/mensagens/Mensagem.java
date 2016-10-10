package modelo.mensagens;

public class Mensagem {

	private DirecaoMensagem direcao;
	private DesfechoMensagem desfecho;
	private double duracao;
	String conteudo;
	static int count;
	

	public Mensagem(DirecaoMensagem direcao, DesfechoMensagem desfecho){
		this.direcao = direcao;
		this.desfecho = desfecho;
		this.duracao = 0;
		conteudo = "\"Eu sou a mensagem " + (count++) + "\""; 
	}
	
	
	 
	 public DirecaoMensagem getDirecao(){
		 return direcao;
	 }
	 
	 public DesfechoMensagem getDesfecho(){
		 return desfecho;
	 }
	 
	 double getDuracao(){
		 return duracao;
	 }
	 
	 public String getConteudo(){
		 return conteudo;
	 }
	 
	 public void addDuracao(double duracao){
		 this.duracao += duracao;
	 }
	 
	 public String toString(){
		 return conteudo + " " + direcao + " " + desfecho;
	 }

}
