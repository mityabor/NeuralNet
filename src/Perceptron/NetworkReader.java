package Perceptron;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

//Debug this class
public class NetworkReader 
{
	public static final String INPUTS = "Inputs";
	public static final String HIDDEN = "Hidden";
	public static final String OUTPUTS = "Outputs";
	public static final String PARAMERTS = "Weights";
	public NeuralNet net = null;
	public void readNetwork(String filename)
	{
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(filename);
		Document document = null;
		int input,hidden,output;
		
		try
		{
		    document = builder.build(filename);
//		    document = (Document)builder.build(filename);
			Element rootNode = document.getRootElement();
//			for(Attribute str : rootNode.getAttributes())
//			{
//				
//				System.out.println("RootAttributes" + str.getQualifiedName()+" = "+str.getDoubleValue());
//			}
			input = rootNode.getAttribute(INPUTS).getIntValue();
			hidden = rootNode.getAttribute(HIDDEN).getIntValue();
			output = rootNode.getAttribute(OUTPUTS).getIntValue();
			net = new NeuralNet(input, hidden, output);
//			System.out.println("Root name of doc:"+rootNode.getName());
//			System.out.println("Root content:"+rootNode.getContent());
			List<Element> childs = rootNode.getChildren();
			
			//Need To Parse  String
			for(Element child : childs)
			{
				System.out.println(child.getName());
				List<Element> inner_childs = child.getChildren();
				for(Element inner_child:inner_childs)
				{
					
					String str = inner_child.getAttribute(PARAMERTS).getValue();
					System.out.println(str);
					String Aux="";
					ArrayList<String> str_list = new ArrayList();
					for(int i=0;i<str.length();i++)
					{
						if(str.charAt(i)!=' '&&i!=str.length()-1)
						{
							Aux +=str.substring(i, i+1); 
						}
						else
						{	if(i==str.length()-1) Aux +=str.substring(i, i+1);
							str_list.add(Aux);
							Aux ="";
						}
					}
				}
				
			}
		}
		catch (IOException io) 
		{
			System.out.print("NO File"+"\t");
			System.out.println(io.getMessage());
		} 
		catch (JDOMException jdomex) 
		{
			System.out.println(jdomex.getMessage());
		}
		
		//Element rootNode = document.getRootElement();
		//System.out.println(document.getContent());
		//System.out.println(rootNode.getName());
	}
	public static void main(String[] args)
	{

		SAXBuilder builder = new SAXBuilder();
		
		File xmlFile = new File("D:\\file.xml");
	 
		try {
	 
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren("staff");
	 
			for (int i = 0; i < list.size(); i++) {
	 
			Element node = (Element) list.get(i);
	 
			System.out.println("First Name : " + node.getChildText("firstname"));
			System.out.println("Last Name : " + node.getChildText("lastname"));
			System.out.println("Nick Name : " + node.getChildText("nickname"));
			System.out.println("Salary : " + node.getChildText("salary"));
	 
			}
	 
		  } catch (IOException io) {
			System.out.println(io.getMessage());
		  } catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		  }
		String str = "rtjkh dgjfl df";
		String Aux="";
		ArrayList<String> str_list = new ArrayList();
		for(int i=0;i<str.length();i++)
		{
			if(str.charAt(i)!=' '&&i!=str.length()-1)
			{
				Aux +=str.substring(i, i+1); 
			}
			else
			{	if(i==str.length()-1) Aux +=str.substring(i, i+1);
				str_list.add(Aux);
				Aux ="";
			}
		}
		System.out.println(str_list.size());
		for(String index_str :str_list )
		{
			System.out.println(index_str);
		}
		String filename = "Xor_Params"; 
		NetworkReader reader = new NetworkReader();
		reader.readNetwork(filename);
	}
	
}
