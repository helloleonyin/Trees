package trees;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class TreeList{
	ArrayList<Tree> list;
	public TreeList() {
		this.list = new ArrayList<Tree>();
	}
	public void add1(Tree tree1){
		this.list.add(tree1);
	}
}