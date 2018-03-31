package sjsu.patel.cs146.project3;



import static org.junit.Assert.*;

import org.junit.Test;


public class RBTTester {
	
	@Test
	public void test() {
		
		RedBlackTree<String> rbt = new RedBlackTree<String>();

		rbt.insert("D");
        rbt.insert("B");
        rbt.insert("A");
        rbt.insert("C");
        rbt.insert("F");
        rbt.insert("E");
        rbt.insert("H");
        rbt.insert("G");
        rbt.insert("I");
        rbt.insert("J");
       assertEquals("DBACFEHGIJ", makeString(rbt));
        String str=     
"Color: 1, Key:D Parent: \n"+
"Color: 1, Key:B Parent: D\n"+
"Color: 1, Key:A Parent: B\n"+
"Color: 1, Key:C Parent: B\n"+
"Color: 1, Key:F Parent: D\n"+
"Color: 1, Key:E Parent: F\n"+
"Color: 0, Key:H Parent: F\n"+
"Color: 1, Key:G Parent: H\n"+
"Color: 1, Key:I Parent: H\n"+
"Color: 0, Key:J Parent: I\n";
		assertEquals(str, makeStringDetails(rbt));
	}
		public static String makeString(RedBlackTree<String> t)
	    {
	       class MyVisitor implements RedBlackTree.Visitor<String> {
	          String result = "";
	          public void visit(RedBlackTree.Node<String> n)
	          {
	             result = result + n.key;
	          }
	       };
	       MyVisitor v = new MyVisitor();
	       t.preOrderVisit(v);
	       return v.result;
	    }

	    public static String makeStringDetails(RedBlackTree<String> t) {
	    	{
	    	       class MyVisitor implements RedBlackTree.Visitor<String> {
	    	          String result = "";
	    	          public void visit(RedBlackTree.Node<String> n)
	    	          {
	    	        	      if((n.key).equals(""))
	    	        		  result = result +"Color: "+n.color+", Key:"+n.key+" Parent: "+n.parent.key+"\n";
	    	             
	    	          }
	    	       };
	    	       MyVisitor v = new MyVisitor();
	    	       t.preOrderVisit(v);
	    	       return v.result;
	    	 }
	    }
	}
   
	  