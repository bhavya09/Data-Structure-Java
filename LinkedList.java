package LinkedList;

import java.util.*;

public class LinkedList {

	private class Node {
		int data;
		Node next;
	}

	private Node head;
	private Node tail;
	int size;

	public void display() {
		System.out.println("------------------------------------");
		Node temp = this.head;
		while (temp != null) {
			System.out.print(temp.data + ", ");
			temp = temp.next;
		}
		System.out.println();
		System.out.println("------------------------------------");
	}

	public void addLast(int item) {

		Node nn = new Node();
		nn.data = item;
		nn.next = null;

		if (this.size >= 1) {
			this.tail.next = nn;
		}

		if (this.size == 0) {
			this.head = nn;
			this.tail = nn;
			this.size++;
		} else {
			this.tail = nn;
			this.size++;
		}
	}

	public void addFirst(int item) {
		Node nn = new Node();
		nn.data = item;
		nn.next = null;

		if (this.size >= 1) {
			nn.next = this.head;
		}
		if (this.size == 0) {
			this.head = nn;
			this.tail = nn;
			this.size++;
		} else {
			this.head = nn;
			this.size++;
		}
	}

	public int getFirst() throws Exception {
		if (this.size == 0) {
			throw new Exception("LL is Empty.");
		}
		return this.head.data;
	}

	public int getLast() throws Exception {
		if (this.size == 0) {
			throw new Exception("LL is Empty.");
		}
		return this.tail.data;
	}

	public int getAt(int idx) throws Exception {
		if (this.size == 0) {
			throw new Exception("LL is Empty");
		}
		if (idx < 0 || idx >= this.size) {
			throw new Exception("Invalid Index");
		}
		Node temp = this.head;
		for (int i = 1; i <= idx; i++) {
			temp = temp.next;
		}
		return temp.data;

	}

	private Node getNodeAt(int idx) throws Exception {
		if (this.size == 0) {
			throw new Exception("LL is Empty");
		}
		if (idx < 0 || idx >= this.size) {
			throw new Exception("Invalid Index");
		}
		Node temp = this.head;
		for (int i = 1; i <= idx; i++) {
			temp = temp.next;
		}
		return temp;
	}

	public void addAt(int idx, int item) throws Exception {

		if (idx < 0 || idx > this.size) {
			throw new Exception("Invalid Index");
		}
		if (idx == 0) {
			addFirst(item);
		} else if (idx == this.size) {
			addLast(item);
		} else {
			Node nn = new Node();
			nn.data = item;
			nn.next = null;
			Node nm1 = getNodeAt(idx - 1);
			// System.out.println(nm1.data);
			Node np1 = nm1.next;
			nm1.next = nn;
			nn.next = np1;
			this.size++;
		}
	}

	public int removeFirst() throws Exception {
		if (this.size == 0) {
			throw new Exception("LL is Empty");
		}
		int rv = head.data;
		if (this.size == 1) {
			this.head = null;
			this.tail = null;
			this.size = 0;
		} else {
			this.head = this.head.next;
			this.size--;
		}
		return rv;
	}

	public int removeLast() throws Exception {
		if (this.size == 0) {
			throw new Exception("LL is Empty");
		}
		int rv = tail.data;

		if (this.size == 1) {
			this.head = null;
			this.tail = null;
			this.size = 0;
		} else {
			Node lm = this.getNodeAt(this.size - 2);
			this.tail = lm;
			this.tail.next = null;
			this.size--;
		}

		return rv;
	}

	public int removeAt(int idx) throws Exception {
		if (this.size == 0) {
			throw new Exception("LL is Empty.");
		}
		if (idx < 0 || idx >= this.size) {
			throw new Exception("Invalid Index");
		}
		if (idx == 0) {
			return removeFirst();
		} else if (idx == this.size) {
			return removeLast();
		} else {
			Node nm1 = getNodeAt(idx - 1);
			Node n = nm1.next;
			Node np1 = n.next;
			nm1.next = np1;
			this.size--;
			return n.data;
		}
	}

	public void reverseData() throws Exception {
		int left = 0;
		int right = this.size - 1;

		while (left < right) {
			Node le = getNodeAt(left);
			Node ri = getNodeAt(right);

			int temp = le.data;
			le.data = ri.data;
			ri.data = temp;
			left++;
			right--;

		}
	}

	public void reversePointers() {
		Node prev = this.head;
		Node curr = prev.next;

		while (curr != null) {
			Node ahead = curr.next;
			curr.next = prev;
			prev = curr;
			curr = ahead;
		}

		Node t = this.head;
		this.head = this.tail;
		this.tail = t;
		this.tail.next = null;
	}

	public boolean detectLoop() {
		Node p = this.head;
		Node q = this.head;

		while (p != null && q != null && q.next != null) {
			p = p.next;
			q = q.next.next;
			if (p == q) {
				removecycle(p, head);
				return true;
			}
		}
		return false;
	}

