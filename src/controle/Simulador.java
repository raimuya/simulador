package controle;

import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;

import modelo.eventos.Evento;
import modelo.mensagens.Mensagem;
import visao.Janela;

public class Simulador {

	Queue<Mensagem> fila_recepcao;
	public int serv_livre_recepcao; //servidores livres
	public int serv_total_recepcao; //numero total de servidores
	Map<Double, Integer> msgXtempo_fila_recepcao;
	
	Queue<Mensagem> fila_destino_local;
	public int serv_livre_local; //servidores livres
	public int serv_total_local; //numero total de servidores
	Map<Double, Integer> msgXtempo_fila_local;
	
	Queue<Mensagem> fila_destino_remoto;
	public int serv_livre_remoto; //servidores livres
	public int serv_total_remoto; //numero total de servidores
	Map<Double, Integer> msgXtempo_fila_remoto;
	
	public Simulador(int serv_total_local, int serv_total_remoto,
			int serv_total_recepcao){
		new Janela().setVisible(true);
		
		fila_destino_local = new LinkedList<>();
		this.serv_total_recepcao = serv_total_recepcao;
		this.serv_livre_recepcao = this.serv_total_recepcao;
		msgXtempo_fila_recepcao = new HashMap<Double, Integer>();
		
		fila_destino_local = new LinkedList<Mensagem>();
		this.serv_total_local = serv_total_local;
		this.serv_livre_local = this.serv_total_local;
		msgXtempo_fila_local = new HashMap<Double, Integer>();
		
		fila_destino_remoto = new LinkedList<Mensagem>();
		this.serv_total_remoto = serv_total_remoto;
		this.serv_livre_remoto = this.serv_total_remoto;
		msgXtempo_fila_remoto = new HashMap<Double, Integer>();
	}
	
	public void add_fila_local(Mensagem m){
		fila_destino_local.add(m);
	}
	
	public void add_fila_remoto(Mensagem m){
		fila_destino_remoto.add(m);
	}
	
	public void add_fila_recepcao(Mensagem m){
		fila_recepcao.add(m);
	}
	
	public void add_qtd_pessoas_fila_remoto(Double tempo){
		msgXtempo_fila_remoto.put(tempo, fila_destino_local.size());
	}
	
	public void add_qtd_pessoas_fila_recepcao(Double tempo){
		msgXtempo_fila_recepcao.put(tempo, fila_destino_local.size());
	}
	
	public void add_qtd_pessoas_fila_local(Double tempo){
		msgXtempo_fila_local.put(tempo, fila_destino_local.size());
	}
	
	
	
	//adiciona um novo evento a lista de eventos e manda a UI adiciona-lo também em sua JList
	public void addEvento(Evento event){
		
	}
	
	
	//TODO: gera as estatísticas da simulação e manda a UI carregar a ultima estatística gerada.
	public void geraEstatisticas(){
		
	}
	
	//TODO: inicia a simulação
	public void iniciar(){
		
	}

}
