import java.util.ArrayList;

class Evaluator {

	private static double x[];
	private static double y[];

	public static void computeDataForGraph(int days, double dosage, int half_life) {
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
	        	return;
	        }
	    }
	}
	public static double[] getX() {
		return x;
	}
	public static double[] getY() {
		return y;
	}
}
