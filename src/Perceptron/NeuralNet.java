package Perceptron;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Date;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Document;
import org.jdom2.Element;

/**
 *						Todo : 
 *1)write xml func to write a xml file properly
 * 
 *2)Write Neural Net Params to xml 
 */
public class NeuralNet extends AbstractNeuralNet
{
	private double Input_Hidden_Weights[][];
	private double Hidden_Output_Weights[][];
	private int Number_Of_Inputs;
	private int Number_Of_Outputs;
	private int Number_Of_Hidden;
	
	
	public  NeuralNet(int input,int hidden,int output) 
	{
		Input_Hidden_Weights = new double[input][hidden];
		Hidden_Output_Weights = new double[hidden][output];
		Number_Of_Inputs = input;
		Number_Of_Hidden = hidden;
		Number_Of_Outputs = output;
		
		Random rand = new Random();
		for(int i = 0;i<input;i++)
		{
			for(int j=0;j<hidden;j++)
			{
				Input_Hidden_Weights[i][j] = (-1+2*rand.nextDouble())/2; 
			}
		}
		for(int i = 0;i<hidden;i++)
		{
			for(int j=0;j<output;j++)
			{
				Hidden_Output_Weights[i][j] =( -1 +2*rand.nextDouble())/2; 
			}
		}
	}
	public int getNumber_Of_Inputs()
	{
		return Number_Of_Inputs;
	}
	
	public int getNumber_Of_Hidden()
	{
		return Number_Of_Hidden;
	}
	public int getNumber_Of_Outputs()
	{
		return Number_Of_Outputs;
	}
	
	public void setInput_Hidden(double weights[][])
	{
		Input_Hidden_Weights = weights;
	}
	public double[][] getInputHidden()
	{
		return Input_Hidden_Weights;
	}
	
	public void  setInputHidden(double[][] weights)
	{
		this.Input_Hidden_Weights = weights;
	}
	
	public void setHiddenOutput(double[][]weights)
	{
		this.Hidden_Output_Weights = weights;
	}
	public void setHidden_Output(double weights[][])	
	{
		Hidden_Output_Weights = weights;
	}
	
	public double[][] getHidden_Output()
	{
		return Hidden_Output_Weights;
	}
	
	public void printHidden()
	{
		for(int i=0;i<Number_Of_Inputs;i++)
		{
			for(int j =0;j<Number_Of_Hidden;j++)
			{
				System.out.print("w[" +i+"]"+"["+j+"]"+" = " + Input_Hidden_Weights[i][j]+"  ");
			}
			System.out.println("");
		}
	}
	
	public void printOutput()
	{
		for(int i=0;i<Number_Of_Hidden;i++)
		{
			for(int j =0;j<Number_Of_Outputs;j++)
			{
				System.out.print("w[" +i+"]"+"["+j+"]"+" = " + Hidden_Output_Weights[i][j]+"  ");
			}
			System.out.println("");
		}
	}

	public double[] getHiddenOutput(double[] input)
	{
		double output[] = new double[Number_Of_Hidden];
		for(int i=0;i<Number_Of_Hidden;i++)
		{
			double inner_product =0.0;
			for(int j=0;j<Number_Of_Inputs;j++)
			{
				inner_product += Input_Hidden_Weights[j][i]*input[j];
			}
			output[i] = sigmoid(inner_product);
		}
		return output;
	}
	
	public double[] activate(double[] input)
	{
		double output[] = new double[Number_Of_Outputs];
		double hidden_output[] = new double[Number_Of_Hidden];
		hidden_output = getHiddenOutput(input);
		for(int i = 0;i<Number_Of_Outputs;i++)
		{
			double inner_droduct = 0.0;
			for(int j = 0;j<Number_Of_Hidden;j++)
			{
				inner_droduct += Hidden_Output_Weights[j][i]*hidden_output[j];
			}
			output[i] = sigmoid(inner_droduct);
		}
		return output;
	}
	public static double sigmoid(double x)
	{
		return 1.0/(1+Math.exp(-x));
	} 
	
	public static double inner_product(double[] x, double[] y )
	{
		if(x.length!=y.length)
		{
			throw new RuntimeException("Length of vectors is diffrent ");
		}
		else
		{
			double result =0;
			for(int i=0;i<x.length;i++)
			{
				result += x[i] * y[i];
			}
			return result;
		}
	
	}
	public void save()
	{
	
		
		
	}
	public static void main(String[] args)
	{
		System.out.println("Hello World");
		NeuralNet  nn = new NeuralNet(2,3,2);
		double input[] = {1,3};
		double input1[] = {2,4};
		try
		{
		System.out.println("Number Of Inputs = "+nn.getNumber_Of_Inputs()+"\n"+
						   "Number Of Hidden = "+nn.getNumber_Of_Hidden()+"\n"+
						   " Number of Outputs = "+nn.getNumber_Of_Outputs()+"\n"+
						   "Output from hidden[0] = "+nn.getHiddenOutput(input)[0]+"\n"+
						   "Output from hidden[1] = "+nn.getHiddenOutput(input)[1]+"\n"+
						   "Output from hidden[2] = "+nn.getHiddenOutput(input)[2]+"\n"+
						   "Output from Net[0] = "+nn.activate(input)[0]+"\n"+
						   "Output from Net[1] = "+nn.activate(input)[1]+"\n"+
						   "Inner product = "+inner_product(input, input1)+"\n"+
						   "Sigmoid test ="+sigmoid(10));
							
							nn.printHidden();
							nn.printOutput();
		}
		catch(RuntimeException e) 
		{
			e.printStackTrace();
		}
		
		Element root = new Element("newnet");
		Element child = new Element("child");
		child.setAttribute("name","mitya");
		child.setAttribute("work","programmer");
		Element Zhenya = new Element("Zhenya");
		Zhenya.setAttribute("name","Zhenya");
		Zhenya.setAttribute("work","Zhenya");
		root.addContent(child);
		root.addContent(Zhenya);
		Document xmlDoc = new Document(root);
		String filename = new String("XMLFILE");
		XMLOutputter output = new XMLOutputter();
		output.setFormat(Format.getPrettyFormat());
		try
		{
			output.output(xmlDoc, new FileOutputStream(filename));
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
