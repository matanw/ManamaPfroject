public class Tree {
	private Node root;

	public Tree() {
		root = null;
	}

	public void insert(StudentData studentData)// o(h)
	{
		if (root == null) {
			root = new Node(studentData, null, null, Color.BLACK);
			return;
		}
		Node newNode = new Node(studentData, null, null, Color.RED);
		Node current = root;
		while (true) {
			SonType sonType = (newNode.getKey() <= current.getKey() ? SonType.LEFT : SonType.RIGHT);
			if (hasRealSon(current, sonType)) {
				current = getSon(current, sonType);
			} else {
				setSon(current, newNode, sonType);
				newNode.setRight(getSuccessorOfLeaf(newNode));
				newNode.setLeft(getPredecessorOfLeaf(newNode));
				return;
			}
		}
	}

	public void remove(long studentId)// o(h)
	{
		Node originalNode = innerSearch(studentId);
		if (originalNode == null) {
			throw new IllegalArgumentException("number is not exists in tree");
		}
		Node nodeToDelete;
		if (!hasRealSon(originalNode, SonType.RIGHT) || !hasRealSon(originalNode, SonType.LEFT)) {
			nodeToDelete = originalNode;
		} else {
			nodeToDelete = getSuccessor(originalNode);
		}
		Node prev = getPredecessor(nodeToDelete);
		Node next = getSuccessor(nodeToDelete);
		Node son = null;
		if (hasRealSon(nodeToDelete, SonType.RIGHT)) {
			son = nodeToDelete.getRight();
		} else if (hasRealSon(nodeToDelete, SonType.LEFT)) {
			son = nodeToDelete.getLeft();
		}
		if (son != null) {
			son.setParent(nodeToDelete.getParent());
		}
		if (nodeToDelete.getParent() == null) {
			root = son;

		} else if (nodeToDelete.getParent().getLeft() == nodeToDelete) {
			nodeToDelete.getParent().setLeft(son);
		} else if (nodeToDelete.getParent().getRight() == nodeToDelete) {
			nodeToDelete.getParent().setRight(son);
		}
		if (prev != null && !hasRealSon(prev, SonType.LEFT)) {
			prev.setLeft(getSuccessor(prev));
		}
		if (next != null && !hasRealSon(next, SonType.RIGHT)) {
			next.setLeft(getPredecessor(next));
		}
		originalNode.setStudentData(nodeToDelete.getStudentData());
	}

	public StudentData search(long studentId)// o(h)
	{
		Node node = innerSearch(studentId);
		return (node == null ? null : node.getStudentData());
	}

	private Node innerSearch(long studentId) {
		if (root == null) {
			return null;
		}
		Node current = root;
		while (true) {
			if (current.getKey() == studentId) {
				return current;
			}
			SonType sonType = (studentId < current.getKey() ? SonType.LEFT : SonType.RIGHT);
			if (hasRealSon(current, sonType)) {
				current = getSon(current, sonType);
			} else {
				return null;
			}
		}
	}

	public StudentData getSuccessor(long studentId)// o(h)
	{
		Node node = innerSearch(studentId);
		if (node == null) {
			return null;
		}
		return getStudentDataSafety(getSuccessor(node));
	}

	private Node getSuccessor(Node node) {
		if (hasRealSon(node, SonType.RIGHT)) {
			return goInDirection(node.getRight(), SonType.LEFT);
		} else {
			return node.getRight();
		}
	}

	private StudentData getStudentDataSafety(Node node) {
		return (node == null ? null : node.getStudentData());
	}

	private Node getSuccessorOfLeaf(Node node)// o(h)
	{
		Node current = node;
		while (current.getParent() != null && current.getParent().getRight() == current) {
			current = current.getParent();
		}
		return current.getParent();
	}

	private Node getPredecessorOfLeaf(Node node)// o(h)
	{
		Node current = node;
		while (current.getParent() != null && current.getParent().getLeft() == current) {
			current = current.getParent();
		}
		return current.getParent();
	}

	public StudentData getPredecessor(long studentId)// o(h)
	{
		Node node = innerSearch(studentId);
		if (node == null) {
			return null;
		}
		return getStudentDataSafety(getPredecessor(node));
	}

	public Node getPredecessor(Node node) {
		if (hasRealSon(node, SonType.LEFT)) {
			return goInDirection(node.getLeft(), SonType.RIGHT);
		} else {
			return node.getLeft();
		}
	}

	public StudentData getMinimum()// o(h)
	{
		if (root == null) {
			return null;
		}
		return goInDirection(root, SonType.LEFT).getStudentData();
	}

	public StudentData getMaximum()// o(h)
	{
		if (root == null) {
			return null;
		}
		return goInDirection(root, SonType.RIGHT).getStudentData();
	}

	private Node goInDirection(Node strat, SonType sonType) {
		Node node = strat;
		while (hasRealSon(node, sonType)) {
			node = getSon(node, sonType);
		}
		return node;
	}

	public void printInOrder()// o(n)
	{
		if (root != null) {
			printInOrder(root);
		}
	}

	private void printInOrder(Node node) {
		if (hasRealSon(node, SonType.LEFT)) {
			printInOrder(node.getLeft());
		}
		System.out.println(node.getStudentData());
		if (hasRealSon(node, SonType.RIGHT)) {
			printInOrder(node.getRight());
		}
	}

	public void printPreOrder()// o(n)
	{
		if (root != null) {
			printPreOrder(root);
		}
	}

	private void printPreOrder(Node node) {
		System.out.println(node.getStudentData());
		if (hasRealSon(node, SonType.LEFT)) {
			printPreOrder(node.getLeft());
		}
		if (hasRealSon(node, SonType.RIGHT)) {
			printPreOrder(node.getRight());
		}
	}

	public void printPostOrder()// o(n)
	{
		if (root != null) {
			printPostOrder(root);
		}
	}

	private void printPostOrder(Node node) {
		if (hasRealSon(node, SonType.LEFT)) {
			printPostOrder(node.getLeft());
		}
		if (hasRealSon(node, SonType.RIGHT)) {
			printPostOrder(node.getRight());
		}
		System.out.println(node.getStudentData());
	}

	public void printTreeForDebug()// not part of assigment
	{
		System.out.println("**tree**");
		printTreeForDebug(root, "");
	}

	private void printTreeForDebug(Node node, String preperation) {// not part of assigment
		String separator = "   ";
		SonType sonType = SonType.LEFT;
		if (hasRealSon(node, sonType)) {
			printTreeForDebug(getSon(node, sonType), preperation + separator);
		}
		Long next = (getSuccessor(node.getKey()) != null ? getSuccessor(node.getKey()).getId() : null);
		Long prev = (getPredecessor(node.getKey()) != null ? getPredecessor(node.getKey()).getId() : null);
		System.out.println(preperation + node.getKey() + " - " + getSonsDescription(node) + " - next -" + next
				+ " ,prev -" + prev);

		sonType = SonType.RIGHT;
		if (hasRealSon(node, sonType)) {
			printTreeForDebug(getSon(node, sonType), preperation + separator);
		}
	}

	private String getSonsDescription(Node node) {
		if (hasRealSon(node, SonType.LEFT) && hasRealSon(node, SonType.RIGHT)) {
			return "both sons";
		}
		if (hasRealSon(node, SonType.LEFT) && !hasRealSon(node, SonType.RIGHT)) {
			return "only left";
		}
		if (!hasRealSon(node, SonType.LEFT) && hasRealSon(node, SonType.RIGHT)) {
			return "only right";
		}
		return "no sons";

	}
	/*
	 * private void printTreeForDebug(Node node, String preperation) {
	 * System.out.println(preperation + node.getKey() + " - " + node.getColor());
	 * for (SonType sonType : new SonType[] { SonType.LEFT, SonType.RIGHT }) {
	 * if (hasRealSon(node, sonType)) {
	 * printTreeForDebug(getSon(node, sonType), preperation + "-");
	 * } else {
	 * System.out.println(preperation + "-null");
	 * }
	 * }
	 * }
	 */

	private boolean hasRealSon(Node node, SonType sonType) {
		return getSon(node, sonType) != null && getSon(node, sonType).getParent() == node;
	}

	private Node getSon(Node node, SonType sonType) {
		return (sonType == SonType.LEFT ? node.getLeft() : node.getRight());
	}

	private void setSon(Node node, Node son, SonType sonType) {
		if (sonType == SonType.LEFT) {
			node.setLeft(son);
		} else {
			node.setRight(son);
		}
		if (son != null) {
			son.setParent(node);
		}
	}

	private SonType getOpposite(SonType sonType) {
		return sonType == SonType.LEFT ? SonType.RIGHT : SonType.LEFT;
	}
}
