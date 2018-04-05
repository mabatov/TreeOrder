package Other;

import POJO.Relations;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BinaryTreePostOrder {

    public static class TreeNode
    {
        long id;
        public ArrayList<TreeNode> childs = new ArrayList<>();

        TreeNode(long id)
        {
            this.id=id;
        }

        public TreeNode(ArrayList<TreeNode> child) {
            this.childs = child;
        }
    }

    public static TreeNode createBinaryTree()
    {
        TreeNode rootNode=new TreeNode(0);

        /*В куске ниже приводится
         * создание,
         * инициализация и
         * установление взаимоотношений
         * между узлами дерева*/
        Gson gson = new Gson();
        BufferedReader br_relations = null;
        try {
            br_relations = new BufferedReader(new FileReader("files/relations.json"));
            long rootParent = 7103;
            long firstLevel = 1;
            Relations[] relations = gson.fromJson(br_relations, Relations[].class);
            for (long l = 1; l<=10; l++) {
                for (Relations r : relations) {
                    if (r.getParent().equals(rootParent) && r.getLevel().equals(l)) {
                        if (rootNode.childs.size() >= 2
                                && r.getParent().equals(rootNode.childs.get(rootNode.childs.size()-2))
                                && r.getLevel().equals(firstLevel)){
                            rootNode.childs.add(new TreeNode(r.getNode()));
                        } else {
                            rootNode.childs.add(new TreeNode(r.getNode()));
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (br_relations != null) {
                try {
                    br_relations.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return rootNode;
    }


    public void postOrder(TreeNode root) {
        if(root !=  null) {
            for(int i = 0; i < root.childs.size(); i++)
                postOrder(root.childs.get(i));

            System.out.printf("%d \n", root.id);
        }
    }

    public static void main(String[] args)
    {
        BinaryTreePostOrder bi = new BinaryTreePostOrder();

        TreeNode rootNode = createBinaryTree();
        System.out.println("Using Recursive solution:");

        bi.postOrder(rootNode);

    }
}