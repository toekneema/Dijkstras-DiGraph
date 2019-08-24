package DiGraph_A5;

public class MinBinHeap implements Heap_Interface {
  private EntryPair[] array; //load this array
  private int size;
  private static final int arraySize = 1000000; //Everything in the array will initially 
                                              //be null. This is ok! Just build out 
                                              //from array[1]

  public MinBinHeap() {
    this.array = new EntryPair[arraySize];
    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
                                             //of child/parent computations...
                                             //the book/animation page both do this.
  }
    
  //Please do not remove or modify this method! Used to test your entire Heap.
  @Override
  public EntryPair[] getHeap() { 
    return this.array;
  }

@Override
public void insert(EntryPair entry) {
	    size++;
        array[size] = entry;
        bubbleUp(size);
}

@Override
public void delMin() {

	array[1] = array[size];
	size--;
    
	if (size > 0) {
        	bubbleDown(1);
    }
 }

@Override
public EntryPair getMin() {
	if (size <= 0 ) {
		return null;
	}
	return array[1];
}

@Override
public int size() {
	return size;
}

@Override
public void build(EntryPair[] entries) {
	for(int i = 1; i < entries.length+1; i++) {
		array[i] = entries[i-1];
		size++;
	}
	for(int i = size; i > 0; i--) {
		bubbleDown(i);
	}
	
}

private void bubbleUp(int nodeIndex) {

    int parentIndex;
    EntryPair tmp = new EntryPair(null, -1);
    
    if (nodeIndex != 1) {
          parentIndex = Math.floorDiv(nodeIndex, 2);
          //might need an extra line to get the string value over to the new array index
          if (array[parentIndex].getPriority() > array[nodeIndex].getPriority()) {
                tmp = array[parentIndex];
                array[parentIndex] = array[nodeIndex];
                array[nodeIndex] = tmp;
                bubbleUp(parentIndex);
          }
    }
}

private void bubbleDown(int nodeIndex) {

    int leftChildIndex, rightChildIndex, minIndex;
    EntryPair tmp = new EntryPair(null, -1);
    leftChildIndex = nodeIndex*2;
    rightChildIndex = (nodeIndex*2)+1;
    
    if (rightChildIndex > size) {
          if (leftChildIndex > size) {
        	  return;
          } else {
                minIndex = leftChildIndex;
          }
    } else {
          if (array[leftChildIndex].getPriority() <= array[rightChildIndex].getPriority()) {
        	  minIndex = leftChildIndex;
          } else {
                minIndex = rightChildIndex;
          }
    }
    if (array[nodeIndex].getPriority() > array[minIndex].getPriority()) { //not correctly swapping the array[nodeIndex] with array[minIndex] 
          tmp = array[minIndex];
          array[minIndex] = array[nodeIndex];
          array[nodeIndex] = tmp;
          bubbleDown(minIndex);
    }
}

}