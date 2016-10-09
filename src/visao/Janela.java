package visao;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

public class Janela extends JFrame{
	private static final long serialVersionUID = 1L;

	public Janela(){
		super("Simulador");
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setLayout(new GridBagLayout()); //para ficar expandido na horizontal
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.insets = new Insets(5, 5, 5, 5); //tamanho das células
		constraints.fill = GridBagConstraints.HORIZONTAL;
		panel.add(variaveis_simulacao(), constraints);
		
		constraints.gridy = 1;
		panel.add(controle_simulacao(), constraints);
		
		constraints.fill = GridBagConstraints.VERTICAL;
		constraints.gridy = 0;
		constraints.gridx = 1;
		panel.add(estatiscas_simulacao(), constraints);
		
		add(panel);
		
		pack(); //tamanho da tela p/ as coisas aparecer
		setLocationRelativeTo(null); //aparece no meio a janela
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
				"Estatísticas da Simulação"));
		
		return panel;
	}
	
	JPanel estatisticas_a(){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5); //tamanho das células
	
		JLabel a_min = new JLabel("Mínimo: ");
		panel.add(a_min, constraints);
		
		JLabel a_max = new JLabel("Máximo: ");
		constraints.gridx = 1;
		panel.add(a_max, constraints);
		
		JLabel a_med = new JLabel("Média: ");
		constraints.gridx = 2;
		panel.add(a_med, constraints);
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Número de Mensagens no Sistema (mínimo; máximo e média"));

		return panel;
	}
	
	JPanel estatisticas_b(){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5); //tamanho das células
	
		JLabel b_centroA = new JLabel("Centro A: ");
		panel.add(b_centroA, constraints);
		
		JLabel b_centroB = new JLabel("Centro B: ");
		constraints.gridx = 1;
		panel.add(b_centroB, constraints);
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Taxa Média de Ocupação dos Centros"));

		return panel;
	}
	
	JPanel estatisticas_c(){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5); //tamanho das células
	
		JLabel c_min = new JLabel("Mínimo: ");
		panel.add(c_min, constraints);
		
		JLabel c_max = new JLabel("Máximo: ");
		constraints.gridx = 1;
		panel.add(c_max, constraints);
		
		JLabel c_med = new JLabel("Média: ");
		constraints.gridx = 2;
		panel.add(c_med, constraints);
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Tempo de Transito das Mensagens no Sistema (mínimo; máximo e médio)"));

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
		
		JButton iniciar = new JButton(" Iniciar ");
		JButton pausar = new JButton(" Pausar ");
		JButton continuar = new JButton("Continuar");
		
		panel.add(iniciar);
		panel.add(pausar);
		panel.add(continuar);
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Controle da Simulação"));
		return panel;
	}
	
	JPanel variaveis_simulacao(){
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //para ficar expandido na horizontal
		
		panel.add(variaveis_volume());
		panel.add(variaveis_proporcoes());
		panel.add(variaveis_origem());
		
		return panel;
	}
	
	JPanel variaveis_proporcoes(){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5); //tamanho das céclucas
		
		JLabel direcao = new JLabel("Direção");
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(direcao, constraints);
		
		JLabel sucesso = new JLabel("Sucesso");
		constraints.gridx = 1;
		panel.add(sucesso, constraints);
		
		JLabel fracasso = new JLabel("Fracasso");
		constraints.gridx = 2;
		panel.add(fracasso, constraints);

		JLabel adiamento = new JLabel("Adiamento");
		constraints.gridx = 3;
		panel.add(adiamento, constraints);

		JLabel total = new JLabel("Total");
		constraints.gridx = 4;
		panel.add(total, constraints);

		// -------------- LL ----------------
		JLabel ll = new JLabel("LL");
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(ll, constraints);
		
		SpinnerModel model_ll_sucesso = new SpinnerNumberModel(87, 0, 100, .1);
		JSpinner spinner_ll_sucesso = new JSpinner(model_ll_sucesso);
		JComponent editor_ll_sucesso = new JSpinner.NumberEditor(spinner_ll_sucesso, "#,##0.###");
		spinner_ll_sucesso.setEditor(editor_ll_sucesso);
		constraints.gridx = 1;
		panel.add(spinner_ll_sucesso, constraints);
		
		SpinnerModel model_ll_fracasso = new SpinnerNumberModel(.5, 0, 100, .1);
		JSpinner spinner_ll_fracasso = new JSpinner(model_ll_fracasso);
		JComponent editor_ll_fracasso = new JSpinner.NumberEditor(spinner_ll_fracasso, "#,##0.###");
		spinner_ll_fracasso.setEditor(editor_ll_fracasso);
		constraints.gridx = 2;
		panel.add(spinner_ll_fracasso, constraints);
		
		SpinnerModel model_ll_adiamento = new SpinnerNumberModel(12.5, 0, 100, .1);
		JSpinner spinner_ll_adiamento = new JSpinner(model_ll_adiamento);
		JComponent editor_ll_adiamento = new JSpinner.NumberEditor(spinner_ll_adiamento, "#,##0.###");
		spinner_ll_adiamento.setEditor(editor_ll_adiamento);
		constraints.gridx = 3;
		panel.add(spinner_ll_adiamento, constraints);
		
		JLabel ll_total = new JLabel("100");
		constraints.gridx = 4;
		panel.add(ll_total, constraints);

		// -------------- LR ----------------
		JLabel lr = new JLabel("LR");
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(lr, constraints);
		
		SpinnerModel model_lr_sucesso = new SpinnerNumberModel(96, 0, 100, .1);
		JSpinner spinner_lr_sucesso = new JSpinner(model_lr_sucesso);
		JComponent editor_lr_sucesso = new JSpinner.NumberEditor(spinner_lr_sucesso, "#,##0.###");
		spinner_lr_sucesso.setEditor(editor_lr_sucesso);
		constraints.gridx = 1;
		panel.add(spinner_lr_sucesso, constraints);
		
		SpinnerModel model_lr_fracasso = new SpinnerNumberModel(1.5, 0, 100, .1);
		JSpinner spinner_lr_fracasso = new JSpinner(model_lr_fracasso);
		JComponent editor_lr_fracasso = new JSpinner.NumberEditor(spinner_lr_fracasso, "#,##0.###");
		spinner_lr_fracasso.setEditor(editor_lr_fracasso);
		constraints.gridx = 2;
		panel.add(spinner_lr_fracasso, constraints);
		
		SpinnerModel model_lr_adiamento = new SpinnerNumberModel(2.5, 0, 100, .1);
		JSpinner spinner_lr_adiamento = new JSpinner(model_lr_adiamento);
		JComponent editor_lr_adiamento = new JSpinner.NumberEditor(spinner_lr_adiamento, "#,##0.###");
		spinner_lr_adiamento.setEditor(editor_lr_adiamento);
		constraints.gridx = 3;
		panel.add(spinner_lr_adiamento, constraints);
		
		JLabel lr_total = new JLabel("100");
		constraints.gridx = 4;
		panel.add(lr_total, constraints);

		
		// -------------- RL ----------------
		JLabel rl = new JLabel("RL");
		constraints.gridx = 0;
		constraints.gridy = 3;
		panel.add(rl, constraints);
		
		SpinnerModel model_rl_sucesso = new SpinnerNumberModel(96, 0, 100, .1);
		JSpinner spinner_rl_sucesso = new JSpinner(model_rl_sucesso);
		JComponent editor_rl_sucesso = new JSpinner.NumberEditor(spinner_rl_sucesso, "#,##0.###");
		spinner_rl_sucesso.setEditor(editor_rl_sucesso);
		constraints.gridx = 1;
		panel.add(spinner_rl_sucesso, constraints);
		
		SpinnerModel model_rl_fracasso = new SpinnerNumberModel(3, 0, 100, .1);
		JSpinner spinner_rl_fracasso = new JSpinner(model_rl_fracasso);
		JComponent editor_rl_fracasso = new JSpinner.NumberEditor(spinner_rl_fracasso, "#,##0.###");
		spinner_rl_fracasso.setEditor(editor_rl_fracasso);
		constraints.gridx = 2;
		panel.add(spinner_rl_fracasso, constraints);
		
		SpinnerModel model_rl_adiamento = new SpinnerNumberModel(1, 0, 100, .1);
		JSpinner spinner_rl_adiamento = new JSpinner(model_rl_adiamento);
		JComponent editor_rl_adiamento = new JSpinner.NumberEditor(spinner_rl_adiamento, "#,##0.###");
		spinner_rl_adiamento.setEditor(editor_rl_adiamento);
		constraints.gridx = 3;
		panel.add(spinner_rl_adiamento, constraints);
		
		JLabel rl_total = new JLabel("100");
		constraints.gridx = 4;
		panel.add(rl_total, constraints);

		// -------------- RR ----------------
		JLabel rr = new JLabel("RR");
		constraints.gridx = 0;
		constraints.gridy = 4;
		panel.add(rr, constraints);
		
		SpinnerModel model_rr_sucesso = new SpinnerNumberModel(90, 0, 100, .1);
		JSpinner spinner_rr_sucesso = new JSpinner(model_rr_sucesso);
		JComponent editor_rr_sucesso = new JSpinner.NumberEditor(spinner_rr_sucesso, "#,##0.###");
		spinner_rr_sucesso.setEditor(editor_rr_sucesso);
		constraints.gridx = 1;
		panel.add(spinner_rr_sucesso, constraints);
		
		SpinnerModel model_rr_fracasso = new SpinnerNumberModel(1, 0, 100, .1);
		JSpinner spinner_rr_fracasso = new JSpinner(model_rr_fracasso);
		JComponent editor_rr_fracasso = new JSpinner.NumberEditor(spinner_rr_fracasso, "#,##0.###");
		spinner_rr_fracasso.setEditor(editor_rr_fracasso);
		constraints.gridx = 2;
		panel.add(spinner_rr_fracasso, constraints);
		
		SpinnerModel model_rr_adiamento = new SpinnerNumberModel(9, 0, 100, .1);
		JSpinner spinner_rr_adiamento = new JSpinner(model_rr_adiamento);
		JComponent editor_rr_adiamento = new JSpinner.NumberEditor(spinner_rr_adiamento, "#,##0.###");
		spinner_rr_adiamento.setEditor(editor_rr_adiamento);
		constraints.gridx = 3;
		panel.add(spinner_rr_adiamento, constraints);
		
		JLabel rr_total = new JLabel("100");
		constraints.gridx = 4;
		panel.add(rr_total, constraints);

		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), " Proporção de sucessos, falhas e adiamentos"));
		
		return panel;
	}
	
	//----------------------------
	// Variáveis de volume de tráfego
	//----------------------------
	JPanel variaveis_volume(){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		//constraints.anchor = GridBagConstraints.WEST; // não precisa?
		constraints.insets = new Insets(5, 5, 5, 5); //tamanho das céclucas
		
		JLabel direcao = new JLabel("Direção");
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(direcao, constraints);
		
		JLabel ll = new JLabel("LL");
		constraints.gridx = 1;
		panel.add(ll, constraints);
		
		JLabel lr = new JLabel("LR");
		constraints.gridx = 2;
		panel.add(lr, constraints);

		JLabel rl = new JLabel("RL");
		constraints.gridx = 3;
		panel.add(rl, constraints);

		JLabel rr = new JLabel("RR");
		constraints.gridx = 4;
		panel.add(rr, constraints);

		JLabel proporcao = new JLabel("Proporção (%)");
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(proporcao, constraints);

		
		SpinnerModel model_ll = new SpinnerNumberModel(50, 0, 100, .1);
		JSpinner spinner_ll = new JSpinner(model_ll);
		JComponent editor_ll = new JSpinner.NumberEditor(spinner_ll, "#,##0.###");
		spinner_ll.setEditor(editor_ll);
		constraints.gridx = 1;
		panel.add(spinner_ll, constraints);
		
		SpinnerModel model_lr = new SpinnerNumberModel(25, 0, 100, .1);
		JSpinner spinner_lr = new JSpinner(model_lr);
		JComponent editor_lr = new JSpinner.NumberEditor(spinner_lr, "#,##0.###");
		spinner_lr.setEditor(editor_lr);
		constraints.gridx = 2;
		panel.add(spinner_lr, constraints);
		
		SpinnerModel model_rl = new SpinnerNumberModel(15, 0, 100, .1);
		JSpinner spinner_rl = new JSpinner(model_rl);
		JComponent editor_rl = new JSpinner.NumberEditor(spinner_rl, "#,##0.###");
		spinner_rl.setEditor(editor_rl);
		constraints.gridx = 3;
		panel.add(spinner_rl, constraints);
		
		SpinnerModel model_rr = new SpinnerNumberModel(5, 0, 100, .1);
		JSpinner spinner_rr = new JSpinner(model_rr);
		JComponent editor_rr = new JSpinner.NumberEditor(spinner_rr, "#,##0.###");
		spinner_rr.setEditor(editor_rr);
		constraints.gridx = 4;
		panel.add(spinner_rr, constraints);
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Variáveis de volume de tráfego"));
		
		return panel;
	}
	
	JPanel variaveis_origem(){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5); //tamanho das céclucas
		
		JLabel origem = new JLabel("Origem");
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(origem, constraints);
		
		JLabel tec = new JLabel("TEC (seg.)");
		constraints.gridx = 1;
		panel.add(tec, constraints);
		
		JLabel local = new JLabel("Local");
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(local, constraints);
		
		JLabel expo_local = new JLabel("EXPO");
		constraints.gridx = 1;
		panel.add(expo_local, constraints);
		
		SpinnerModel model_origem_local = new SpinnerNumberModel(.5, 0, 100, .1);
		JSpinner spinner_origem_local = new JSpinner(model_origem_local);
		JComponent editor_origem_local = new JSpinner.NumberEditor(spinner_origem_local, "#,##0.###");
		spinner_origem_local.setEditor(editor_origem_local);
		constraints.gridx = 2;
		panel.add(spinner_origem_local, constraints);
		
		JLabel remota = new JLabel("Remota");
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(remota, constraints);
		
		JLabel expo_remota = new JLabel("EXPO");
		constraints.gridx = 1;
		panel.add(expo_remota, constraints);
		
		SpinnerModel model_origem_remota = new SpinnerNumberModel(.5, 0, 100, .1);
		JSpinner spinner_origem_remota = new JSpinner(model_origem_remota);
		JComponent editor_origem_remota = new JSpinner.NumberEditor(spinner_origem_remota, "#,##0.###");
		spinner_origem_remota.setEditor(editor_origem_remota);
		constraints.gridx = 2;
		panel.add(spinner_origem_remota, constraints);
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Valores dos tempos entre chegadas das mensagens"));
		
		return panel;
	}
}

