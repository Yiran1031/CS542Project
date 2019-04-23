import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class Operator {
	public WeightedGraph<String> graph = new WeightedGraph<String>();

	public void enter() throws IOException 
	{
		System.out.println("\ninput \"enter\" to continue......");
		new BufferedReader(new InputStreamReader(System.in)).readLine();
	}
	
	public void findShortestPath(String route)
	{
		System.out.println("Source route:" + (Integer.valueOf(route)+1));
		System.out.println("route      |Distance");
		System.out.println("--------------------");		
		for(int i = 0; i < graph.getNodeNumber(); i++) 
		{
			String target = String.valueOf(i);
			LinkedQueue<String> path = graph.shortestPath(route,target);
			String p = "";
			while(!path.isEmpty()) 
			{
				try {
					p = p+","+path.dequeue();
				} catch (QueueUnderflowException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("R"+(Integer.valueOf(target)+1)+"         |"+ p);
		}
	}
	
	public void readFile(String filename) 
	{

		try{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			//reader.readLine();
			String line = null;
			int i = 0;
			while((line = reader.readLine()) != null) 
			{
				//System.out.println(i);
				String item[] = line.split(",");
				if(i == 0) {
					for(int m = 0; m < item.length; m++) 
					{
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
	public static  void main(String[] args) throws IOException 
	{
		Operator op = new Operator();
		boolean flag = true;
		Scanner s;
		op.readFile("D:\\code\\java\\router.csv");
		
		while(flag) 
		{
			System.out.println("please choose the opetion");
			System.out.println("(1)Input a Network Topology"
					+ "\n(2)Create a Forward Table"
					+ "\n(3)Paths from Source to Destination"
					+ "\n(4)Update Network Topology"
					+ "\n(5)Best Router for Broadcast"
					+ "\n(6)Exit");
			System.out.print("input value:");
			s = new Scanner(System.in);
			String option = null;
			if(s.hasNextLine()) 
			{
				option = s.nextLine();
			}
			switch(option) {
				case "1":
					System.out.println("operation 1");
					op.printMatrix();
					op.enter();
					break;
				case "2":
					System.out.println("operation 2");
					op.enter();
					break;
				case "3":
					System.out.println("operation 3");	
					op.findShortestPath("3");
					op.enter();
					break;
				case "4":
					System.out.println("operation 4");
					op.enter();
					break;
				case "5":
					System.out.println("operation 5");
					op.enter();
					break;
				case "6":
					flag = false;
					System.out.println("Exit CS542 2019 Spring project. Good Bye!");
					break;
					//break;
				default:
					System.out.println("please input valid value(1~6).");
					op.enter();
					break;
			}
		}
	}
}
