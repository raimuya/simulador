package modelo.mensagens;

public class Mensagem {

	private DirecaoMensagem direcao;
	private DesfechoMensagem desfecho;
	private double duracao;

	 Mensagem(DirecaoMensagem direcao, DesfechoMensagem desfecho, double duracao){
		this.direcao = direcao;
		this.desfecho = desfecho;
		this.duracao = duracao;
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
	 
	 public void addDuracao(double duracao){
		 this.duracao += duracao;
	 }

}
