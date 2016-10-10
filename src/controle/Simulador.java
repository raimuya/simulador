package controle;

import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;

import modelo.Gerador;
import modelo.ListaEventos;
import modelo.Relogio;
import modelo.eventos.Evento;
import visao.Janela;

public class Simulador {

	Queue<Evento> fila_recepcao;
	public int serv_livre_recepcao; //servidores livres
	public int serv_total_recepcao; //numero total de servidores
	Map<Double, Integer> msgXtempo_fila_recepcao;
	
	Queue<Evento> fila_destino_local;
	public int serv_livre_local; //servidores livres
	public int serv_total_local; //numero total de servidores
	Map<Double, Integer> msgXtempo_fila_local;
	
	Queue<Evento> fila_destino_remoto;
	public int serv_livre_remoto; //servidores livres
	public int serv_total_remoto; //numero total de servidores
	Map<Double, Integer> msgXtempo_fila_remoto;
	
	ListaEventos listaEventos;
	
	static Relogio t;
	
	Gerador g;
	
	double tempo_maximo_permanencia;
	
	public Simulador(int serv_total_local, int serv_total_remoto,
			int serv_total_recepcao){
		
		//new Janela().setVisible(true);
		
		fila_recepcao = new LinkedList<Evento>();
		this.serv_total_recepcao = serv_total_recepcao;
		this.serv_livre_recepcao = this.serv_total_recepcao;
		msgXtempo_fila_recepcao = new HashMap<Double, Integer>();
		
		fila_destino_local = new LinkedList<Evento>();
		this.serv_total_local = serv_total_local;
		this.serv_livre_local = this.serv_total_local;
		msgXtempo_fila_local = new HashMap<Double, Integer>();
		
		fila_destino_remoto = new LinkedList<Evento>();
		this.serv_total_remoto = serv_total_remoto;
		this.serv_livre_remoto = this.serv_total_remoto;
		msgXtempo_fila_remoto = new HashMap<Double, Integer>();
		
		listaEventos = new ListaEventos();
		
		t = new Relogio(0.0);
		
		g = new Gerador();
	}
	
	public void add_fila_local(Evento e){
		fila_destino_local.add(e);
	}
	
	public void add_fila_remoto(Evento e){
		fila_destino_remoto.add(e);
	}
	
	public void add_fila_recepcao(Evento e){
		fila_recepcao.add(e);
	}
	
	public void add_qtd_pessoas_fila_remoto_NOW(){
		msgXtempo_fila_remoto.put(TNOW(), fila_destino_local.size());
	}
	
	public void add_qtd_pessoas_fila_recepcao_NOW(){
		msgXtempo_fila_recepcao.put(TNOW(), fila_destino_local.size());
	}
	
	public void add_qtd_pessoas_fila_local_NOW(){
		msgXtempo_fila_local.put(TNOW(), fila_destino_local.size());
	}
	
	public Evento proximo_fila_recepcao(){
		msgXtempo_fila_recepcao.put(TNOW(), fila_recepcao.size());
		return fila_recepcao.poll();
	}
	
	public Evento proximo_fila_remoto(){
		msgXtempo_fila_remoto.put(TNOW(), fila_destino_remoto.size());
		return fila_destino_remoto.poll();
	}
	
	public Evento proximo_fila_local(){
		msgXtempo_fila_local.put(TNOW(), fila_destino_local.size());
		return fila_destino_local.poll();
	}
	
	//adiciona um novo evento a lista de eventos e manda a UI adiciona-lo também em sua JList
	public void addEvento(Evento evento){
		listaEventos.add(evento);
	}
	
	public Double TNOW(){
		return t.getTempo();
	}
	
	//TODO: gera as estatísticas da simulação e manda a UI carregar a ultima estatística gerada.
	void geraEstatisticas(){
		
	}
	
	//TODO: inicia a simulação
	public void iniciar(double tempo_simulacao){
		while(TNOW() < tempo_simulacao){
			avanco_tempo();
			proximo_evento();
		}
	}
	
	void avanco_tempo(){
		Evento e = g.gera_um_evento();
		addEvento(e);
		t.avanca(e.get_inicio());
	}
	
	void proximo_evento(){
		Evento e = listaEventos.proximo();
		e.processa_evento(this);
	}	
}
