// Time Complexity :  O(n)
// Space Complexity :  O(n+h)
// Did this code successfully run on Leetcode :  Yes

class Solution {

    //keep track of the root from postOrder[]
    private int postIndex;
    //map to store indices
    private HashMap<Integer,Integer> map= new HashMap<>();

    private void buildMap(int[] inorder){
        for(int i=0; i<inorder.length; i++){
            map.put(inorder[i],i);
        }
    }

    // build the tree using postIndex with postOrder and (start & end) with inOrder
    private TreeNode buildTreeRec(int[] postorder, int[] inorder, int start, int end){
        if(end<start){
            return null;
        }
        int val= postorder[postIndex];
        //get the index from inOrder
        int index= getIndex(val);
        //increment postIndex so that we can move to next node in postOrder
        postIndex--;
        TreeNode root= new TreeNode(val);
        //build the tree using left subset and right subset of the root in inOrder
        root.right= buildTreeRec(postorder,inorder,index+1,end);
        root.left= buildTreeRec(postorder,inorder,start,index-1);
        return root;
    }

    private int getIndex(int val){
        return map.get(val);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex=postorder.length-1;
        buildMap(inorder);
        return buildTreeRec(postorder,inorder,0,inorder.length-1);
    }
}