import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Formatter;


public class Operator {
	//public WeightedGraph<String> graph = new WeightedGraph<String>();
	public WeightedGraph<String> graph;
	static Formatter formatter = new Formatter(System.out);
	String route = "1";
	String filename = "test.txt";


	public void enter() throws IOException 
	{
		System.out.println("\ninput \"enter\" to continue......");
		new BufferedReader(new InputStreamReader(System.in)).readLine();
	}
	
	public void cleanGraph()
	{
		graph = new WeightedGraph<String>();
	}

	public void findShortestPath(String route)
	{
		int n = graph.getNodeNumber();
		int[] weight = new int[n];
		String[] result = new String[n];
		int we = 0;
		int re = 0;

		weight = graph.getShortestWeight(route);

		// System.out.println("Source route:" + (Integer.valueOf(route)+1));
		// System.out.println("route      |Distance");
		// System.out.println("--------------------");		
		for(int i = 0; i < graph.getNodeNumber(); i++) 
		{
			String target = String.valueOf(i);
			ArrayList<Integer> list = graph.shortestPath(route,target);
			//System.out.println("test ");
			//System.out.println("result:");
			Collections.reverse(list);
			for(int tep:list)
			{	
				//System.out.print((Integer.valueOf(tep)+1)+"--->");
				if(result[i]==null){
					result[i] = (Integer.valueOf(tep)+1)+"--->";
					continue;
				}
				result[i] = result[i]+(Integer.valueOf(tep)+1)+"--->";
			}
			result[i] = result[i]+(i+1);
			//System.out.println(Integer.valueOf(target)+1);
			// LinkedQueue<Integer> path = graph.shortestPath(route,target);
			// String p = "";
			// while(!path.isEmpty()) 
			// {
			// 	try {
			// 		p = p+","+path.dequeue();
			// 	} catch (QueueUnderflowException e) {
			// 		// TODO Auto-generated catch block
			// 		e.printStackTrace();
			// 	}
			// }
			// System.out.println("R"+(Integer.valueOf(target)+1)+"         |"+ p);
		}
		System.out.println("Source route:" + (Integer.valueOf(route)+1));
		System.out.println("route      |Path                   |Distance");
		System.out.println("---------------------------------------------");
		for(int i = 0; i < graph.getNodeNumber(); i++)
		{
			// System.out.println((i+1)+"          |"+result[i]+"     |"+weight[i]);
			formatter.format("%-10s %-20s %5s\n",(i+1)+"","|"+result[i],"|"+weight[i]);
		}	
	}
	
	public void readFile(String filename) 
	{

		try{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			cleanGraph();
			//reader.readLine();
			String line = null;
			int i = 0;
			while((line = reader.readLine()) != null) 
			{
				//System.out.println(i);
				//String item[] = line.split(",");
				String item[] = line.split("\\s+");
				if(i == 0) {
					for(int m = 0; m < item.length; m++) 
					{
						//System.out.println(item[m]);
						graph.addVertex(String.valueOf(m));
					}
				}
				if(item.length > WeightedGraph.DEFCAP) 
				{
					System.out.println("input out of bound");
					break;
				}
				for(int j = 0; j < item.length; j++) 
				{
//					matrix[i][j] = Integer.valueOf(item[j]);
//					graph.edges[i][j] = Integer.valueOf(item[j]);
//					System.out.print(graph.edges[i][j]+",");
					graph.addEdge(String.valueOf(i),String.valueOf(j), Integer.valueOf(item[j]));
				}		
//				graph.changeVertex(String.valueOf(i), i);
				i++;
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void writeFile(String filename, int[] matrix) 
	{
		
	}
	public void printMatrix() 
	{
		graph.showMatrix();
	}	

	public void getForwardTable(String route)
	{
		int[] ft = graph.forwardTable(route);
		System.out.println("source route is : R" + 	(Integer.valueOf(route)+1));
		System.out.println("Destination           |Interface");
		System.out.println("--------------------------------");
		for(int i = 0; i < graph.getNodeNumber(); i++)
		{
			if(ft[i] == 0){
				System.out.println("R"+(Integer.valueOf(graph.getVertices(i))+1) + "                    |" + "-");
				continue;
			}
			System.out.println("R"+(Integer.valueOf(graph.getVertices(i))+1) + "                    |" + ft[i]);
		}

	}
	public static  void main(String[] args) throws IOException 
	{
		Operator op = new Operator();
		boolean flag = true;
		Scanner s;
		op.readFile(op.filename);
		
		while(flag) 
		{
			System.out.println("please choose the opetion");
			System.out.println("(1)Input a Network Topology"
					+ "\n(2)Create a Forward Table"
					+ "\n(3)Paths from Source to Destination"
					+ "\n(4)Update Network Topology"
					+ "\n(5)Best Router for Broadcast"
					+ "\n(6)"
					+ "\n(7)Exit");
			System.out.print("input value:");
			s = new Scanner(System.in);
			String option = null;
			if(s.hasNextLine()) 
			{
				option = s.nextLine();
			}
			switch(option) {
				case "1":
					op.cleanGraph();
					System.out.println("Please input your file name:");
					Scanner s1 = new Scanner(System.in);
					op.filename = null;
					if(s1.hasNextLine())
					{
						op.filename = s1.nextLine();
					}
					System.out.println(op.filename);
					op.readFile(op.filename);
					op.printMatrix();
					op.enter();
					break;
				case "2":
					System.out.println("operation 2");
					System.out.println("Please Choose a route:");
					Scanner s2 = new Scanner(System.in);
					if(s2.hasNextLine())
					{
						op.route = s2.nextLine();
					}
					//because the array start from 0 and route start from 1, we need to maek them harmony.
					op.route = String.valueOf(Integer.valueOf(op.route)-1);
					op.getForwardTable(op.route);
					op.enter();
					break;
				case "3":
					System.out.println("operation 3");	
					op.findShortestPath("3");
					op.enter();
					break;
				case "4":
					System.out.println("operation 4");
					Scanner s4 = new Scanner(System.in);
					System.out.println("1.Add a new vertex");
					System.out.println("2.Add a new edges");
					String c = "1";
					if(s4.hasNextLine())
					{
						op.route = s4.nextLine();
					}
					if(c.equals("1"))
					{
						System.out.println("add a new router.....");
						String newVertex = String.valueOf(graph.getNodeNumber());
						graph.addVertex(newVertex);
						System.out.println("a new router R"+graph.getNodeNumber()+" has been added successfully.")
					}else if(c.equals("2"))
					{
						System.out.println("add a new edge");
					}else
					{
						System.out.println("please input a valied command");
					}
					op.enter();
					break;

				case "5":
					System.out.println("operation 5");
					op.enter();
					break;
				case "7":
					flag = false;
					System.out.println("Exit CS542 2019 Spring project. Good Bye!");
					break;
					//break;
				case "6":
					op.printMatrix();
					break; 
				default:
					System.out.println("please input valid value(1~6).");
					op.enter();
					break;
			}
		}
	}
}
