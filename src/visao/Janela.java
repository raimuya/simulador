package visao;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;


public class Janela extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	//direcoes
	public JSpinner spinner_ll, spinner_lr, spinner_rl, spinner_rr;
	JLabel direcoes_total;
	
	//sucesso, fracasso adiamento
	public JSpinner spinner_lls, spinner_llf, spinner_lla,
	         spinner_lrs, spinner_lrf, spinner_lra,
	         spinner_rls, spinner_rlf, spinner_rla,
	         spinner_rrs, spinner_rrf, spinner_rra;
	JLabel rr_total, rl_total, lr_total, ll_total;
	
	//TECs
	public JSpinner spinner_tec_local, spinner_tec_remota;
	
	public JSpinner spinner_tempo_simulacao_segundos;
	
	//controle simulacao
	public JButton iniciar, pausar, continuar;
	
	//area simulacao
	public JTextArea area_texto_campo_simulacao;
	public JScrollPane scroll_area_texto_simulacao;
		
	public void atualiza_total_propocoes(double direcoes_tot,
			double ll_tot, double lr_tot, double rl_tot, double rr_tot){
		direcoes_total.setText(String.valueOf(direcoes_tot));
		ll_total.setText(String.valueOf(ll_tot));
		lr_total.setText(String.valueOf(lr_tot));
		rl_total.setText(String.valueOf(rl_tot));
		rr_total.setText(String.valueOf(rr_tot));
	}
	
	
	public Janela(){
		super("Simulador");
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setLayout(new GridBagLayout()); //para ficar expandido na horizontal
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.insets = new Insets(5, 5, 5, 5); //tamanho das c�lulas
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.FIRST_LINE_END;
		panel.add(variaveis_simulacao(), constraints);
		
		constraints.gridx = 1;
		panel.add(controle_simulacao(), constraints);
		
		//constraints.fill = GridBagConstraints.VERTICAL;
		constraints.gridy = 0;
		constraints.gridx = 1;
		
		
		constraints.gridx = 2;
		panel.add(estatiscas_simulacao(), constraints);
		
		add(panel);
		pack(); //tamanho da tela p/ as coisas aparecer
		setLocationRelativeTo(null); //aparece no meio a janela
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	JPanel visualizacao_simulacao(){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5); //tamanho das c�lulas
		
		area_texto_campo_simulacao = new JTextArea(24, 30);
		area_texto_campo_simulacao.setEditable(false);
		scroll_area_texto_simulacao = new JScrollPane(area_texto_campo_simulacao);
		
		constraints.gridwidth = GridBagConstraints.REMAINDER;
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.weightx = 1.0;
	    constraints.weighty = 4.0;
	    panel.add(scroll_area_texto_simulacao, constraints);
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Simula��o"));
		return panel;
	}
	
	JPanel estatiscas_simulacao(){
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //para ficar expandido na horizontal

		panel.add(estatisticas_a());
		panel.add(estatisticas_b());
		panel.add(estatisticas_c());
		panel.add(estatisticas_d());
		panel.add(estatisticas_e());
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Estat�sticas da Simula��o"));
		
		return panel;
	}
	
	JPanel estatisticas_a(){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5); //tamanho das c�lulas
	
		JLabel a_min = new JLabel("M�nimo: ");
		panel.add(a_min, constraints);
		
		JLabel a_max = new JLabel("M�ximo: ");
		constraints.gridx = 1;
		panel.add(a_max, constraints);
		
		JLabel a_med = new JLabel("M�dia: ");
		constraints.gridx = 2;
		panel.add(a_med, constraints);
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"N�mero de Mensagens no Sistema (m�nimo; m�ximo e m�dia"));

		return panel;
	}
	
	JPanel estatisticas_b(){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5); //tamanho das c�lulas
	
		JLabel b_centroA = new JLabel("Centro A: ");
		panel.add(b_centroA, constraints);
		
		JLabel b_centroB = new JLabel("Centro B: ");
		constraints.gridx = 1;
		panel.add(b_centroB, constraints);
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Taxa M�dia de Ocupa��o dos Centros"));

		return panel;
	}
	
	JPanel estatisticas_c(){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5); //tamanho das c�lulas
	
		JLabel c_min = new JLabel("M�nimo: ");
		panel.add(c_min, constraints);
		
		JLabel c_max = new JLabel("M�ximo: ");
		constraints.gridx = 1;
		panel.add(c_max, constraints);
		
		JLabel c_med = new JLabel("M�dia: ");
		constraints.gridx = 2;
		panel.add(c_med, constraints);
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Tempo de Transito das Mensagens no Sistema (m�nimo; m�ximo e m�dio)"));

		return panel;
	}
	
	JPanel estatisticas_d(){
		JPanel panel = new JPanel(new GridBagLayout());

		JLabel mensagens = new JLabel("Mensagens: ");
		panel.add(mensagens);
	
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Contadores de Mensagens Despachadas"));

		return panel;
	}
	
	JPanel estatisticas_e(){
		JPanel panel = new JPanel(new GridBagLayout());

		JLabel contador = new JLabel("Contador: ");
		panel.add(contador);
	
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Contador de Mensagens por tipo"));

		return panel;
	}
	
	
	JPanel controle_simulacao(){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5); //tamanho das c�lulas
		
		iniciar = new JButton("      Iniciar     ");
		iniciar.addActionListener(this);
		pausar = new JButton("     Pausar     ");
		pausar.addActionListener(this);
		pausar.setEnabled(false);
		continuar = new JButton("  Continuar  ");
		continuar.addActionListener(this);
		continuar.setEnabled(false);
		
		panel.add(iniciar, constraints);
		panel.add(pausar, constraints);
		panel.add(continuar, constraints);
		
		area_texto_campo_simulacao = new JTextArea(24, 30);
		area_texto_campo_simulacao.setEditable(false);
		scroll_area_texto_simulacao = new JScrollPane(area_texto_campo_simulacao);
		constraints.gridx = 0;
		constraints.gridy = 1;
	    constraints.gridwidth = 3;
	    panel.add(scroll_area_texto_simulacao, constraints);
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Controle da Simula��o"));
		return panel;
	}
	
	JPanel variaveis_simulacao(){
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //para ficar expandido na horizontal
		
		panel.add(variaveis_volume());
		panel.add(variaveis_proporcoes());
		panel.add(variaveis_origem());
		panel.add(variaveis_gerais());
		
		return panel;
	}
	
	JPanel variaveis_gerais(){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5); //tamanho das c�lulas
		
		panel.add(new JLabel("Tempo de simulacao (seg.): "), constraints);
		
		spinner_tempo_simulacao_segundos = new JSpinner(new SpinnerNumberModel(2., 0, 1000, 1));
		spinner_tempo_simulacao_segundos.setEditor(new JSpinner.NumberEditor(spinner_tempo_simulacao_segundos, "#,##0.###"));
		constraints.gridx = 1;
		panel.add(spinner_tempo_simulacao_segundos, constraints);
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Tempo"));
		
		return panel;
	}
	
	JPanel variaveis_proporcoes(){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5); //tamanho das c�clucas
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(new JLabel("Dire��o"), constraints);
		
		constraints.gridx = 1;
		panel.add(new JLabel("Sucesso"), constraints);
		
		constraints.gridx = 2;
		panel.add(new JLabel("Fracasso"), constraints);

		constraints.gridx = 3;
		panel.add(new JLabel("Adiamento"), constraints);

		constraints.gridx = 4;
		panel.add(new JLabel("Total"), constraints);

		// -------------- LL ----------------
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(new JLabel("LL"), constraints);
		
		spinner_lls = new JSpinner(new SpinnerNumberModel(87., 0, 100, 1));
		spinner_lls.setEditor(new JSpinner.NumberEditor(spinner_lls, "#,##0.###"));
		constraints.gridx = 1;
		panel.add(spinner_lls, constraints);
		
		spinner_llf = new JSpinner(new SpinnerNumberModel(.5, 0, 100, 1));
		spinner_llf.setEditor(new JSpinner.NumberEditor(spinner_llf, "#,##0.###"));
		constraints.gridx = 2;
		panel.add(spinner_llf, constraints);
		
		spinner_lla = new JSpinner(new SpinnerNumberModel(12.5, 0, 100, 1));
		spinner_lla.setEditor(new JSpinner.NumberEditor(spinner_lla, "#,##0.###"));
		constraints.gridx = 3;
		panel.add(spinner_lla, constraints);
		
		ll_total = new JLabel("100.0");
		constraints.gridx = 4;
		panel.add(ll_total, constraints);

		// -------------- LR ----------------
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(new JLabel("LR"), constraints);
		
		spinner_lrs = new JSpinner(new SpinnerNumberModel(96., 0, 100, 1));
		spinner_lrs.setEditor(new JSpinner.NumberEditor(spinner_lrs, "#,##0.###"));
		constraints.gridx = 1;
		panel.add(spinner_lrs, constraints);
		
		spinner_lrf = new JSpinner(new SpinnerNumberModel(1.5, 0, 100, 1));
		spinner_lrf.setEditor(new JSpinner.NumberEditor(spinner_lrf, "#,##0.###"));
		constraints.gridx = 2;
		panel.add(spinner_lrf, constraints);
		
		spinner_lra = new JSpinner(new SpinnerNumberModel(2.5, 0, 100, 1));
		spinner_lra.setEditor(new JSpinner.NumberEditor(spinner_lra, "#,##0.###"));
		constraints.gridx = 3;
		panel.add(spinner_lra, constraints);
		
		lr_total = new JLabel("100.0");
		constraints.gridx = 4;
		panel.add(lr_total, constraints);

		
		// -------------- RL ----------------
		constraints.gridx = 0;
		constraints.gridy = 3;
		panel.add(new JLabel("RL"), constraints);
		
		spinner_rls = new JSpinner(new SpinnerNumberModel(96., 0, 100, 1));
		spinner_rls.setEditor(new JSpinner.NumberEditor(spinner_rls, "#,##0.###"));
		constraints.gridx = 1;
		panel.add(spinner_rls, constraints);
		
		spinner_rlf = new JSpinner(new SpinnerNumberModel(3., 0, 100, 1));
		spinner_rlf.setEditor(new JSpinner.NumberEditor(spinner_rlf, "#,##0.###"));
		constraints.gridx = 2;
		panel.add(spinner_rlf, constraints);
		
		spinner_rla = new JSpinner(new SpinnerNumberModel(1., 0, 100, 1));
		spinner_rla.setEditor(new JSpinner.NumberEditor(spinner_rla, "#,##0.###"));
		constraints.gridx = 3;
		panel.add(spinner_rla, constraints);
		
		rl_total = new JLabel("100.0");
		constraints.gridx = 4;
		panel.add(rl_total, constraints);

		// -------------- RR ----------------
		constraints.gridx = 0;
		constraints.gridy = 4;
		panel.add(new JLabel("RR"), constraints);
		
		SpinnerModel model_rr_sucesso = new SpinnerNumberModel(90., 0, 100, 1);
		spinner_rrs = new JSpinner(model_rr_sucesso);
		spinner_rrs.setEditor(new JSpinner.NumberEditor(spinner_rrs, "#,##0.###"));
		constraints.gridx = 1;
		panel.add(spinner_rrs, constraints);
		
		SpinnerModel model_rr_fracasso = new SpinnerNumberModel(1., 0, 100, 1);
		spinner_rrf = new JSpinner(model_rr_fracasso);
		spinner_rrf.setEditor(new JSpinner.NumberEditor(spinner_rrf, "#,##0.###"));
		constraints.gridx = 2;
		panel.add(spinner_rrf, constraints);
		
		SpinnerModel model_rr_adiamento = new SpinnerNumberModel(9., 0, 100, 1);
		spinner_rra = new JSpinner(model_rr_adiamento);
		spinner_rra.setEditor(new JSpinner.NumberEditor(spinner_rra, "#,##0.###"));
		constraints.gridx = 3;
		panel.add(spinner_rra, constraints);
		
		rr_total = new JLabel("100.0");
		constraints.gridx = 4;
		panel.add(rr_total, constraints);

		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), " Propor��o de sucessos, falhas e adiamentos"));
		
		return panel;
	}
	
	//----------------------------
	// Vari�veis de volume de tr�fego
	//----------------------------
	JPanel variaveis_volume(){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		//constraints.anchor = GridBagConstraints.WEST; // n�o precisa?
		constraints.insets = new Insets(5, 5, 5, 5); //tamanho das c�clucas
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(new JLabel("Dire��o"), constraints);
		
		constraints.gridx = 1;
		panel.add(new JLabel("LL"), constraints);
		
		constraints.gridx = 2;
		panel.add(new JLabel("LR"), constraints);

		constraints.gridx = 3;
		panel.add(new JLabel("RL"), constraints);

		constraints.gridx = 4;
		panel.add(new JLabel("RR"), constraints);
		
		constraints.gridx = 5;
		panel.add(new JLabel("Total"), constraints);

		JLabel proporcao = new JLabel("Propor��o (%)");
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(proporcao, constraints);
		
		spinner_ll = new JSpinner(new SpinnerNumberModel(50., 0, 100, 1));
		spinner_ll.setEditor(new JSpinner.NumberEditor(spinner_ll, "#,##0.###"));
		constraints.gridx = 1;
		panel.add(spinner_ll, constraints);
		
		spinner_lr = new JSpinner( new SpinnerNumberModel(25., 0, 100, 1));
		spinner_lr.setEditor(new JSpinner.NumberEditor(spinner_lr, "#,##0.###"));
		constraints.gridx = 2;
		panel.add(spinner_lr, constraints);
		
		spinner_rl = new JSpinner(new SpinnerNumberModel(15., 0, 100, 1));
		spinner_rl.setEditor(new JSpinner.NumberEditor(spinner_rl, "#,##0.###"));
		constraints.gridx = 3;
		panel.add(spinner_rl, constraints);
		
		spinner_rr = new JSpinner(new SpinnerNumberModel(5., 0, 100, 1));
		spinner_rr.setEditor(new JSpinner.NumberEditor(spinner_rr, "#,##0.###"));
		constraints.gridx = 4;
		panel.add(spinner_rr, constraints);
		
		direcoes_total = new JLabel("100.0");
		constraints.gridx = 5;
		panel.add(direcoes_total, constraints);
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Vari�veis de volume de tr�fego"));
		
		return panel;
	}
	
	JPanel variaveis_origem(){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5); //tamanho das c�clucas
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(new JLabel("Origem"), constraints);
		
		constraints.gridx = 1;
		panel.add(new JLabel("TEC (seg.)"), constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(new JLabel("Local"), constraints);
		
		constraints.gridx = 1;
		panel.add(new JLabel("EXPO"), constraints);
		
		spinner_tec_local = new JSpinner(new SpinnerNumberModel(.5, 0, 100, .1));
		spinner_tec_local.setEditor(new JSpinner.NumberEditor(spinner_tec_local, "#,##0.###"));
		constraints.gridx = 2;
		panel.add(spinner_tec_local, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(new JLabel("Remota"), constraints);
		
		constraints.gridx = 1;
		panel.add(new JLabel("EXPO"), constraints);
		
		spinner_tec_remota = new JSpinner(new SpinnerNumberModel(.6, 0, 100, .1));
		spinner_tec_remota.setEditor(new JSpinner.NumberEditor(spinner_tec_remota, "#,##0.###"));
		constraints.gridx = 2;
		panel.add(spinner_tec_remota, constraints);
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Valores dos tempos entre chegadas das mensagens"));
		
		return panel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(iniciar)){
			iniciar.setEnabled(false);
			pausar.setEnabled(true);
			continuar.setEnabled(false);
		}
		if(e.getSource().equals(pausar)){
			iniciar.setEnabled(false);
			pausar.setEnabled(false);
			continuar.setEnabled(true);
		}
		if(e.getSource().equals(continuar)){
			iniciar.setEnabled(false);
			pausar.setEnabled(true);
			continuar.setEnabled(false);
		}
		
	}


//	TODO: ver como colocar os eventos printando
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if(e.getSource().equals(iniciar)){
//			SwingUtilities.invokeLater(new Runnable() {
//				public void run() {
////					while(true){
////						if(s.mudou_evento_gerado){
////							area_texto_campo_simulacao.append(s.temp_evento_gerado);
////							s.mudou_evento_gerado = false;
////						}
////					}
//				}
//			});
//		}
//		if(e.getSource().equals(pausar)){
//		}
//		if(e.getSource().equals(continuar)){
//		}
//	
//		
//
//	 
//	}

}


