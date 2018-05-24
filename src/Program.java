
public class Program {

	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.insert(new StudentData(50, ""));
		// tree.printTreeForDebug();
		System.out.println("--");
		tree.insert(new StudentData(55, ""));
		// tree.printTreeForDebug();
		System.out.println("--");
		tree.insert(new StudentData(53, ""));
		// tree.printTreeForDebug();
		System.out.println("--");
		tree.insert(new StudentData(54, ""));
		tree.insert(new StudentData(57, ""));
		tree.insert(new StudentData(30, ""));
		tree.insert(new StudentData(20, ""));
		tree.insert(new StudentData(10, ""));
		tree.printTreeForDebug();
		// System.out.println(tree.search(54));
		// System.out.println(tree.search(56));
		System.out.println("***in order**");
		tree.printInOrder();
		System.out.println("***pre order**");
		tree.printPreOrder();
		System.out.println("***post order**");
		tree.printPostOrder();
	}

}
