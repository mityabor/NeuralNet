package Perceptron;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class Trainer 
{
	private NeuralNet net;
	private DataSet ds;
	private double alpha;
	private double momentum;
	private double[] Errors;
	
	public Trainer(NeuralNet net,DataSet ds,double alpha, double momentum)
	{
		this.net = net;
		this.ds = ds;
		this.alpha = alpha;
		this.momentum = momentum;
		this.Errors = new double[this.ds.getDataSetLength()];
		
	}
	public void train()
	{
		int size = ds.getDataSetLength();
		double[] input = new double[ds.getInputCount()];
		double[] output = new double[ds.getOutputCount()];
		double[] desired = new double[ds.getOutputCount()];
		double[] hidden_output = new double[net.getNumber_Of_Hidden()];
		double[][] deltaHidden_Weights = new double[net.getNumber_Of_Inputs()][net.getNumber_Of_Hidden()];
		double[][] deltaOutput_Weights = new double[net.getNumber_Of_Hidden()][net.getNumber_Of_Outputs()];
		
		double[][] newOutput_Hidden = new double[net.getNumber_Of_Hidden()][net.getNumber_Of_Outputs()];
		double[][] newInput_Hidden = new double[net.getNumber_Of_Inputs()][net.getNumber_Of_Hidden()];
		double[] local_gradient = new double[net.getNumber_Of_Outputs()];
		double[] gradient_hidden = new double[net.getNumber_Of_Hidden()];
		double Error;
		
		for(int i = 0;i<size;i++)
		{
			for(int j=0;j<ds.getInputCount();j++)
			{
				input[j] = ds.getInput(i, j);
				
			}
			hidden_output = net.getHiddenOutput(input);
			output = net.activate(input);
			desired = ds.getOutput(i);
			
			for(int j =0;j<ds.getOutputCount();j++)
			{
				local_gradient[j] = (desired[j] - output[j])*
									output[j]*(1-output[j]);
				for(int k =0;k<net.getNumber_Of_Hidden();k++)
				{
					deltaOutput_Weights[k][j] *= momentum;
					deltaOutput_Weights[k][j] += alpha*
												local_gradient[j]*
												hidden_output[k];
					newOutput_Hidden[k][j] = deltaOutput_Weights[k][j] + 
											net.getHidden_Output()[k][j];
//					deltaOutput_Old_Old[k][j] = deltaOutput_Weights[k][j];
				}
				
			}
			net.setHidden_Output(newOutput_Hidden);
			for(int j =0;j<net.getNumber_Of_Hidden();j++)
			{
				double sum =0;
				
				for(int k =0;k<net.getNumber_Of_Outputs();k++)
				{
					sum = sum + local_gradient[k]*(net.getHidden_Output())[j][k];
				}
				gradient_hidden[j] = sum * hidden_output[j]*(1-hidden_output[j]);
				for(int k=0;k<net.getNumber_Of_Inputs();k++)
				{
					deltaHidden_Weights[k][j] *= momentum;
					deltaHidden_Weights[k][j] += alpha*
												gradient_hidden[j]*
												input[k];
					newInput_Hidden[k][j] = deltaHidden_Weights[k][j] + net.getInputHidden()[k][j];
//					deltaOutput_Old[k][j] = deltaHidden_Weights[k][j];
				}
				
			}
			net.setInput_Hidden(newInput_Hidden);
			Error = (desired[0] - output[0])*(desired[0] - output[0]);
			Errors[i] = Error;
//			System.out.println("* Error ="+Error+"*");
			
		}	
	}
	
	
	public static int  xor(int x, int y)
	{
		if(x!=y)
		{
			return 1;
		}
		else return 0;
	}
	public static void main(String[] args)
	{
		DataSet ds = new DataSet(2,1);
		int size = 100000;
		ds.setDataSetLength(size);
		Random rand = new Random();
		for(int i=0;i<size;i++)
		{
			int j = rand.nextInt(2);
			int k = rand.nextInt(2);
			ds.setInput(i,0, j);
			ds.setInput(i,1,k);
			ds.setOutput(i, 0, xor(j,k));
		}
//		System.out.println(ds.getOutput(100, 0));
		NeuralNet net = new NeuralNet(2,3,1);
		double alpha =1;
		double momentum = 0.3;
		Trainer trainer = new Trainer(net, ds, alpha, momentum);
		trainer.train();
		double[][] pattern = {{0,0},{1,0},{0,1},{1,1}};
		for(int i=0;i<4;i++){
			System.out.println(net.activate(pattern[i])[0]);
		}
//		PlottingData  plot = new PlottingData(trainer.Errors);
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new PlottingData(trainer.Errors,1000));
		f.setSize(400,400);
		f.setLocation(200,200);
		f.setVisible(true);
		
		NetworkWriter writer = new NetworkWriter(net, "Xor_Params");
		writer.write();    
		
	}
	
}
