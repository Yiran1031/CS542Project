import java.util.ArrayList;

public class WeightedGraph<T>{
	public static final int NULL_EDGE = -1;
	public static final int DEFCAP = 5;
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
	
	public int weightIs(T fromVertex, T toVertex)
	{
		int row;
		int column;
		row = indexIs(fromVertex);
		column = indexIs(toVertex);
		return edges[row][column];
	}
	public QueueInterface<T> getToVertices(T vertex)
	{
		QueueInterface<T> adjVertices = new LinkedQueue<T>();
		int fromIndex, toIndex;
		fromIndex = indexIs(vertex);
		for(toIndex = 0; toIndex < numVertices; toIndex++) 
		{
			if(edges[fromIndex][toIndex] != NULL_EDGE) {
				try {
					adjVertices.enqueue(vertices[toIndex]);
				} catch (QueueOverflowException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return adjVertices;
	}
	 
	public int[] Dijkstra(T vertex) 
	{
		int source;
		int start;
		int preIndex;
		source = indexIs(vertex);
		start = source;
		//preIndex = source;
		int[] dist = new int[DEFCAP];
		for(int i = 0; i < DEFCAP; i++) 
		{
			dist[i] = MAX_WEIGHT;
		}
		
		while(isComplete(marks)) 
		{
			for(int i = 0; i < numVertices; i++) 
			{
				if(edges[start][i] != NULL_EDGE && !marks[i]) 
				{
					if(dist[i] > dist[start]+edges[start][i]) 
					{
						dist[i]=dist[start]+edges[start][i];
					}
				}
				
			}
			int min = MAX_WEIGHT;
			int minIndex = -1;
			for(int i = 0; i < dist.length; i++) 
			{
				if(marks[i])
					continue;
				if(min > dist[i]) 
				{
					min = dist[i];
					minIndex = i;
				}
			}
			start = minIndex;
		}
		
		return dist;
		

		//		int fromIndex,toIndex;
//		fromIndex = indexIs(vertex);
//		int[] dist = new int[numVertices];
//		int[] prev = new int[numVertices];
		
		//ArrayList<String> path = new ArrayList<String>();
//
//		for(int i =0; i < numVertices; i ++) 
//		{
//			if(edges[fromIndex][i] != NULL_EDGE) 
//			{
//				dist[i] = edges[fromIndex][i];
//				prev[i] = edges[fromIndex][i];
//			}else 
//			{
//				dist[i] = 10000;
//				prev[i] = 10000;
//			}
//			//marks[i] = false;
//		}
//		int minIndex = findMin(dist);
//		marks[minIndex] = true;

	}
	
	
	public boolean isComplete(boolean marks[])
	{
		boolean result = true;
		for(int i = 0; i < marks.length; i++) 
		{
			if(!marks[i])
				result = false;
		}
		return result;
	}
//	public void markPath(T vertex, int dist[]) 
//	{
//		int start = indexIs(vertex);
//		int index = -1;
//		int weight = 10000;
//		for(int i = 0; i < numVertices; i++) 
//		{
//			if(edges[start][i] != NULL_EDGE && !marks[i]) 
//			{
//				if(dist[i] > (dist[start] + edges[start][i])) 
//				{
//					dist[i] = dist[start] + edges[start][i];
//				} 
//			}
//		}
//		
//		int min = 10000;
//		int minIndex = 0;
//		for(int i = 0; i < numVertices; i++) 
//		{
//			if(marks[i]) 
//				continue;
//			if(dist[i] < min) 
//			{
//				min = dist[i];
//				minIndex = i;
//			}
//		}
//		marks[minIndex] = true;
//	}
//	
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
		for(int i = 0; i < edges.length; i++) 
		{
			System.out.print("\n");
			for(int j = 0; j < edges[i].length;j++) 
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
	
	public LinkedQueue<T> shortestPath(T vertex, T target) 
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
		
//		T[] path = (T[])new Object[10];				
//		int temp = 0;
//		for(int i = 0; i < dist.length; i++) 
//		{
//			path[i] = null;
//		}
		
		LinkedQueue<T> path = new LinkedQueue<T>();
		int prev = MAX_WEIGHT;
		
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
			
				try {
					path.enqueue(vertices[start]);
				} catch (QueueOverflowException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			start = minIndex;
			marks[start] = true;
			//System.out.println(" ");
//			for(int i = 0; i < numVertices; i++) 
//			{
//				System.out.print(dist[i]+",");
//			}
//			System.out.println("choose"+ start);
			
		}
		return path;
		
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
}
