package controle;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import modelo.Gerador;
import modelo.ListaEventos;
import modelo.Relogio;
import modelo.eventos.Evento;
import modelo.mensagens.Mensagem;
import visao.Janela;

public class Simulador{
	
	Janela janela;
	public static DecimalFormat tres_digitos = new DecimalFormat("#######.###");
	
	Queue<Evento> fila_recepcao;
	public int serv_livre_recepcao; //servidores livres
	public int serv_total_recepcao; //numero total de servidores
	
	Queue<Evento> fila_destino_local;
	public int serv_livre_local; //servidores livres
	public int serv_total_local; //numero total de servidores
	
	Queue<Evento> fila_destino_remoto;
	public int serv_livre_remoto; //servidores livres
	public int serv_total_remoto; //numero total de servidores
	
	static ListaEventos listaEventos;
	static Relogio t;
	Gerador g;
	double tempo_simulacao;
	
	//estatisticas
	//a
	int numero_max_mensagens_sistema;
	int numero_min_mensagens_sistema;
	int numero_atual_mensagens_sistema;
	TreeMap<Double, Integer> mensagemXtempo_no_sistema;
	
	//b
	Map<Double, Integer> ocupacaoXtempo_recepcao;
	Map<Double, Integer> ocupacaoXtempo_C1;
	Map<Double, Integer> ocupacaoXtempo_C2;
	
	//c
	double duracao_min;
	double duracao_max;
	double duracao_media;
	TreeSet<Double> duracoes;
	
	//d
	public static int total_mensagens_despachadas;
	
	//e
	public static int total_LLS, total_LLF, total_LLA;
	public static int total_LRS, total_LRF, total_LRA;
	public static int total_RLS, total_RLF, total_RLA;
	public static int total_RRS, total_RRF, total_RRA;
	
	public Simulador (Janela j, int serv_total_local, int serv_total_remoto,
			int serv_total_recepcao) throws InterruptedException{
		
		this.janela = j;
		
		t  = new Relogio(0.0);
		
		esperando_nova_simulacao();
	}
	
	private void estatistiscas_init() {
		//A
		numero_max_mensagens_sistema = 0;
		numero_min_mensagens_sistema = 0;
		numero_atual_mensagens_sistema = 0;
		mensagemXtempo_no_sistema = new TreeMap<Double, Integer>();
		mensagemXtempo_no_sistema.put(0.0, 0);
		
		//TODO arrumar
		//B
		ocupacaoXtempo_recepcao = new HashMap<Double, Integer>();
		ocupacaoXtempo_recepcao.put(0.0, 0);
		ocupacaoXtempo_C1 = new HashMap<Double, Integer>();
		ocupacaoXtempo_C1.put(0.0, 0);
		ocupacaoXtempo_C2 = new HashMap<Double, Integer>();
		ocupacaoXtempo_C2.put(0.0, 0);
		
		//C
		duracao_min = 0.;
		duracao_max = 0.;
		duracao_media = 0.;
		duracoes = new TreeSet<Double>();
		
		//D
		total_mensagens_despachadas = 0;
		
		//E
		total_LLS = 0; total_LLF = 0; total_LLA = 0;
		total_LRS = 0; total_LRF = 0; total_LRA = 0;
		total_RLS = 0; total_RLF = 0; total_RLA = 0;
		total_RRS = 0; total_RRF = 0; total_RRA = 0;
			
	}
	
	public void atualiza_qtd_mensagens_sistema(boolean entrando, boolean saindo){
		//se está somente entrando uma mensagem no sistema
		if(entrando){
			numero_atual_mensagens_sistema++;
			mensagemXtempo_no_sistema.put(TNOW(), numero_atual_mensagens_sistema);
			if(numero_atual_mensagens_sistema > numero_max_mensagens_sistema)
				numero_max_mensagens_sistema = numero_atual_mensagens_sistema;
		}
		//se está somente saindo uma mensagem no sistema
		if(saindo){
			numero_atual_mensagens_sistema--;
			mensagemXtempo_no_sistema.put(TNOW(), numero_atual_mensagens_sistema);
		}
	}
	
