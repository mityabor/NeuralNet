package Perceptron;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class NetworkWriter {
	
	private NeuralNet net;
	private String filename;
	public NetworkWriter(NeuralNet net,String filename)
	{
		this.net = net;
		this.filename = filename;
	}
	public void  write()
	{
		Element root =  new Element("FeedForwardNetwork");
		root.setAttribute("Inputs",String.valueOf(net.getNumber_Of_Inputs()));
		root.setAttribute("Hidden",String.valueOf(net.getNumber_Of_Hidden()));
		root.setAttribute("Outputs",String.valueOf(net.getNumber_Of_Outputs()));
		
		Element input_hidden = new Element("Input_Hidden_Layer");
		root.addContent(input_hidden);
		
		Element output_hidden = new Element("Hidden_Output_Layer");
		root.addContent(output_hidden);
		
		Element parametrs_hidden = new Element("Parametrs");
		input_hidden.addContent(parametrs_hidden);
		
		Element parametrs_output = new Element("Parametrs");
		output_hidden.addContent(parametrs_output);
		String Params="";
		for(int i=0;i<net.getNumber_Of_Inputs();i++)
		{
			for(int j=0;j<net.getNumber_Of_Hidden();j++)
			{
				Params +="\t" + String.valueOf(net.getInputHidden()[i][j]);
//				parametrs_hidden.setAttribute("W_hid"+i+j,
//												String.valueOf(net.getInputHidden()[i][j]));
			}
		}
		parametrs_hidden.setAttribute("Weights",Params);
		Params ="";
		for(int i=0;i<net.getNumber_Of_Hidden();i++)
		{
			for(int j=0;j<net.getNumber_Of_Outputs();j++)
			{
				Params +="\t" + String.valueOf(net.getHidden_Output()[i][j]);
//				parametrs_output.setAttribute("W_out"+i+j,
//												String.valueOf(net.getHidden_Output()[i][j]));
			}
		}
		parametrs_output.setAttribute("Weights",Params);
		Document doc = new Document(root);
		XMLOutputter output = new XMLOutputter();
		output.setFormat(Format.getPrettyFormat());
		try
		{
			output.output(doc, new FileOutputStream(filename));
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
