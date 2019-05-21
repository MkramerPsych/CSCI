/**
 * The driver class for our TreeMethodFrame GUI.  Contains initialization
 * functions and the main method for users to run.
 *
 * @author John Donaldson
 * @author Benjamin Kuperman (Spring 2007)
 * @author Alexa Sharp (Fall 2012)
 * @author John Donaldson (Spring 2018)
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TreeMethodApplication {
    boolean packFrame = false;
    private TreeLoader treeLoader = new TreeLoader();
    private BinaryTree tree = new BinaryTree();

    //Construct the application
    public TreeMethodApplication() {
        final TreeMethodFrame frame = new TreeMethodFrame();
        //Validate frames that have preset sizes
        //Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame) {
            frame.pack();
        }
        else {
            frame.validate();
        }

        //Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);

        // -- Initialize the ActionListener for buttons ---------------------

        // Load File
        frame.addButtonListener("Load File", new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame.fd.setVisible(true);
                String filename = frame.fd.getFile();
                String dirname = frame.fd.getDirectory();
                if(filename!=null && dirname!=null){
                    try {
                        tree = treeLoader.loadTreeFromFile(dirname + filename);
                        frame.setOutputArea("");
                    }
                    catch (IOException ex1){
                        frame.setOutputArea(ex1.toString());
                        tree = new BinaryTree();
                    }
                    frame.setTreeDisplayArea(tree.toString());
                }
            }
        });

        // Count Nodes
        frame.addButtonListener("Count Nodes", new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setOutputArea("Node Count: "+tree.nodeCount());
            }
        });

        // Height
        frame.addButtonListener("Height", new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setOutputArea("Height: "+tree.height());
            }
        });

        // Mirror
        frame.addButtonListener("Mirror", new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setOutputArea(tree.mirrorImage().toString());
            }
        });

        // Count Leaves
        frame.addButtonListener("Count Leaves", new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int leafct = tree.leafCount();
                if(leafct==1)
            frame.setOutputArea("The tree has 1 leaf");
                else
            frame.setOutputArea("The tree has "+leafct+" leaves");
            }
        });

        // Level Count
        frame.addButtonListener("Level Counts", new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int level=0;
                String output="";
                int lcount=tree.levelCount(level);
                if(lcount==0)
            output+="The tree is empty";
                else
            while(lcount>0 && level<=1000){
                if(lcount==1)
            output+=("There is 1 node at level "+level+"\n");
                else
            output+=("There are "+lcount+" nodes at level "+level+"\n");
        ++level;
        lcount=tree.levelCount(level);
            }
        frame.setOutputArea(output);
            }
        });


        // Weight Balance Factor
        frame.addButtonListener("Weight Balance Factor", new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setOutputArea("Weight balance factor:" 
                                       + tree.weightBalanceFactor());
            }
        });


        // Node Sum
        frame.addButtonListener("Node Sum", new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    frame.setOutputArea("Sum of data values: "+tree.nodeSum());
                }
                catch (ClassCastException ex1){
                    frame.setOutputArea("Tree contains non-integer data");
                }
                catch (NumberFormatException ex2){
                    frame.setOutputArea("Tree contains non-integer data");
                }
                catch (Exception ex){
                    frame.setOutputArea(ex.toString());
                }
            }
        });

        // Double
        frame.addButtonListener("Double", new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    tree.doubles();
                    frame.setTreeDisplayArea(tree.toString());
                    frame.setOutputArea("");
                }
                catch (ClassCastException ex){
                    frame.setOutputArea("Tree contains non-integer data");
                }
                catch (NumberFormatException ex2){
                    frame.setOutputArea("Tree contains non-integer data");
                }
                catch (Exception ex){
                    frame.setOutputArea(ex.toString());
                }
            }
        });

        // Max Path Sum
        frame.addButtonListener("Max Path Sum", new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    frame.setOutputArea("Maximum path sum: "+tree.maxPathSum());
                }
                catch (ClassCastException ex){
                    frame.setOutputArea("Tree contains non-integer data");
                }
                catch (NumberFormatException ex2){
                    frame.setOutputArea("Tree contains non-integer data");
                }
                catch (Exception ex){
                    frame.setOutputArea(ex.toString());
                }
            }
        });
        
        // Full
        frame.addButtonListener("Full", new ActionListener(){
            public void actionPerformed(ActionEvent e){
		if(tree.isFull())
		    frame.setOutputArea("The tree is full");
		else
		    frame.setOutputArea("Not a full binary tree");
            }
        });

        // Complete
        frame.addButtonListener("Complete", new ActionListener(){
            public void actionPerformed(ActionEvent e){
		if(tree.isComplete())
		    frame.setOutputArea("The tree is complete");
		else
		    frame.setOutputArea("Not a complete binary tree");
            }
        });

        // Prune
        frame.addButtonListener("Prune", new ActionListener(){
            public void actionPerformed(ActionEvent e){
		int removed = tree.prune();
		frame.setTreeDisplayArea(tree.toString());
		if(removed==1)
		    frame.setOutputArea(""+removed+" leaf pruned");
		else
		    frame.setOutputArea(""+removed+" leaves pruned");
            }
        });

        // preorder
        frame.addButtonListener("Preorder", new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setOutputArea(""+tree.preOrder());
            }
        });
        // inorder
        frame.addButtonListener("Inorder", new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setOutputArea(""+tree.inOrder());
            }
        });
        // postorder
        frame.addButtonListener("Postorder", new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setOutputArea(""+tree.postOrder());
            }
        });
        // level order
        frame.addButtonListener("Level order", new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setOutputArea(""+tree.levelOrder());
            }
        });
        
        // Quit
        frame.addButtonListener("Quit", new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(1);
            }
        });

    }

    //Main method
    public static void main(String[] args) {
        new TreeMethodApplication();
    }
}
