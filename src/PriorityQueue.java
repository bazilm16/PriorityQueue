
public class PriorityQueue {

	private int[] container;
	private int size;//this is the size or the number of the elements it the queue
	private int length;//this is the length of the container array
	private int pos; //this is the position of the

	public PriorityQueue(int len){
		container = new int[len];
		size = 0;
		pos = 1;
		length = len;
	}//PriorityQueue
	
	/**
	 * checks whether an index is a left child or it's a right child of it's parent
	 * @param childIndex, the index of the child we're checking
	 * @return, true if the child is a left Child else false otherwise
	 */
	private boolean isLeftChild(int childIndex){
		return childIndex % 2 == 0;
	}//isLeftChild
	
	/**
	 * checks whether an index is a right child or it's a right child of it's parent
	 * @param childIndex, the index of the child we're checking
	 * @return, true if the child is a right Child and false otherwise
	 */
	private boolean isRightChild(int childIndex){
		return !isLeftChild(childIndex);
	}//isRightChild
	
	/**
	 * gets the parent of selected node with the given index
	 * @param childIndex, this is the index of the child to be checked
	 * @return, the index of the parent
	 */
	private int getParent(int childIndex){
		int parent;
		if(isLeftChild(childIndex)){
			parent = childIndex / 2;
		}else{
			parent = (childIndex - 1) / 2;
		}
	return parent;
	}//getParent
	
	/**
	 * sifts or moves up the newly inserted element so that the behavior or characteristic
	 * of the queue is observed
	 * @param curr, this is the current position of the element we're trying to sift up
	 */
	private void siftUp(int curr){
		int temp, parent;
		while(curr != 1){
			parent = getParent(curr);//this is the parent of the current node
			if(container[curr] > container[parent]){
				temp = container[curr];//we store the value of the current node
				//we switch the current node with it's parent
				container[curr] = container[parent];
				container[parent] = temp;
			}
			//we set the current node equal to the parent so that we can compare it with it's parent as well
			curr = parent;
		}
	}//siftUp
	
	/**
	 * increases the size of the container by twice the current container
	 */
	private void growContainer(){
		int newLength = 2 * length;
		int[] newContainer = new int[newLength];
		for(int i = 0; i < length; i++){
			newContainer[i] = container[i];
		}
		container = newContainer;
		//increment the new length
		length = newLength;
	}//growContainer
	
	/**
	 * add an element to the queue
	 * @param val, the value to be added to the queue
	 */
	public void push(int val){
		if(pos >= length){
			//double the size of the array
			growContainer();
		}
		//add to the container
		container[pos] = val;
		//sift up 
		siftUp(pos);
		//increment the size of the container
		size++;
		//increment the current position of insertion
		pos++;
	}//push
	
	/**
	 * gets the left child of the node
	 * @param index, the index of the node we're checking
	 * @return, the index of the left child
	 */
	private int getLeftChild(int index){
		return 2 * index;
	}//getLeftChild
	
	/**
	 * gets the right child of a node
	 * @param index, the node we're checking
	 * @return, the index of it's right child
	 */
	private int getRightChild(int index){
		return 2 * index + 1;
	}//getRightChild
	
	/**
	 * gets the child node with the larger value of some parent
	 * @param index, the index of the parent node
	 * @return, the index with the larger node
	 */
	private int getLargerChild(int index){
		int leftChild = getLeftChild(index);
		int rightChild = getRightChild(index);
		return (leftChild > rightChild)?leftChild:rightChild;
	}
	
	/**
	 * moves the value down to it's relevant position on the queue
	 */
	private void siftDown(){
		int curr = 1, temp;
		while(curr < pos - 1){
			int larger = getLargerChild(curr);
			if(container[curr] < container[larger]){
				temp = container[curr];
				container[curr] = container[larger];
				container[larger] = temp;
			}
			curr = larger;
		}
	}//siftDown
	
	/**
	 * function to remove an element from the top of a queue
	 * @return the value of the object removed
	 */
	public int pop(){
		int maxVal = container[1];
		//we first replace the root value with the last element in the array
		container[1] = container[pos - 1];
		//sift down the element at the root so that it is in it's rightful place
		siftDown();
		//decrement the position and the size of the queue
		pos--;
		size--;
		return maxVal;
	}//pop
	
	/**
	 * prints the container
	 */
	public void print(){
		for(int i = 1; i <= size; i++){
			System.out.println(container[i]);
		}
		//System.out.println("the length of the container is " + container.length);
	}//print
	
	public static void main(String[] args) {
		PriorityQueue myQueue = new PriorityQueue(5);
		myQueue.push(2);
		myQueue.push(11);
		myQueue.push(0);
		myQueue.push(7);
		myQueue.push(36);
		myQueue.push(47);
		myQueue.pop();
		myQueue.print();
	}

}
