package guicalculator;


import javax.swing.JFrame;

/*
 * Driver Class
 *

 */
public class CalculatorDriver {

  
    
   public static void main(String[] args) {
      JFrame frame = new JFrame("Simple Calculator");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
      CalculatorPanel panel = new CalculatorPanel();
      frame.getContentPane().add(panel);
   
      frame.pack();
      frame.setResizable(false);
      frame.setLocation(800, 300);
      frame.setVisible(true);
   }
}
