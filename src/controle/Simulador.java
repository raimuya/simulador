package controle;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import modelo.Gerador;
import modelo.ListaEventos;
import modelo.Relogio;
import modelo.eventos.Evento;
import visao.Janela;

public class Simulador{
	
	Janela janela;

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
	
	static ListaEventos listaEventos;
	
	static Relogio t  = new Relogio(0.0);
	DecimalFormat dois_digitos = new DecimalFormat("#######.##");
	
	Gerador g;
	
	public Simulador (Janela j, int serv_total_local, int serv_total_remoto,
			int serv_total_recepcao) throws InterruptedException{
		
		this.janela = j;
		
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
		
		while(janela.iniciar.isEnabled())
			atualiza_total();
		
		iniciar();
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
	

	public void gerador_init(){
		g = new Gerador(
				(double) janela.spinner_ll.getValue(), (double) janela.spinner_lr.getValue(),
				(double) janela.spinner_lr.getValue(), (double) janela.spinner_rr.getValue(),
				(double) janela.spinner_lls.getValue(), (double) janela.spinner_llf.getValue(), (double) janela.spinner_lla.getValue(),
				(double) janela.spinner_lrs.getValue(), (double) janela.spinner_lrf.getValue(), (double) janela.spinner_lra.getValue(),
				(double) janela.spinner_rls.getValue(), (double) janela.spinner_rlf.getValue(), (double) janela.spinner_rla.getValue(),
				(double) janela.spinner_rrs.getValue(), (double) janela.spinner_rrf.getValue(), (double) janela.spinner_rra.getValue(),
				(double) janela.spinner_tec_local.getValue(), (double) janela.spinner_tec_remota.getValue());
	}
	
	public void atualiza_total(){
		double ll = (double) janela.spinner_lls.getValue() +
					(double) janela.spinner_llf.getValue() +
					(double) janela.spinner_lla.getValue();
		double lr = (double) janela.spinner_lrs.getValue() +
					(double) janela.spinner_lrf.getValue() +
					(double) janela.spinner_lra.getValue();
		double rl = (double) janela.spinner_rls.getValue() +
					(double) janela.spinner_rlf.getValue() +
					(double) janela.spinner_rla.getValue();
		double rr = (double) janela.spinner_rrs.getValue() +
					(double) janela.spinner_rrf.getValue() +
					(double) janela.spinner_rra.getValue();
		double dr = (double) janela.spinner_ll.getValue() + 
					(double) janela.spinner_lr.getValue() +
					(double) janela.spinner_rl.getValue() +
					(double) janela.spinner_rr.getValue();
		janela.atualiza_total_propocoes(dr, ll, lr, rl, rr);
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
	public static int addEvento(Evento evento){
		return listaEventos.add(evento);
	}
	
	public static Double TNOW(){
		return t.getTempo();
	}
	
	//TODO: gera as estatísticas da simulação e manda a UI carregar a ultima estatística gerada.
//	void geraEstatisticas(){
//		
//	}
	
	
	public void iniciar() throws InterruptedException{
		double tempo_simulacao = (double) janela.spinner_tempo_simulacao_segundos.getValue();
		//inicia gerador
		gerador_init();
		//inicializacao: geracao de um evento inicial
		Evento e = g.gera_um_evento();
		janela.area_texto_campo_simulacao.append("EVENTO CRIADO ÀS " + dois_digitos.format(Simulador.TNOW()) + "\n");
		janela.area_texto_campo_simulacao.append(e.toString());
		int pos = addEvento(e);
		System.out.println("Adicionado na posicao LEF: " + pos);
		janela.area_texto_campo_simulacao.append("\nAdicionado na posicao LEF: " + pos + "\n\n");
	
		while(TNOW() < tempo_simulacao){
			if(janela.pausar.isEnabled()){
				TimeUnit.SECONDS.sleep((TNOW().longValue()));
				Evento iminente = avanco_tempo();
				
				proximo_evento(iminente);
			}
		}
		janela.pausar.setEnabled(false);
	}
	
	Evento avanco_tempo(){
		//remova o evento iminente da LEF
		Evento iminente = listaEventos.iminente();
		
		//avance o relógio para o tempo do evento iminente
		t.avanca(iminente.get_inicio());
		
		return iminente;
	}
	
	void proximo_evento(Evento iminente){
		//execute o evento iminente
		//System.out.println("IMINENTE: " + iminente.toString());

		iminente.processa_evento(this);
		
		//gere futuros eventos
		Evento e = g.gera_um_evento();
		janela.area_texto_campo_simulacao.append("EVENTO CRIADO ÀS " + dois_digitos.format(Simulador.TNOW()) + "\n");
		janela.area_texto_campo_simulacao.append(e.toString());

		//adicione na LEF na POSICAO CORRETA
		int pos = addEvento(e);
		//System.out.println("Adicionado na posicao LEF: " + pos);
		janela.area_texto_campo_simulacao.append("\nAdicionado na posicao LEF: " + pos + "\n\n");
	}

	public Queue<Evento> get_fila_recepcao(){
		return fila_recepcao;
	}
	
	public Queue<Evento> get_fila_local() {
		return fila_destino_local;
	}
	
	public Queue<Evento> get_fila_remoto() {
		return fila_destino_remoto;
	}
	
}
