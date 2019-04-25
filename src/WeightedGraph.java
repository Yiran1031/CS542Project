import java.util.ArrayList;

public class WeightedGraph<T>{
	public static final int NULL_EDGE = -1;
	public static final int DEFCAP = 10;
	private static final int MAX_WEIGHT = 10000;
	private int numVertices;
	private int maxVertices;
	public T[] vertices;
	public int[][] edges;
	private boolean[] marks;
	public WeightedGraph() 
	{
		numVertices = 0;
		maxVertices = DEFCAP;
		vertices = (T[]) new Object[DEFCAP];
		marks = new boolean[DEFCAP];
		edges = new int[DEFCAP][DEFCAP];
	}
	// instantiates a graph with capacity maxV
	public void changeVertex(T c, int index) 
	{
		vertices[index] = c; 
	}
	public WeightedGraph(int maxV) 
	{
		numVertices = 0;
		maxVertices = maxV;
		vertices = (T[]) new Object[maxV];
		marks = new boolean[maxV];
		edges = new int[maxV][maxV];
	}
	public int getNodeNumber() 
	{
		return numVertices;
	}
	public void addVertex(T vertex)
	{
		vertices[numVertices] = vertex;
		for(int index = 0; index < numVertices; index++) 
		{
			edges[numVertices][index] = NULL_EDGE;
			edges[index][numVertices] = NULL_EDGE;
		}
		//System.out.println(vertices[numVertices]);
		numVertices++;
	}
	public int indexIs(T vertex) 
	{
		int index = 0;
		while(!vertex.equals(vertices[index]))
		{
			//System.out.println(vertices[i]);
			index++;
		}
		return index;
	}
	
	public void addEdge(T fromVertex, T toVertex, int weight)
	{
		int row;
		int column;
		
		row = indexIs(fromVertex);
		column = indexIs(toVertex);
		edges[row][column] = weight;
	}
	
	public T getVertices(int index)
	{
		return vertices[index];
	}
	public int weightIs(T fromVertex, T toVertex)
	{
		int row;
		int column;
		row = indexIs(fromVertex);
		column = indexIs(toVertex);
		return edges[row][column];
	}
	// public QueueInterface<T> getToVertices(T vertex)
	// {
	// 	QueueInterface<T> adjVertices = new LinkedQueue<T>();
	// 	int fromIndex, toIndex;
	// 	fromIndex = indexIs(vertex);
	// 	for(toIndex = 0; toIndex < numVertices; toIndex++) 
	// 	{
	// 		if(edges[fromIndex][toIndex] != NULL_EDGE) {
	// 			try {
	// 				adjVertices.enqueue(vertices[toIndex]);
	// 			} catch (QueueOverflowException e) {
	// 				// TODO Auto-generated catch block
	// 				e.printStackTrace();
	// 			}
	// 		}
	// 	}
	// 	return adjVertices;
	// }	 	
	
	public boolean isComplete(boolean marks[])
	{
		boolean result = true;
		for(int i = 0; i < numVertices; i++) 
		{
			if(!marks[i])
				result = false;
		}
		return result;
	}

	public int findMin(int[] a) 
	{
		int min = 10000;
		int index = 0;
		int minIndex = 0;
		while(index < a.length) 
		{
			if(a[index] < min)
				minIndex = index;
			index++;
		}
//		for(int i = 0; i < a.length; i++) 
//		{
//			if(a[i] < min)
//				min = a[i];
//		}
		return minIndex;
	}
	public void showMatrix() 
	{
		for(int i = 0; i < numVertices; i++) 
		{
			System.out.print("\n");
			for(int j = 0; j < numVertices;j++) 
			{
				System.out.print(edges[i][j]+",");
			}
		}
	}
	public void showGraph() 
	{
		System.out.println("vertex|    ");
	}
	public void cleanMarks() 
	{
		for(int i = 0; i < marks.length; i++) 
		{
			marks[i] = false;
		}
	}
	public int[] getShortestWeight(T vertex)
	{
		cleanMarks();
		int source = indexIs(vertex);
		marks[source] = true;
		
		int[] dist = new int[numVertices];
		for(int i = 0; i < dist.length; i++) 
		{
			dist[i] = MAX_WEIGHT;
		}
		
		dist[source] = 0;
		int start = source;
			
		int[] path = new int[numVertices];		
//		int temp = 0;
		for(int i = 0; i < numVertices; i++) 
		{
			path[i] = -1;
		}
		
		// LinkedQueue<T> path = new LinkedQueue<T>();
		// int prev = MAX_WEIGHT;
		
		while(!isComplete(marks)) 
		{
			
			//showMarks(marks);
//			System.out.println("");
			boolean flag = false;
			
			for(int toVertex = 0; toVertex < numVertices; toVertex++) 
			{
				if(edges[start][toVertex] != NULL_EDGE && !marks[toVertex]) 
				{
					if(dist[toVertex] > edges[start][toVertex]+dist[start]) 
					{
						dist[toVertex] = edges[start][toVertex]+dist[start];
						path[toVertex] = start;
					}
//					System.out.print(toVertex+",");
				}
			}

			
						
			//System.out.println("process test2");
			int minValue = MAX_WEIGHT;
			int minIndex = start;
			for(int i = 0;i < numVertices; i++) 
			{
				if(!marks[i] && minValue > dist[i]) 
				{
					minValue = dist[i];
					minIndex = i;
				}
			}
			
				// try {
				// 	path.enqueue(vertices[start]);
				// } catch (QueueOverflowException e) {
				// 	// TODO Auto-generated catch block
				// 	e.printStackTrace();
				// }
			
			start = minIndex;
			marks[start] = true;
			//System.out.println("length:"+path.length);
			//System.out.println(" ");
//			for(int i = 0; i < numVertices; i++) 
//			{
//				System.out.print(dist[i]+",");
//			}
//			System.out.println("choose"+ start);
			
		}

		return dist;
	}


