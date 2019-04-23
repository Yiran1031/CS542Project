

public class LinkedQueue<T> implements QueueInterface<T> 
{
	protected LLNode<T> front;
	protected LLNode<T> rear;
	protected int numElements = 0;
	
	public LinkedQueue() 
	{
		front = null;
		rear = null;
	}
	@Override
	public void enqueue(T element) throws QueueOverflowException {
		// TODO Auto-generated method stub
		LLNode<T> newNode = new LLNode<T>(element);
		if(rear == null) 
		{
			front = newNode;
		}else 
		{
			rear.setLink(newNode);
		}
		rear = newNode;
		numElements++;
	}
	
	@Override
	public T dequeue() throws QueueUnderflowException {
		// TODO Auto-generated method stub
		if(isEmpty())
			throw new QueueUnderflowException("Dequeue attempted on empty queue");
		else 
		{
			T element;
			element = front.getInfo();
			front = front.getLink();
			if(front == null)
				rear = null;
			numElements--;
			return element;
		}
	}
	public T peek() 
	{
		if(numElements != 0) 
		{
			return  front.info;
		}else
			return null;
	}
	
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		//linkList never full
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(numElements == 0)
			return true;
		else
			return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return numElements;
	}
}