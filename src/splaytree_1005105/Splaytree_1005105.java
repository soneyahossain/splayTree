
package splaytree_1005105;




public class Splaytree_1005105 {
    
    private BST_NODE root;
    private int  size;  
    
    
       public Splaytree_1005105(){
		root = null;
		size = 0;
	}
    
    
      public BST_NODE root() { 
		return root;
	} 
    
    
        int size(){ 
		return size; 
	}
    
    
       void Do_Empty(){
		root = null;
		size = 0;
	}
     
     
     public void insert(int x){
		
	     BST_NODE newNode = new BST_NODE();
	     newNode.Node=x;
             BST_NODE root1= new BST_NODE();
             BST_NODE newNode1 = new BST_NODE();
	    
	     if( root == null ){
	          root = newNode; size++; 
	     } 
             
             
             else {
                 
                 root1=root;
	    	 
	    	 while (true){	    		
	    		
                     int key=root1.Node;
	    		 if( key<x){
                             
                             if(root1._right!=null)
                             { 
                                 newNode1=root1._right;
                                 root1=newNode1;
                      
                             }
                             else
                             {
                                 
                                 root1._right=newNode;
                                 size++;
                                 root=splay(x,root);
	    			 return;
                             }
	    		 }
	    		 else
                         {	              
	    	             if(root1._left!=null)
                             { 
                                 newNode1=root1._left;
                                 root1=newNode1;
                      
                             }
                             else
                             {
                                 
                                 root1._left=newNode;
                                 size++;
                                 root=splay(x,root);
	    			 return;
                             }
	    		 }
	    		        
	    	 }
                 
                 
	     }
           
             
	}
     
  //.........................delete.............................
     
     public int find_max(BST_NODE t){
         
         int max=t.Node;
         while(t._right!=null)
         {
             max=t._right.Node;
             t=t._right;
         } 
         return max;
     }  
     public boolean find(Comparable keys){
	      if (root==null) return false;
	      root = splay(keys, root);
	      if(root.Node!=keys){ return false; }
	      else return true;
	}
     
   public BST_NODE delete(int keys){
		
	    BST_NODE _newTree;

	    root = splay( keys, root );
            
             System.out.println("");
             System.out.println("");
             System.out.println("");
             printBinaryTree(root,height());
	    
             System.out.println("");
             System.out.println("");
             System.out.println("");
	    if(root.Node!=keys ){ 
                
                System.out.println("key not found");
	    	return null;
	    } 
	 
	    BST_NODE result = root;

	    if( root._left == null )
            {
	         _newTree = root._right;
                
            }
	    else{
	       
	        _newTree = root._left;
                int max=find_max(_newTree);
	        _newTree = splay(max, _newTree );
	        _newTree._right = root._right;
	    }

	    root = _newTree;
	    size--; 
	    return result;
	}  
     
     
     
 //....................................................
     
   private static BST_NODE header = new BST_NODE(); 
   
   private BST_NODE splay(Comparable keys, BST_NODE t){
		
       
            BST_NODE _leftTreeMax, _rightTreeMin;
	    header._left = header._right = null;
	    _leftTreeMax = _rightTreeMin = header;

	    for( ; ; ){
	        Comparable rKey = t.Node;
	        if( keys.compareTo(rKey) < 0 ){
                    
                    
	            if(t._left == null) break;
	            if( keys.compareTo(t._left.Node) < 0 ) t = rotateWithLeftChild(t);
	            if( t._left == null ) break;
	               
	            // Link Right
	            _rightTreeMin._left = t;
	            _rightTreeMin = t;
	            t = t._left;
	        }
	        else if( keys.compareTo(rKey) > 0){
	            
                    if( t._right == null ) break;
	            if( keys.compareTo(t._right.Node) > 0) t = rotateWithRightChild(t);
	            if( t._right == null ) break;    
	 
	            // Link Left
	            _leftTreeMax._right = t;
	            _leftTreeMax = t;
	            t = t._right;
	        } else break;
	   }

	   _leftTreeMax._right = t._left;
	   _rightTreeMin._left = t._right;
	   t._left = header._right;
	   t._right = header._left;
	   return t;

  }
   
   
 //...........................................................................  
        private BST_NODE rotateWithLeftChild(BST_NODE k2){
	    BST_NODE k1 = k2._left;
	    k2._left = k1._right;
	    k1._right = k2;
	    return k1;
	}

	private BST_NODE rotateWithRightChild(BST_NODE k1){
	    BST_NODE k2 = k1._right;
	    k1._right = k2._left;
	    k2._left = k1;
	    return k2;
	}
	
   
//........................................................................
     
   
   
   
       
	
   
   
    public static void main(String[] args) {
        
        Splaytree_1005105 instance=new Splaytree_1005105();
       
        instance.insert(1);
        instance.insert(910);
        instance.insert(10);
        instance.insert(0);
        instance.insert(-10);
        instance.insert(45);
        instance.insert(20);
        instance.insert(1);
        printBinaryTree(instance.root,instance.height());
        instance.delete(-10);
        printBinaryTree(instance.root,instance.height());
        //instance.splay(10,instance.root);
        
       //System.out.println(instance.root.Node);
       //System.out.println(instance.root._right.Node);
       //System.out.println(instance.root._left.Node);
       //System.out.println(instance.size);
       printBinaryTree(instance.splay(10,instance.root),instance.height());
        
    }
    
    
      public static void printBinaryTree(BST_NODE root, int level){
    if(root==null)
         return;
    printBinaryTree(root._right, level+1);
    if(level!=0){
        for(int i=0;i<level-1;i++) {
            System.out.print("|\t");
        }
            System.out.println("|--- "+root.Node);
    }
    else
        System.out.println(root.Node);
    printBinaryTree(root._left, level+1);
}    
 
  public int height()
  { 
		return height(root);
 }  
	  

	public int height(BST_NODE t){
		if (t == null) return 0;
		int left_height = height(t._left);
		int right_height = height(t._right);
		   
		return (left_height>right_height)?(++left_height):(++right_height);   
	}   
    
    
}



class BST_NODE
{
    
     int  Node ;
     BST_NODE  _left     = null;
     BST_NODE  _right    = null;
   
     public BST_NODE(){
        
     }
    
    
}