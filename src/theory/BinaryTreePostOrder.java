package theory;

public class BinaryTreePostOrder {

    public static class TreeNode
    {
        int data;
        String email;
        TreeNode left;
        TreeNode right;

        TreeNode(String email, int data)
        {
            this.data=data;
            this.email = email;
        }
    }

    public static TreeNode createBinaryTree()
    {
        TreeNode rootNode=new TreeNode("nroot@mail.ru", 40);
        TreeNode node20=new TreeNode("n20@mail.ru", 20);
        TreeNode node10=new TreeNode("n3@mail.ru", 10);
        TreeNode node30=new TreeNode("n4@mail.ru", 30);
        TreeNode node60=new TreeNode("n5@mail.ru", 60);
        TreeNode node50=new TreeNode("n6@mail.ru", 50);
        TreeNode node70=new TreeNode("n7@mail.ru", 70);

        rootNode.left=node20;
        rootNode.right=node60;

        node20.left=node10;
        node20.right=node30;

        node60.left=node50;
        node60.right=node70;

        return rootNode;
    }


    public void postOrder(TreeNode root) {
        if(root !=  null) {
            postOrder(root.left);
            postOrder(root.right);

            System.out.printf("%d %s \n", root.data, root.email);
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