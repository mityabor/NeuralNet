package Perceptron;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;

public class DataSet 
{
	private int InputCount;
	private int OutputCount;
	private int DataSetLength;
	private double input[][];
	private double output[][];
	
	public DataSet(int InputCount, int OutputCount)
	{	
		this.InputCount = InputCount;
		this.OutputCount = OutputCount;
		this.DataSetLength = 0;
	}
	
	public void setDataSetLength(int length)
	{
		this.DataSetLength = length;
		this.input = new double[length][InputCount];
		this.output = new double[length][OutputCount];
	}
	
	public int getDataSetLength()	
	{
		return DataSetLength;
	}
	
	public int getInputCount()
	{
		return InputCount;
	}
	
	public int getOutputCount()
	{
		return OutputCount;
	}
	
	public void setInput(int set,int index, double value) throws RuntimeException
	{
		if((set<0) ||(set>=DataSetLength))
		{
			throw new RuntimeException("Out of DataSetLength");
		}
		
		if((index<0)||(index>=InputCount))
		{
			throw new RuntimeException("Out of Input range");
		}
		
		this.input[set][index] = value;
	}
	
	public double getInput(int set,int index) throws RuntimeException
	{
		if((set<0)||set>=DataSetLength)
		{
			throw new RuntimeException("Out of set range");
		}
		
		if((index<0)||(index>=InputCount))
		{
			throw new RuntimeException("Out of the input count");
		}		
		return this.input[set][index];	
	}
	
	public double[] getInput(int set) throws RuntimeException
	{
		if((set<0)||set>=DataSetLength)
		{
			throw new RuntimeException("Out of set range");
		}
		return input[set];
	}
	
	public void setOutput(int set, int index, double value) throws RuntimeException
	{
		if((set<0)||(set>=DataSetLength))
		{
			throw new RuntimeException("Out of DataSetLength");
		}
		
		if((index <0)||(index>=OutputCount))
		{
			throw new RuntimeException("Out of Output range");
		}
		this.output[set][index] = value;
	}
	
	public double getOutput(int set, int index)
	{
		if((set<0)||(set>=DataSetLength))
		{
			throw new RuntimeException("Out of Data set length");
		}
		if((index<0)||(index>=OutputCount))
		{
			throw new RuntimeException("Out of Output range");
		}
		return output[set][index];
	}
	
	public double[] getOutput(int set)
	{
		if((set<0)||(set>=DataSetLength))
		{
			throw new RuntimeException("Out of Data set length");
		}
		return output[set];
	}
	public static void main(String[] args)
	{
		DataSet ds = new DataSet(5,6);
		ds.setDataSetLength(1000);
		ds.setInput(0, 3, 13);
		ds.setOutput(0, 0, 1.21);
		System.out.println("Number Of Inputs = "+ds.getInputCount()+
							"\nNumber Of Outputs = "+ds.getOutputCount()
							+"\nDataSetLength = "+ds.getDataSetLength()
							+"\nDataSet[i][j] = "+ds.getInput(0, 0)
							+"\nDataSet[i][j] = "+ds.getOutput(0, 0)
							+"\nDataSet[]"+ds.getOutput(0)[0]
							+"\nDataSet[]"+ds.getInput(0)[3]
							);
		//double inpt[][] = new double[23][32];
		//System.out.println(inpt[22][31]);
		
	}
}
