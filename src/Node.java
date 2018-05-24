
public class Node {
	private Node right;
	private Node left;
	private Node parent;
	private Color color;
	private StudentData studentData;

	public Node(StudentData studentData, Node right, Node left, Color color) {
		super();
		this.studentData = studentData;
		this.right = right;
		this.left = left;
		this.color = color;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Color getColor() {
		return color;
	}

	public long getKey() {
		return studentData.getId();
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public StudentData getStudentData() {
		return studentData;
	}

	public void setStudentData(StudentData studentData) {
		this.studentData = studentData;
	}

}
