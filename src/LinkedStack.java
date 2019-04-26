
public class LinkedStack<T> {
	protected LLNode<T> top;
	
	public LinkedStack() 
	{
		top = null;
	}
	
	public void push(T element) 
	{
		LLNode<T> newNode = new LLNode<T>(element);
		newNode.setLink(top);
		top = newNode;
	}
	public boolean isEmpty() 
	{
		return (top == null);
	}
	public boolean isFull() 
	{
		return false;
	}
	public void pop() 
	{
		if(isEmpty()) 
			System.out.println("The stack is empty");
		else
			top = top.getLink();
	}
	public T getTop() 
	{		
		return top.getInfo();
	}
	
	public int getNum() {
		int i = 0;
		LLNode<T> temp = top;
		while(temp != null) 
		{
			i++;
			temp = temp.getLink();
		}
		return i;
	}
	public T[] getAll() {
		T[] result = (T[])new Object[getNum()];
		LLNode<T> temp = top;
		for(int i = 0; i< getNum();i++) 
		{
			result[i] = temp.getInfo();
			temp = temp.getLink();
		}
		return result;
	}
	
	public boolean exist(T target) 
	{
		LLNode<T> temp = top;
		while(temp != null) 
		{
			if(temp.getInfo().equals(target)) 
			{
				return true;
			}else 
			{
				temp = temp.getLink();
			}
		}
		return false;
	}
	public void clean() 
	{
		top = null;
	}
 //	public LinkedStack<T> clone()
//	{
//		LLNode<T> temp = top;
//		LinkedStack<T> newStack = new LinkedStack<T>();
//		while(temp != null) 
//		{
//			newStack =
//		}
//		
//		return null;
//	}
}
