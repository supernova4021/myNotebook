package interview.linklist;

public class LinkedListDeletor {
	public static Node deleteIfEquals(Node head, Integer value){
		if(head == null){
			return null;
		}
		
		while(head != null && head.getValue().equals(value)){
			head = head.getNext();
		}
		
		Node prev = head;
		// loop invariant:
		// prev: list node from head up to prev has been processed
		while(prev.getNext() != null){
			if(prev.getNext().getValue().equals(value)){
				prev.setNext(prev.getNext().getNext());
			}else{
				prev = prev.getNext();
			}
		}
		return head;
	}
}
