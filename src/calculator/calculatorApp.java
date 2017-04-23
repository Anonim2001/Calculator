package calculator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class calculatorApp {

	private JFrame frame;
	private JTextField wyswietlacz;
	
	private enum Operacja {
		BRAK, DODAWANIE, ODEJMOWANIE, MNOZENIE, DZIELENIE, PIERWIASTEK, ODWROTNOSC, LOGARYTM, PROCENT
	}
	
	private Operacja biezacaOperacja = Operacja.BRAK;
	
	private Double liczba = null;
	
	private boolean wyczyscPrzed = false;
	
	private void oblicz() {
		if (liczba != null) {
			Double liczba2 = getDoubleValue();
			if (liczba2 != null) {
				if (Operacja.DODAWANIE.equals(biezacaOperacja)){
					liczba = liczba + liczba2;
					
				} else if (Operacja.ODEJMOWANIE.equals(biezacaOperacja)){
					liczba = liczba - liczba2;
				} else if (Operacja.MNOZENIE.equals(biezacaOperacja)){
					liczba = liczba * liczba2;
				} else if (Operacja.DZIELENIE.equals(biezacaOperacja)) {
					if (liczba2 != 0){
					liczba = liczba / liczba2;}
				} else if (Operacja.PIERWIASTEK.equals(biezacaOperacja)) {
					liczba=Math.sqrt(liczba);
				} else if (Operacja.PROCENT.equals(biezacaOperacja)){
					liczba = liczba * liczba2 /100;
					
				} else if (Operacja.ODWROTNOSC.equals(biezacaOperacja)) {
					liczba=1/liczba;
				} else if (Operacja.LOGARYTM.equals(biezacaOperacja)) {
					liczba=Math.log10(liczba);
				}
				}
				
				setDoubleValue(liczba);
				wyczyscPrzed = true;
				double wynik = liczba;
				oblicz(wynik);
				liczba = null;
			}
		}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e){
		}
		
		EventQueue.invokeLater(new Runnable() { 
			public void run() {
				
				try {
					calculatorApp window = new calculatorApp();
					window.frame.pack();
					window.frame.setMinimumSize(window.frame.getPreferredSize());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public calculatorApp() {
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		
		JPanel kontenerPanel = new JPanel();
		kontenerPanel.setBackground(new Color(192, 192, 192));
		frame.getContentPane().add(kontenerPanel, BorderLayout.CENTER);
		kontenerPanel.setLayout(new BorderLayout(5, 5));
		
		JPanel wyswietlaczPanel = new JPanel();
		wyswietlaczPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		kontenerPanel.add(wyswietlaczPanel, BorderLayout.NORTH);
		wyswietlaczPanel.setLayout(new BorderLayout(0, 0));
		
		wyswietlacz = new JTextField();
		wyswietlaczPanel.add(wyswietlacz);
		wyswietlacz.setColumns(10);
		
		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				biezacaOperacja = Operacja.BRAK;
				liczba=null;
				wyczyscPrzed =false;
				wyswietlacz.setText("");
			}
		});
		wyswietlaczPanel.add(btnC, BorderLayout.EAST);
		
		JPanel panelFunkcyjny = new JPanel();
		panelFunkcyjny.setBackground(new Color(192, 192, 192));
		kontenerPanel.add(panelFunkcyjny, BorderLayout.EAST);
		panelFunkcyjny.setLayout(new GridLayout(4, 2, 5, 5));
		
		JButton btnDzielenie = new JButton("/");
		btnDzielenie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			wcisnietaOperacja(Operacja.DZIELENIE);
			}
		});
		panelFunkcyjny.add(btnDzielenie);
		
		JButton btnSqrt = new JButton("\u221A");
		btnSqrt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaOperacja(Operacja.PIERWIASTEK);
				oblicz();
			}
		});
		panelFunkcyjny.add(btnSqrt);
		
		JButton btnMnozenie = new JButton("*");
		btnMnozenie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			wcisnietaOperacja(Operacja.MNOZENIE);
			}
		});
		panelFunkcyjny.add(btnMnozenie);
		
		JButton btnProcent = new JButton("%");
		btnProcent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			wcisnietaOperacja(Operacja.PROCENT);

			}
		});
		panelFunkcyjny.add(btnProcent);
		
		JButton btnMinus = new JButton("-");
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			wcisnietaOperacja(Operacja.ODEJMOWANIE);	
			}
		});
		panelFunkcyjny.add(btnMinus);
		
		JButton btnx = new JButton("1/x");
		btnx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			wcisnietaOperacja(Operacja.ODWROTNOSC);
			oblicz();
			}
		});
		panelFunkcyjny.add(btnx);
		
		JButton btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaOperacja(Operacja.DODAWANIE);
			}
		});
		panelFunkcyjny.add(btnPlus);
		
		JButton btnLog = new JButton("log");
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			wcisnietaOperacja(Operacja.LOGARYTM);
			oblicz();
			}
		});
		panelFunkcyjny.add(btnLog);
		
		JPanel panelKlawiatura = new JPanel();
		panelKlawiatura.setBackground(new Color(192, 192, 192));
		kontenerPanel.add(panelKlawiatura, BorderLayout.CENTER);
		panelKlawiatura.setLayout(new GridLayout(4, 3, 5, 5));
		
		JButton button_6 = new JButton("7");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				wcisnietaCyfra("7");
			}
		});
		panelKlawiatura.add(button_6);
		
		JButton button_7 = new JButton("8");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				wcisnietaCyfra("8");
			}
		});
		panelKlawiatura.add(button_7);
		
		JButton button_8 = new JButton("9");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("9");
			}
		});
		panelKlawiatura.add(button_8);
		
		JButton button_3 = new JButton("4");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("4");
			}
		});
		panelKlawiatura.add(button_3);
		
		JButton button_4 = new JButton("5");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("5");
			}
		});
		panelKlawiatura.add(button_4);
		
		JButton button_5 = new JButton("6");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("6");
			}
		});
		panelKlawiatura.add(button_5);
		
		JButton button = new JButton("1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("1");
			}
		});
		panelKlawiatura.add(button);
		
		JButton button_1 = new JButton("2");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("2");
			}
		});
		panelKlawiatura.add(button_1);
		
		JButton button_2 = new JButton("3");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("3");
			}
		});
		panelKlawiatura.add(button_2);
		
		JButton button_9 = new JButton("0");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("0");
			}
		});
		panelKlawiatura.add(button_9);
		
		JButton button_10 = new JButton(",");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelKlawiatura.add(button_10);
		
		JButton button_11 = new JButton("=");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oblicz();
			}
		});
		panelKlawiatura.add(button_11);
		frame.setBounds(100, 100, 410, 318);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
	}
	private Double getDoubleValue() {
		Double number = null;
		String numberText = wyswietlacz.getText();
		if (numberText != null && (numberText = numberText.trim()).length()>0){
		number = Double.parseDouble(numberText.replace(',', '.'));	
		}
		return number;
	}
	private void setDoubleValue(Double number) {
		if(number != null) {
		String value = number.toString().replace('.', ',');
		wyswietlacz.setText(value);
	}}
	
	
	
	private void oblicz(Double liczba2){
		
	}
		
	private void wcisnietaOperacja(Operacja operacja) {
		this.biezacaOperacja = operacja;
		if (liczba == null) {
			liczba = getDoubleValue();
			wyczyscPrzed = true;
		} else {
			oblicz();;;;;;;;;;;;;;;;;;;;;;
		}
	}
	private void wcisnietaCyfra(String cyfra){
		if (wyczyscPrzed){
			wyswietlacz.setText(cyfra);
			wyczyscPrzed = false;
			
		}else {
			wyswietlacz.setText(wyswietlacz.getText()+cyfra);
		}
	}
	}
	

