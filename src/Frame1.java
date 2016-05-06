import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import org.math.plot.*;


public class Frame1 {

	private JFrame frame;
	static private Plot2DPanel plot;
	static double[] x;
	static double[] y;
	
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

					computeEliminationTime(14, 0.015, 96);
					plot.addLinePlot("wykres", x, y);
					
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
		frame = new JFrame();
		frame.setBounds(100, 100, 788, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane();
		frame.setContentPane(plot);

	}

}
