package model;

import java.util.Arrays;
import java.util.Random;

public class NeuralNetwork {
	
	private double[][] inputs;
	private double[][] outputs;
	private int numTrainingIterations;
	
	private double[][] weights;
	
	public NeuralNetwork(double[][] inputs, double[][] outputs, int numTrainingIterations) {
		
		this.inputs = inputs;
		this.outputs = outputs;
		this.numTrainingIterations = numTrainingIterations;
		
		weights = new double[inputs[0].length][1];
		setWeights();
		
	}
	
	private void setWeights() {
		
		for(int i = 0; i < weights.length; i++) {
			weights[i][0] = new Random().nextDouble() * 2 - 1;
		}
		
	}
	
	public void train() {
		
		for(int i = 0; i < numTrainingIterations; i++) {
			
			double[][] trainingOutput = sigmoid(dot(inputs, weights));
			double[][] error = subtract(outputs, trainingOutput);	
			double[][] adjustment = dot(transpose(inputs), multiply(error, sigmoidDerivative(trainingOutput)));
			weights = add(weights, adjustment);
		}
		
	}
	
	public void test(double[][] testInput, int sampleSetPos) {
		
		int numNewCorrect = 0; // Number of new sets guessed correctly
		int numTotalCorrect = 0; // Total number of sets guessed correctly (including sample sets used for training)
		
		for(int i = 0; i < testInput.length; i++) {
			System.out.print("Input: " + Arrays.toString(testInput[i]));
			System.out.print("   Correct Output: " + testInput[i][0]);
			System.out.print("   Program Output: " + (double)Math.round(sigmoid(dot(testInput, weights))[i][0]));
			System.out.print("   Correct?: ");
			
			if((int)testInput[i][0] == (int)Math.round(sigmoid(dot(testInput, weights))[i][0])) {
				if(i < sampleSetPos) { // sampleSetPos is the position of the first sample set in the array
					numNewCorrect++;
				}
				numTotalCorrect++;
				
				System.out.println("Yes");
			}
			else {
				System.out.println("No");
			}
		}
		
		System.out.println("\nCorrect Guesses for New Data Sets: " + numNewCorrect + "/" + sampleSetPos);
		System.out.println("Total Correct Guesses (Including Guesses for Sample Sets Used in Training: " + numTotalCorrect + "/" + testInput.length);
	}
	
	private double[][] dot(double[][] a, double[][] b) {

		double[][]result = new double[a.length][b[0].length];

		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < b[0].length; j++) {
				for(int k = 0; k < a[0].length; k++) {
					result[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		
		return result;
	
	}
	
	private double[][] add(double[][] a, double[][] b) {
	
		if(a.length == b.length && a[0].length == b[0].length) {
			double[][] result = new double[a.length][a[0].length];
			
			for(int i = 0; i < a.length; i++) {
				for(int j = 0; j < a[0].length; j++) {
					result[i][j] = a[i][j] + b[i][j];
				}
			}
			
			return result;
		}
		else {
			return null;
		}
		
	}
	
	private double[][] subtract(double[][] a, double[][] b) {
		
		if(a.length == b.length && a[0].length == b[0].length) {
			double[][] result = new double[a.length][a[0].length];
			
			for(int i = 0; i < a.length; i++) {
				for(int j = 0; j < a[0].length; j++) {
					result[i][j] = a[i][j] - b[i][j];
				}
			}
			
			return result;
		}
		else {
			return null;
		}
		
	}
	
	private double[][] multiply(double[][] a, double[][] b) {
		
		if(a.length == b.length && a[0].length == b[0].length) {
			double[][] result = new double[a.length][a[0].length];
			
			for(int i = 0; i < a.length; i++) {
				for(int j = 0; j < a[0].length; j++) {
					result[i][j] = a[i][j] * b[i][j];
				}
			}
			
			return result;
		}
		else {
			return null;
		}
		
	}
	
	private double[][] transpose(double[][] a) {
		
		double[][] result = new double[a[0].length][a.length];
		
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				result[j][i] = a[i][j];
			}
		}
		
		return result;
		
	}
	
	private double[][] sigmoid(double[][] a) {
		
		double[][] result = new double[a.length][a[0].length];
		
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				result[i][j] = 1 / (1+Math.exp(-a[i][j]));
			}
		}
		
		return result;
		
	}
	
	// Takes array that has already been put through sigmoid()
	private double[][] sigmoidDerivative(double[][] a) {
		
		double[][] result = new double[a.length][a[0].length];
		
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				result[i][j] = a[i][j] * (1 - a[i][j]);
			}
		}
		
		return result;
		
	}
	
}