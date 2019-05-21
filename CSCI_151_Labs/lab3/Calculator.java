import javax.swing.UIManager;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Calculator extends JFrame {
   
   private ExpressionManager expManager = new ExpressionManager();
   private static final int buttonWidth=120, buttonHeight=40;
   private static final Font font = new java.awt.Font("SansSerif", 0, 22); 
   private static final Dimension buttonDimension = new Dimension(buttonWidth,buttonHeight);
   private static final String[] keyLabels = {"1","2","3","+","4","5","6","-","7","8","9","*",
      "(","0",")","/"};
   
   // all of the GUI components
   private Container contentPane;
   private JPanel jPanel1 = new JPanel();
   private JPanel jPanel2 = new JPanel();
   private JPanel jPanel3 = new JPanel();
   private JPanel jPanel4 = new JPanel();
   
   private JTextField entryField = new JTextField();
   
   private JButton[] keys = new Key[keyLabels.length];
   
   private JButton powerButton = new Key("^", buttonDimension);
   private JButton equalsButton = new Key("=", buttonDimension);
   private JButton clearButton = new JButton("clear");
   
   private JTextField postfixField = new JTextField();
   private JLabel postfixLabel = new JLabel("postfix:");
   private JTextField prefixField = new JTextField();
   private JLabel prefixLabel = new JLabel("prefix:");
   private JTextField infixField = new JTextField();
   private JLabel infixLabel = new JLabel("infix:");
   
   private boolean packFrame = false;
   
   //Construct the application
   public Calculator() {
      setSize(new Dimension(4*(buttonWidth+10)-10, 400));
      setTitle("Calculator");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
      
      // add the components
      contentPane = getContentPane();
      contentPane.setLayout(new BorderLayout());
      contentPane.add(jPanel1, BorderLayout.CENTER);
      contentPane.add(jPanel2,  BorderLayout.SOUTH);
      contentPane.add(entryField, BorderLayout.NORTH);
      
      infixField.setFont(font);
      infixField.setHorizontalAlignment(SwingConstants.RIGHT);
      postfixField.setFont(font);
      postfixField.setHorizontalAlignment(SwingConstants.RIGHT);
      prefixField.setFont(font);
      prefixField.setHorizontalAlignment(SwingConstants.RIGHT);
      entryField.setFont(font);
      entryField.setHorizontalAlignment(SwingConstants.RIGHT);
      
      clearButton.setPreferredSize(new Dimension(2*buttonWidth+10, buttonHeight));
      clearButton.setFont(font);
      
      infixLabel.setHorizontalAlignment(SwingConstants.LEFT);
      postfixLabel.setHorizontalAlignment(SwingConstants.LEFT);
      prefixLabel.setHorizontalAlignment(SwingConstants.LEFT);
      
      //  Place digit and operator buttons in center panel      
      for(int i=0; i<keyLabels.length; i++){
         keys[i] = new Key(keyLabels[i], buttonDimension);
         jPanel1.add(keys[i],null);
      }
      
      jPanel1.add(clearButton, null);
      jPanel1.add(powerButton, null);
      jPanel1.add(equalsButton, null);
      
      //  Divide south panel into left and right panels
      jPanel2.setLayout(new BorderLayout());
      jPanel2.add(jPanel3, BorderLayout.WEST);
      jPanel2.add(jPanel4, BorderLayout.CENTER);
      jPanel3.setLayout(new GridLayout(3,1));
      jPanel4.setLayout(new GridLayout(3,1));
      
      //  Place output labels in southwest panel
      postfixLabel.setFont(font);
      prefixLabel.setFont(font);
      infixLabel.setFont(font);
      jPanel3.add(infixLabel, null);
      jPanel3.add(postfixLabel, null);
      jPanel3.add(prefixLabel, null);
      
      //  Place output fields in southcenter panel
      postfixField.setFont(font);
      infixField.setFont(font);
      jPanel4.add(infixField, null);     
      jPanel4.add(postfixField, null);
      jPanel4.add(prefixField, null);
      
      //Validate frames that have preset sizes
      //Pack frames that have useful preferred size info, e.g. from their layout
      if (packFrame) {
         this.pack();
      }
      else {
         this.validate();
      }
      //Center the window
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension frameSize = this.getSize();
      if (frameSize.height > screenSize.height) {
         frameSize.height = screenSize.height;
      }
      if (frameSize.width > screenSize.width) {
         frameSize.width = screenSize.width;
      }
      this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
      this.setVisible(true);
      
      clearButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            entryField.setText("");
            postfixField.setText("");
            prefixField.setText("");
            infixField.setText("");
         }
      });
      
      equalsButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent event){
            processEntry();
         }
      });
      
      entryField.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            processEntry();
         }
      });
   }

   /* process the contents of the entry field */
   private void processEntry(){
      String infixString = entryField.getText();
      StringTokenizer tokenizer = new StringTokenizer(infixString,"+-*^/() ",true);
      java.util.List<Token> infix = new ArrayList<Token>();
      String errorMessage = null;
      // tokenize the input
      while(tokenizer.hasMoreTokens()){
         String stoken = tokenizer.nextToken();
         if(!stoken.equals(" ")){
	     if("()+-*^/".contains(stoken)){
		infix.add(new Operator(stoken));
	     }
	    else {
		try{
		    infix.add(new Number(Integer.parseInt(stoken)));
		}
		catch(NumberFormatException ne){
		    errorMessage = "Invalid token: "+stoken;
		    break;
		}
	    }
	 }
      }
      if(errorMessage==null){
	  try {
	      // convert to postfix
	      java.util.List<Token> postfix = expManager.infixToPostfix(infix);
	      // build the expression
	      Expression exp = expManager.buildExpression(postfix);
	      // display the results
	      infixField.setText(exp.toInfix());
	      postfixField.setText(exp.toPostfix());
	      prefixField.setText(exp.toPrefix());
	      entryField.setText(""+exp.valueOf());
	  }
	  catch(ArithmeticException ae){
	      errorMessage = ae.getMessage();
	  }
      }
      if(errorMessage!=null)
	  infixField.setText(errorMessage);
   }

   //Main method
   public static void main(String[] args) {
      new Calculator();
   }

   // an inner class to define Key as a subclass of JButton
   class Key extends JButton {
      String token;
      
      public Key(String token, Dimension dimension){
         this.token = token;
         setText(token);
         setFont(font);
         setPreferredSize(dimension);
         if(!token.equals("=")){
            addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  String resultString = entryField.getText();
                  entryField.setText(resultString+Key.this.token);
               }
            });
         }
      }    
   }
}
