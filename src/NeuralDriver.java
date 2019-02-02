import model.NeuralNetwork;

public class NeuralDriver {
	
	private static final double[][] inputs = new double[][] {
		{0, 0, 1},
		{1, 1, 1},
		{1, 0, 1},
		{0, 1, 1}
	};
	
	private static final double[][] outputs = new double[][] {
		{0},
		{1},
		{1},
		{0}
	}; // Output is the left-most input column
	
	private static final double[][] testInput = new double[][] {
		{1, 0, 0},
		{0, 1, 0},
		{0, 0, 0},
		{1, 1, 0},
		{0, 0, 1},
		{1, 1, 1},
		{1, 0, 1},
		{0, 1, 1}
	}; // Last 4 rows were part of the test data
	
	private static final int POSITION_OF_FIRST_SAMPLE_SET = 4;
	
	private static final int NUMBER_OF_TRAINING_ITERATIONS = 10000;
	
	public static void main(String[] args) {
		
		NeuralNetwork n = new NeuralNetwork(inputs, outputs, NUMBER_OF_TRAINING_ITERATIONS);
		n.train();
		n.test(testInput, POSITION_OF_FIRST_SAMPLE_SET);
	}
	
}