	public void adiciona_duracao_mensagem(Mensagem m){
		duracoes.add(m.get_tempo_no_sistema());
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
	
	public void atualiza_ocupacao_C2_NOW(){
		int ocupados = serv_total_remoto - serv_livre_remoto;
		ocupacaoXtempo_C2.put(TNOW(), ocupados);
	}
	
	public void atualiza_ocupacao_recepcao_NOW(){
		int ocupados = serv_total_recepcao - serv_livre_recepcao;
		System.out.println("ocupados: " + ocupados + " às " + TNOW_STRING());
		ocupacaoXtempo_recepcao.put(TNOW(), ocupados);
	}
	
	public void atualiza_ocupacao_C1_NOW(){
		int ocupados = serv_total_local - serv_livre_local;
		ocupacaoXtempo_C1.put(TNOW(), ocupados);
	}
	
	public Evento proximo_fila_recepcao(){
		return fila_recepcao.poll();
	}

	void gerador_init(){
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
		return fila_destino_remoto.poll();
	}
	
	public Evento proximo_fila_local(){
		return fila_destino_local.poll();
	}
	
	//adiciona um novo evento a lista de eventos e manda a UI adiciona-lo também em sua JList
	public static int addEvento(Evento evento){
		return listaEventos.add(evento);
	}
	
	public static Double TNOW(){
		return t.getTempo();
	}
	
	public static String TNOW_STRING(){
		return tres_digitos.format(t.getTempo());
	}
	
	/**
	 * Gera todas as estatísticas da simulação e apresenta na interface gráfica.
	 */
	void geraEstatisticas(){
		gera_estatisticas_a();
		
		gera_estatistica_b();
		
		//c
		if(duracoes.size() > 0){
			duracao_min = duracoes.first();
			duracao_max = duracoes.last();
			duracao_media = 0;
			for(double duracao : duracoes){
				duracao_media += duracao;
			}
			duracao_media /= duracoes.size();
		}
		janela.tempo_transito_minimo.setText(String.valueOf(tres_digitos.format(duracao_min)));
		janela.tempo_transito_maximo.setText(String.valueOf(tres_digitos.format(duracao_max)));
		janela.tempo_transito_media.setText(String.valueOf(tres_digitos.format(duracao_media)));
		
		//d
		janela.mensagens_despachadas.setText(String.valueOf(total_mensagens_despachadas));

		//e
		janela.contador_LLS.setText(String.valueOf(total_LLS));
		janela.contador_LLF.setText(String.valueOf(total_LLF));
		janela.contador_LLA.setText(String.valueOf(total_LLA));
		
		janela.contador_LRS.setText(String.valueOf(total_LRS));
		janela.contador_LRF.setText(String.valueOf(total_LRF));
		janela.contador_LRA.setText(String.valueOf(total_LRA));
		
		janela.contador_RLS.setText(String.valueOf(total_RLS));
		janela.contador_RLF.setText(String.valueOf(total_RLF));
		janela.contador_RLA.setText(String.valueOf(total_RLA));
		
		janela.contador_RRS.setText(String.valueOf(total_RRS));
		janela.contador_RRF.setText(String.valueOf(total_RRF));
		janela.contador_RRA.setText(String.valueOf(total_RRA));
	}
	
	private void gera_estatistica_b() {
//		double media_recp = calcula_ponderada(ocupacaoXtempo_recepcao);
		
		int size = ocupacaoXtempo_recepcao.size() + 1;
		Double[] tempos = new Double[size];
		Integer[] quantidades = new Integer[size];

		Double tempo_anterior = 0.0;
		Integer quantidade_anterior = 0;
				
		int index = 0;
		double media = 0;
		for(Map.Entry<Double, Integer> entrada : ocupacaoXtempo_recepcao.entrySet()){
			tempos[index] = entrada.getKey() - tempo_anterior;
			quantidades[index] = quantidade_anterior;
			
			tempo_anterior = entrada.getKey();
			quantidade_anterior = entrada.getValue();
			
			index++;
		}
		tempos[index] = tempo_simulacao - tempo_anterior;
		quantidades[index] = quantidade_anterior;
		
		for(int i = 0; i < tempos.length; i++){
			media += tempos[i] * quantidades[i];
			System.out.println(tempos[i]);
			System.out.println(quantidades[i]);
		}
		
		
		janela.media_RECEPCAO.setText(tres_digitos.format(media));
		double media_c1 = calcula_ponderada(ocupacaoXtempo_C1);
		janela.media_C1.setText(tres_digitos.format(media_c1));
		double media_c2 = calcula_ponderada(ocupacaoXtempo_C2);
		janela.media_C2.setText(tres_digitos.format(media_c2));
	
}
	double calcula_ponderada(Map<Double, Integer> map){
		int size = map.size() + 1;
		Double[] tempos = new Double[size];
		Integer[] quantidades = new Integer[size];

		Double tempo_anterior = 0.0;
		Integer quantidade_anterior = 0;
				
		int index = 0;
		double media = 0;
		for(Map.Entry<Double, Integer> entrada : map.entrySet()){
			tempos[index] = entrada.getKey() - tempo_anterior;
			quantidades[index] = quantidade_anterior;
			
			tempo_anterior = entrada.getKey();
			quantidade_anterior = entrada.getValue();
			
			index++;
		}
		double tnow_aux = TNOW();
		if(tnow_aux > tempo_simulacao)
			tnow_aux = tempo_simulacao;
		tempos[index] = tnow_aux - tempo_anterior;
		quantidades[index] = quantidade_anterior;
		
		for(int i = 0; i < tempos.length; i++){
			media += tempos[i] * quantidades[i];
		}
		return media;
	}

	private void gera_estatisticas_a() {
		janela.minimo_mensagens.setText(String.valueOf(numero_min_mensagens_sistema));
		janela.maximo_mensagens.setText(String.valueOf(numero_max_mensagens_sistema));
		
		double media = calcula_ponderada(mensagemXtempo_no_sistema);
		janela.media_mensagens.setText(String.valueOf(tres_digitos.format(media)));
	}

	public void iniciar() throws InterruptedException{
		//seta configs iniciais
		
		limpa_area_simulacao();
		t.reset();
		filas_init();
		Mensagem.init();
		gerador_init();
		estatistiscas_init();
		
		//inicializacao: geracao de um evento inicial
		Evento e = g.gera_um_evento();
		
		int pos = addEvento(e);
		
//		atualiza_area_simulacao("EVENTO CRIADO ÀS " + TNOW_STRING() + "\n");
//		atualiza_area_simulacao(e.toString());
//		atualiza_area_simulacao("\nAdicionado na posicao LEF: " + pos + "\n\n");
	
		tempo_simulacao = (double) janela.spinner_tempo_simulacao_segundos.getValue();
		
		while(TNOW() < tempo_simulacao){
			System.out.println(" "); //sem o print n funfa?
			TimeUnit.SECONDS.sleep((long) (TNOW().longValue()+0.01));
			if(janela.pausar.isEnabled()){
				Evento iminente = avanco_tempo();
				if(TNOW() >= tempo_simulacao){
					break;
				}
				proximo_evento(iminente);
			}
			geraEstatisticas();
		}
		geraEstatisticas();
		esperando_nova_simulacao();
	}
	
	private void filas_init() {
		fila_recepcao = new LinkedList<Evento>();
		serv_total_recepcao = (int) janela.spinner_servidor_recepcao.getValue();
		serv_livre_recepcao = serv_total_recepcao;
		
		fila_destino_local = new LinkedList<Evento>();
		serv_total_local = (int) janela.spinner_servidor_C1.getValue();
		serv_livre_local = serv_total_local;
		
		fila_destino_remoto = new LinkedList<Evento>();
		serv_total_remoto = (int) janela.spinner_servidor_C2.getValue();
		serv_livre_remoto = serv_total_remoto;
	}

	public void esperando_nova_simulacao() throws InterruptedException{
		janela.pausar.setEnabled(false);
		janela.iniciar.setEnabled(true);
		
		listaEventos = new ListaEventos();
		
		while(janela.iniciar.isEnabled()){
			atualiza_total();
		}
		iniciar();
	}
	
	
	Evento avanco_tempo(){
		//remova o evento iminente da LEF
		Evento iminente = listaEventos.iminente();
		
		//avance o relógio para o tempo do evento iminente
		t.avanca(iminente.get_inicio());
		
		return iminente;
	}
	
	public Gerador get_gerador(){
		return g;
	}
	
	public void atualiza_area_simulacao(String s){
		janela.area_texto_campo_simulacao.append(s);
	}
	
	public void limpa_area_simulacao(){
		janela.area_texto_campo_simulacao.setText(null);
	}
	
	void proximo_evento(Evento iminente){
		//execute o evento iminente
		//System.out.println("IMINENTE: " + iminente.toString());

		iminente.processa_evento(this);
		
		//gere futuros eventos
		Evento e = g.gera_um_evento();
		
		//adicione na LEF na POSICAO CORRETA
		int pos = addEvento(e);
		
//		atualiza_area_simulacao("EVENTO CRIADO ÀS " + TNOW_STRING() + "\n");
//		atualiza_area_simulacao(e.toString());
//		atualiza_area_simulacao("\nAdicionado na posicao LEF: " + pos + "\n\n");
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
