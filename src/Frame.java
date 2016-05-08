import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.math.plot.*;

public class Frame {

	private JFrame frame;
	private Plot2DPanel plot;
	private JPanel panel;
	
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Frame() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 790, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane();

		plot = new Plot2DPanel();
//		plot.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e){
//				java.awt.Toolkit.getDefaultToolkit().beep(); //debug beep
//				System.exit(0);
//			}
//		});
		
		panel = new JPanel();

		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblForHowMany = new JLabel("For how many days you have been taking he substance");
		lblForHowMany.setBounds(22, 8, 336, 14);
		panel.add(lblForHowMany);
		
		JTextField daysField = new JTextField();
		daysField.setBounds(32, 30, 86, 20);
		panel.add(daysField);
		daysField.setColumns(10);
		
		JLabel lblHowManyMiligrams = new JLabel("How many miligrams per day");
		lblHowManyMiligrams.setBounds(22, 57, 273, 14);
		panel.add(lblHowManyMiligrams);
		
		JTextField dosageField = new JTextField();
		dosageField.setBounds(32, 82, 86, 20);
		panel.add(dosageField);
		dosageField.setColumns(10);
		
		JLabel lblWhatsTheHalf = new JLabel("What's the half life of the substance in hours");
		lblWhatsTheHalf.setBounds(22, 113, 262, 14);
		panel.add(lblWhatsTheHalf);
		
		JTextField half_lifetField = new JTextField();
		half_lifetField.setBounds(32, 138, 86, 20);
		panel.add(half_lifetField);
		half_lifetField.setColumns(10);
		
		JButton btnShowAGraph = new JButton("Show a graph");
		btnShowAGraph.setBounds(29, 183, 189, 44);
		btnShowAGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
//				java.awt.Toolkit.getDefaultToolkit().beep();
				int days;
				double dosage;
				int half_life;
				try{
					days = Integer.parseInt(daysField.getText());
					dosage = Double.parseDouble(dosageField.getText());
					half_life = Integer.parseInt(half_lifetField.getText());
				} catch (NumberFormatException exc)
				{
					return;
				}
				Evaluator.computeDataForGraph(days, dosage, half_life);
				plot.addLinePlot("graph", Evaluator.getX(), Evaluator.getY());
				frame.getContentPane().add(plot);
				frame.setContentPane(plot);
				frame.revalidate();
				frame.repaint();
			}
		});
		panel.add(btnShowAGraph);
	}
}