	public void removecycle(Node prev, Node curr) {
		Node ptr1 = null;
		Node ptr2 = curr;

		while (true) {
			ptr1 = prev;
			while (ptr1.next != prev && ptr1.next != ptr2) {
				ptr1 = ptr1.next;
			}
			if (ptr1.next == ptr2) {
				break;
			}
			ptr2 = ptr2.next;
		}
		ptr1.next = null;
	}

	public void midNode() {
		Node a = head;
		Node b = head;
		while (b.next != null && b.next.next != null) {
			a = a.next;
			b = b.next.next;
		}
		System.out.println(a.data);

	}

	public boolean palindrome() {
		Stack<Integer> s = new Stack();

		Node curr = head;
		while (curr != null) {
			s.push(curr.data);
			curr = curr.next;
		}
		curr = head;
		while (curr != null) {
			if (s.pop() == curr.data) {
				curr = curr.next;
			} else {
				return false;
			}
		}
		return true;
	}

	public void removeDuplicates() {
		Node curr = head;
		while (curr.next != null) {
			if (curr.data == curr.next.data) {
				curr.next = curr.next.next;
			} else {
				curr = curr.next;
			}
		}
	}

	public void deleteDuplicates() {
		Node dum = new Node();
		dum.data = 0;

		dum.next = head;
		Node prev = dum;
		Node curr = head;
		while (curr != null) {
			while (curr.next != null && prev.next.data == curr.next.data) {
				curr = curr.next;
			}

			if (prev.next == curr) {
				prev = prev.next;
			} else {
				prev.next = curr.next;
			}
			curr = curr.next;

		}

		head = dum.next;

	}

	public void removeDuplicatesFromUnsorted() {

		Node ptr1 = head;
		Node ptr2 = null;

		while (ptr1 != null && ptr1.next != null) {
			ptr2 = ptr1;

			while (ptr2.next != null) {
				if (ptr1.data == ptr2.next.data) {
					ptr2.next = ptr2.next.next;
				} else {
					ptr2 = ptr2.next;
				}
			}
			ptr1 = ptr1.next;
		}
	}

	public void removeDuplicatesFromUnsorted2() {
		HashSet<Integer> map = new HashSet<>();
		Node curr = head;
		Node prev = null;

		while (curr != null) {

			int vl = curr.data;
			if (map.contains(vl)) {
				prev.next = curr.next;
			} else {
				map.add(vl);
				prev = curr;
			}

			curr = curr.next;
		}

	}

	public void evenOdd() {

		Node evenstart = null;
		Node evenend = null;
		Node oddstart = null;
		Node oddend = null;
		Node curr = head;

		while (curr != null) {
			int element = curr.data;

			if (element % 2 == 0) {
				if (evenstart == null) {
					evenstart = curr;
					evenend = evenstart;
				} else {
					evenend.next = curr;
					evenend = evenend.next;
				}
			} else {
				if (oddstart == null) {
					oddstart = curr;
					oddend = oddstart;
				} else {
					oddend.next = curr;
					oddend = oddend.next;
				}
			}

			curr = curr.next;
		}

		head = evenstart;
		oddend.next = null;
		evenend.next = oddstart;
	}

	public void deleteMid() {
		Node fast = head;
		Node slow = head;
		Node dum = new Node();
		dum.next = head;
		Node prev = dum;
		Node curr = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast = fast.next.next;
			prev = prev.next;
		}

		prev.next = slow.next;
		head = dum.next;

	}

	public void partition(int k) {

		Node sh = null, sl = null, eh = null, el = null, gh = null, gl = null;

		Node curr = head;

		while (curr != null) {
			if (curr.data < k) {
				if (sh == null) {
					sh = curr;
					sl = sh;
				} else {
					sl.next = curr;
					sl = sl.next;
				}
			} else if (curr.data == k) {
				if (eh == null) {
					eh = curr;
					el = eh;
				} else {
					el.next = curr;
					el = el.next;
				}
			} else if (curr.data > k) {
				if (gh == null) {
					gh = curr;
					gl = gh;
				} else {
					gl.next = curr;
					gl = gl.next;
				}
			}
			curr = curr.next;
		}
		//System.out.println(gh.data);
		if(gh!=null)
		{
			gl.next=null;
		}
		else 
		{
			if(el!=null)
			{
				el.next=null;
			}
			else
			{
				sl.next=null;
			}
		}
		if(sh==null)
		{
			if(eh==null)
			{
				if(gh==null)
				{
					return;
				}
				head=gh;
				return ;
			}
			head=eh;
			el.next=gh;
			return;
		}
		head=sh;
    
		if(eh!=null)
		{
      		sl.next=eh;
			if(gh!=null)
			{
				el.next=gh;
			}
		}
		else
		{
			if(gh!=null)
			{
				sl.next=gh;
			}
		}
		
		
	}

	
}
