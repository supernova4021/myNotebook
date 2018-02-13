package interview.linklist;

public class LinkedListReverser {

	public static Node reverseLinkedList(Node head){
		Node newHead = null; 
		Node currentHead = head;
		//loop invariant:
		//the head of linkedlist that has been reversed 
		//the head of linkedlist that has not been reversed
		while(currentHead != null){
			// loop invariant holds
			Node currentNextNode = currentHead.getNext();
			currentHead.setNext(newHead);
			newHead = currentHead;
			currentHead = currentNextNode;
			//loop invariant holds
		}
		//loop invariant holds
		return newHead;
	}
	
	
	/**
	 * 
	 * @param head  of the linkedlist to reverse
	 * @return head of the reversed linkedlist
	 */
	public static Node reverseLinkedListByRecursion(Node head){
		
		if(head == null){
			return null;
		}
		
		if(head.getNext() == null){
			return head;
		}
		
		Node headOfReversedSublist = reverseLinkedList(head.getNext());
		head.getNext().setNext(head);
		head.setNext(null);
		return headOfReversedSublist;
	}
	
}