	public ArrayList<Integer> shortestPath(T vertex, T target) 
	{
		cleanMarks();
		int source = indexIs(vertex);
		marks[source] = true;
		
		int[] dist = new int[numVertices];
		for(int i = 0; i < dist.length; i++) 
		{
			dist[i] = MAX_WEIGHT;
		}
		
		dist[source] = 0;
		int start = source;
			
		int[] path = new int[numVertices];		
//		int temp = 0;
		for(int i = 0; i < numVertices; i++) 
		{
			path[i] = -1;
		}
		path[source] = source; 
		
		// LinkedQueue<T> path = new LinkedQueue<T>();
		// int prev = MAX_WEIGHT;
		
		while(!vertices[start].equals(target)) 
		{
			
			//showMarks(marks);
//			System.out.println("");
			boolean flag = false;
			
			for(int toVertex = 0; toVertex < numVertices; toVertex++) 
			{
				if(edges[start][toVertex] != NULL_EDGE && !marks[toVertex]) 
				{
					if(dist[toVertex] > edges[start][toVertex]+dist[start]) 
					{
						dist[toVertex] = edges[start][toVertex]+dist[start];
						path[toVertex] = start;
					}
//					System.out.print(toVertex+",");
				}
			}

			
						
			//System.out.println("process test2");
			int minValue = MAX_WEIGHT;
			int minIndex = start;
			for(int i = 0;i < numVertices; i++) 
			{
				if(!marks[i] && minValue > dist[i]) 
				{
					minValue = dist[i];
					minIndex = i;
				}
			}
			
				// try {
				// 	path.enqueue(vertices[start]);
				// } catch (QueueOverflowException e) {
				// 	// TODO Auto-generated catch block
				// 	e.printStackTrace();
				// }
			
			start = minIndex;
			marks[start] = true;
			//System.out.println("length:"+path.length);
			//System.out.println(" ");
//			for(int i = 0; i < numVertices; i++) 
//			{
//				System.out.print(dist[i]+",");
//			}
//			System.out.println("choose"+ start);
			
		}
		// return dist[start];

		ArrayList<Integer> list = getShortestPath(path,source,start);
		return list;
		
	}

	public ArrayList<Integer> getShortestPath(int[] path,int source,int start)
	{
		// int[] path = new int[numVertices];

		// int temp = i.length-1;
		// int k = 0;
		// while(k < i.length)
		// {
		// 	path[k] = i[i.length-k-1];
		// 	k++;
		// }

		//System.out.println("\npath list is");
		// for(int n = 0; n < path.length;n++)
		// {
		// 	System.out.print(path[n]+",");
		// }


		// System.out.println("array2");
		// for(int n = 0; n < path.length;n++)
		// {
		// 	System.out.print(path[n]+",");
		// }
		ArrayList<Integer> a = new ArrayList<Integer>();
		//System.out.println("\nStart is :"+ (Integer.valueOf(start)+1));
		int p = start;
		while(p < path.length)
		{

			if(path[p] == source)
			{
				a.add(path[p]);
				break;
			}

			if(path[p] == -1)
			{
				break;
			}

			//System.out.println("node: "+path[p]+"is added");
			a.add(path[p]);
			p = path[p];
		}

		// for(int tep:a)
		// {
		// 	System.out.print(tep);
		// }

		// System.out.println("test---1");
		// while(!s.isEmpty()){
		// 	System.out.println("test---2");
		// 	try{
		// 		System.out.println("test---2");
		// 		int m = s.dequeue();
		// 		System.out.print(m+",");
		// 		System.out.println("test---2");
		// 	}catch(Exception e) {
		// 		// TODO Auto-generated catch block
		// 		e.printStackTrace();
		// 	}

		// }

		return a;
  	}

	public void showMarks(boolean marks[]) 
	{
		System.out.print("\n");
		for(int i = 0; i < marks.length; i++) 
		{
			System.out.print(marks[i]+",");
		}
	}
//	private static void shortestPaths(WeightedGraphInterface<String> graph, String startVertex)
//	{
//		int route;
//		int saveRoute;
//		int minDistance;
//		int newDistance;
//		do {
//			route = 
//		}
//	}
	public int[] forwardTable(T route)
	{
		int start = indexIs(route);
		int[] forwardTable = new int[numVertices];
		for(int toIndex = 0; toIndex < numVertices; toIndex++) 
		{
			if(edges[start][toIndex] != NULL_EDGE) {
				forwardTable[toIndex] = edges[start][toIndex];
			}else
			{
				forwardTable[toIndex] = 99999;
			}
		}
		return forwardTable;
	}
}
