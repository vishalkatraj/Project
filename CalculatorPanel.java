package guicalculator;


/*
 * Simple calculator that performs standard operations of +, -, x, /, =, C and
 * incorporates standard memory functions of m+, m-, mc, mr
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The calculator panel
 * @author 
 */
public class CalculatorPanel extends JPanel {

    /**
     * Define instance variables
     */
    private final int CALC_WIDTH=250;
    private final int CALC_HEIGHT=225;
    private final JLabel result;
    private final String[] ops={"*", "+", "/", "=", "C", "-", "mc", "m+", "m-", "mr"};
    private final JButton[] numbers;
    private final JButton[] operators;
    private float memory;
    /**
     * Constructor for the Calculator Panel: Sets up the GUI
     */
    public CalculatorPanel() {
        //  background color(bg) of panel to light grey
        setBackground(Color.LIGHT_GRAY);
        
        // sets the size of the panel of calculator 
        setPreferredSize(new Dimension(CALC_WIDTH,CALC_HEIGHT)); 
        
        // create  lable and set the size
        //font and background color
        result=new JLabel("0",JLabel.RIGHT);
        result.setPreferredSize(new Dimension(CALC_WIDTH-5,50));
        result.setFont(new Font("Helvetica",Font.BOLD,40));
        result.setBackground(Color.WHITE);
        result.setOpaque(true);
        
        // creates an array 10 items JButton to store numbers
        numbers=new JButton[10];
        
        // creates an array 10 items JButton to store operators
        operators=new JButton[10];
        
        // creates a listener for button's
        ButtonListener buttonListener=new ButtonListener();
        
        
     // loop for each button in operators
        for(int i=0;i<operators.length;i++){
            // creates a button operator of given size, font, foreground color and with listerner assigned to it
            operators[i]=new JButton(ops[i]);
            operators[i].addActionListener(buttonListener);
            operators[i].setPreferredSize(new Dimension(53,30));
            operators[i].setFont(new Font("Helvetica",Font.BOLD,12));
            operators[i].setForeground(Color.blue);
        }
        
        // loop for each button in numbers
        for(int i=0;i<numbers.length;i++){
        // creates a button for numbers of given size,font and with listerner assigned to it
            numbers[i]=new JButton(Integer.toString(i));
            numbers[i].addActionListener(buttonListener);
            numbers[i].setPreferredSize(new Dimension(53,30));
            numbers[i].setFont(new Font("Helvetica",Font.BOLD,15));
        }
        
        
        // set foreground color of C button to blue
        operators[4].setForeground(Color.blue);
    
        // adding buttons to the panel
        add(result);        
        add(operators[6]);add(operators[7]);add(operators[8]);add(operators[9]);        
        add(numbers[7]);add(numbers[8]);add(numbers[9]);add(operators[0]);
        add(numbers[4]);add(numbers[5]);add(numbers[6]);add(operators[1]);
        add(numbers[1]);add(numbers[2]);add(numbers[3]);add(operators[2]);
        add(numbers[0]);add(operators[3]);add(operators[4]);add(operators[5]);
        
        // initialize memory to 0
        memory=0.0f;
    }

    
    /**
     * Define the calculate method
     * Perform the calculations on <i>num1</i> and <i>num2</i> depending on
     * the operation <i>op</i>
     * @param op the operation
     * @param num1 the first number of the calculation
     * @param num2 the second number of the calculation
     * @return the result of the calculation
     */
    private float calculate(char op, float num1, float num2){
        float result=0.0f;
        
        // switch statements based on the operator to perform required operation
        switch(op){
        	case '-':
        		result=num1-num2;
        		break;
            case '+':
                result=num1+num2;
                break;
            case '*':
                result=num1*num2;
                break;                
            case '/':
                result=num1/num2;
                break;
        }
        // based on operator returns the result
        return result;
    }
    
    
    /**
     * Define the private inner class ButtonListener
     */
    private class ButtonListener implements ActionListener{
        private float num1;
        private char op;
        private float num2;

        @Override
        public void actionPerformed(ActionEvent e) {
            // get the action command of the button clicked
            String action=e.getActionCommand();
            
            // switch statement based on the action
            switch(action.charAt(0)){
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    // when number is pressed check if the result is not yet stored with value
                    if(result.getText().equals("0")){
                        result.setText(action);
                    }
                    else{
                        result.setText(result.getText()+action);
                    }
                    break;
                case '-':
                case '+':
                case '*':
                case '/':
                    // when operator is pressed
                    num1=Float.parseFloat(result.getText());
                    op=action.charAt(0);
                    result.setText("0");
                    break;
                case 'C':
                    // clears the result and any stored numbers
                    result.setText("0");
                    num1=0;
                    num2=0;
                    break;
                case '=':
                    // when equals is pressed perform the specified arithmentic operation
                    num2=Float.parseFloat(result.getText());
                    result.setText(Float.toString(calculate(op,num1,num2)));
                    break;
                case 'm':
                    // switch statement action for any of the memory button press
                    switch (action) {
                        case "m+":{
                            // add current value to memory
                            float currentValue=Float.parseFloat(result.getText());
                            memory+=currentValue;
                            break;
                        }
                        case "m-":{
                            // subtract current value from memory
                            float currentValue=Float.parseFloat(result.getText());
                            memory-=currentValue;
                            break;
                        }
                        case "mr":
                            //  the value stored in the memory is displayed
                            result.setText(Float.toString(memory));
                            break;
                        case "mc":
                            // clearing  the value in the memory
                            memory=0.0f;
                            break;
                    }
                    break;
                }  
            }           
        }
    
}
