import java.util.Scanner;


class TreeNode{
    int val;
    TreeNode left, right  = null;
    TreeNode parent;

    public TreeNode(int val) {
        this.val = val;
    }
}

class Solution {
    public static TreeNode kthSmallest(TreeNode root, int k) {
        TreeNode temp = root;
        for (int i = 0; i < k - 1; i++){
            while(temp.left != null){
                if (temp.left.left == null) break;
                temp = temp.left;
            }
            if (temp.left != null){
                if (temp.left.right == null) temp.left = null;
                else temp.left = temp.left.right;}

            else {
                root = temp.right;
            }

            temp = root;
        }

        while (root.left != null){
            root = root.left;
        }

        return root;
    }
}

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        TreeNode root = null;
        
        String inputArbol = sc.nextLine(); 
        Scanner sc2 = new Scanner(inputArbol);
        int[] ints = new int[inputArbol.length()];

        int c = 0;
        while (sc2.hasNext()){
            ints[c] = sc2.nextInt();
            c++;
        }
        sc2.close();

        

        for (int i = 0; i < c; i++){
                int val = ints[i];
                
                TreeNode node = new TreeNode(val);
                if (root == null){
                    node.parent = new TreeNode(-1);
                    root = node;
                    continue;
                }

                TreeNode temp = root;

                while (temp != null){
                    if (val > temp.val){
                        if (temp.right == null){
                            node.parent = temp;
                            temp.right = node;
                            break;
                        }
                        else{
                            temp = temp.right;
                        }
                    }

                    else {
                        if (temp.left == null){
                            node.parent = temp;
                            temp.left = node;
                            break;
                        }
                        else{
                            temp = temp.left;
                        }
                    }
                }

            }

        int k = sc.nextInt();
        TreeNode solucion = Solution.kthSmallest(root, k);
        System.out.print(solucion.val + " " + solucion.parent.val + "\n");
        sc.close();
    }
}