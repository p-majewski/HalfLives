import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import org.math.plot.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class Frame1{

	private JFrame frame;
	static private Plot2DPanel plot;
	static double[] x;
	static double[] y;
	private JTextField daysField;
	private JTextField dosageField;
	private JTextField half_lifetField;
	
	public static int computeEliminationTime(int days, double dosage, int half_life) {
		ArrayList<Double> X = new ArrayList<Double>();
		ArrayList<Double> Y = new ArrayList<Double>();
		
		double saturation = 0.0d;
	    double threshold = dosage/60.0d;
	    double degradationFactor = Math.pow(2, -1.0d/(double)half_life);
	    boolean isHeStillTakingMedicine = true;
	    for (int hours = 0;; hours++) {
	        if (isHeStillTakingMedicine) {
	            if (hours % 24 == 0) {
	                saturation += dosage;
	                days--;
	                if (days == -1){
	                    isHeStillTakingMedicine = false;
	                    hours=0;
	                }
	            }
	        }

	        saturation = saturation * degradationFactor; 


	        if(!isHeStillTakingMedicine){
	        	X.add((double)hours);
	        	Y.add(saturation);
	        }
	        
	        if (saturation < threshold) {
	        	x = new double[X.size()];
	        	y = new double[Y.size()];
	        	for(int i = 0; i < x.length ; i++)
	        	{
	        		x[i] = X.get(i);
	        		y[i] = Y.get(i);
	        	}
	        	return hours;
	        }
	    }
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Frame1() {
		initialize();
	}

	private void initialize() {
		plot = new Plot2DPanel();
		JPanel panel = new JPanel();
		frame = new JFrame();
		frame.setBounds(100, 100, 788, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblForHowMany = new JLabel("For how many days you have been taking he substance");
		lblForHowMany.setBounds(10, 0, 762, 20);
		panel.add(lblForHowMany);
		
		daysField = new JTextField();
		daysField.setBounds(10, 31, 86, 20);
		panel.add(daysField);
		daysField.setColumns(10);
		
		JLabel lblHowManyMiligrams = new JLabel("How many miligrams per day");
		lblHowManyMiligrams.setBounds(10, 62, 184, 14);
		panel.add(lblHowManyMiligrams);
		
		dosageField = new JTextField();
		dosageField.setBounds(10, 87, 86, 20);
		panel.add(dosageField);
		dosageField.setColumns(10);
		
		JLabel lblWhatsTheHalf = new JLabel("What's the half life of the substance in hours");
		lblWhatsTheHalf.setBounds(10, 128, 266, 14);
		panel.add(lblWhatsTheHalf);
		
		half_lifetField = new JTextField();
		half_lifetField.setBounds(10, 153, 86, 20);
		panel.add(half_lifetField);
		half_lifetField.setColumns(10);
		
		JButton btnShowAGraph = new JButton("Show a graph");
		btnShowAGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int days;
				double dosage;
				int half_life;
				try{
					days = Integer.parseInt(daysField.getText());
					dosage = Double.parseDouble(dosageField.getText())/1000d;
					half_life = Integer.parseInt(half_lifetField.getText());
				} catch (NumberFormatException exc)
				{
					return;
				}
				computeEliminationTime(days, dosage, half_life);
				plot.addLinePlot("wykres", x, y);
				frame.getContentPane().add(plot);
				frame.setContentPane(plot);
				frame.revalidate();
			}
		});
		btnShowAGraph.setBounds(7, 195, 211, 31);
		panel.add(btnShowAGraph);
	}

}
