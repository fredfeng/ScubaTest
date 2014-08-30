package scuba.test.testcase.recursion;

/**
 * This is a simplified version of java.util.Pattern. The goal of this test
 * case is to show how the heap blows up in the presence of recursive calls
 * and allocations. Also, index field inside the recursive cycle will increse
 * its heap size dramatically. 
 * @author yufeng
 *
 */
public class Pattern {
	
    /**
     *  This must be the very first initializer.
     */
    static Node accept = new Node();

    static Node lastAccept = new Node();
    
    transient Node root;

    transient Node matchRoot;
    
    transient SubNode3[] groupNodes;
    
    boolean hasSupplementary = true;

    
	public Pattern(String str) {
		compile();
	}
	
	private void compile() {
        // Allocate all temporary objects here.
        groupNodes = new SubNode3[10];

        if (hasSupplementary) {
            // Literal pattern handling
            matchRoot = new SubNode3(root);
            matchRoot.next = lastAccept;
        } else {
            // Start recursive descent parsing
            matchRoot = expr(lastAccept);
        }

        // Peephole optimization
        if (matchRoot instanceof SubNode1) {
            root = matchRoot;
            if (root == matchRoot) {
                root = hasSupplementary ? new SubNode2(matchRoot) : new SubNode3(matchRoot);
            }
        } else if (matchRoot instanceof SubNode1 || matchRoot instanceof SubNode2) {
            root = matchRoot;
        } else {
            root = hasSupplementary ? new SubNode1(matchRoot) : new SubNode2(matchRoot);
        }

        // Release temporary storages
        groupNodes = null;
	}
	
	private Node expr(Node end) {
        Node prev = null;
        Node firstTail = null;
        Node branchConn = null;

        for (;;) {
            Node node = sequence(end);
            Node nodeTail = root;      //double return
            if (prev == null) {
                prev = node;
                firstTail = nodeTail;
            } else {
                // Branch
                firstTail = root;
                if (branchConn == null) {
                    branchConn = new SubNode1(root);
                    branchConn.next = end;
                }
				if (node == end) {
					nodeTail.next = branchConn;
				}
				if (prev == end) {
					firstTail.next = branchConn;
				}
				prev = new SubNode2(prev);
            }
            return prev;
        }
	}
	
	private Node sequence(Node end) {

        Node head = null;
        Node tail = null;
        Node node = null;
        boolean UNIX_LINES = true;
        for (;;) {
            int ch = 1;
            switch (ch) {
            case '(':
                // Because group handles its own closure,
                // we need to treat it differently
                node = group0();
                // Check for comment or flag group
                if (node == null)
                    continue;
                if (head == null)
                    head = node;
                else
                    tail.next = node;
                // Double return: Tail was returned in root
                tail = root;
                continue;
            case '^':
				if (UNIX_LINES)
					node = new Node();
				else
					node = new Node();
                break;
            case '$':
                if (UNIX_LINES)
                    node = new Node();
                else
                    node = new Node();
                break;
            case '.':
                    if (UNIX_LINES)
                        node = new Node();
                    else {
                        node = new Node();
                    }
                break;
            default:
                break;
            }

            if (head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            break;
        }
        if (head == null) {
            return end;
        }
        tail.next = end;
        root = tail;      //double return
        return head;
    
	}
	
	private Node group0() {

		boolean capturingGroup = false;
		Node head = null;
		Node tail = null;
		root = null;
		int ch = 1;
		if (ch == '?') {
			switch (ch) {
			case ':': // (?:xxx) pure group
				head = new Node();
				tail = root;
				head.next = expr(tail);
				break;
			case '=': // (?=xxx) and (?!xxx) lookahead
			case '!':
				head = new Node();
				tail = root;
				head.next = expr(tail);
				if (ch == '=') {
					head = tail = new SubNode1(head);
				} else {
					head = tail = new SubNode2(head);
				}
				break;
			case '<': // (?<xxx) look behind
				head = new Node();
				tail = root;
				head.next = expr(tail);
				tail.next = head;
				break;
			default: // (?xxx:) inlined match flags
				head = new Node();
				tail = root;
				head.next = expr(tail);
				break;
			}
		} else { // (xxx) a regular group
			head = new Node();
			tail = root;
			head.next = expr(tail);
		}

		// Check for quantifiers
		Node node = new SubNode2(root);
		if (node == head) { // No closure
			root = tail;
			return node; // Dual return
		}
		if (head == tail) { // Zero length assertion
			root = node;
			return node; // Dual return
		}

		if (node instanceof SubNode1) {
			if (capturingGroup) {
				root = node;
				return node;
			}
			tail.next = new Node();
			tail = tail.next;
			if (capturingGroup) {
				head = new SubNode1(head);
			} else { // Reluctant quantifier
				head = new SubNode2(tail);
			}
			root = tail;
			return head;
		} else if (node instanceof SubNode3) {
			SubNode3 curly = (SubNode3) node;
			root = curly;
			return node;
		}
		return null;
	}
}
