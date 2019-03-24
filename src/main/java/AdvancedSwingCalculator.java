import org.mariuszgromada.math.mxparser.Expression;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.MessageFormat;
import java.util.Date;


import javax.swing.JOptionPane;

import javax.swing.SwingUtilities;


public class AdvancedSwingCalculator{
    private static AdvancedCalculatorLayout newCalculator;
    private static String lastEvaluation = "";

    public static void setMessageTextArea(String messageTextArea) {
        AdvancedSwingCalculator.messageTextArea = messageTextArea;
    }

    private static String messageTextArea;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                newCalculator = new AdvancedCalculatorLayout();
                new AddFunctionList(newCalculator.getOptionList(), newCalculator.getEnterOperationsField(), newCalculator.getHistoryTextArea());
                newCalculator.getEvaluateButton().addActionListener(new MyActionListener());
                newCalculator.getEnterOperationsField().addKeyListener(myKeyListener);
            }
        });

    }

    public static class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Evaluate!")){
               makeCalculations();
            }
        }
    }
    static KeyListener myKeyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }
        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == 10){
               makeCalculations();
            }
            else if(e.getKeyCode() == 38){
                newCalculator.getEnterOperationsField().setText(lastEvaluation);
            }
        }
        @Override
        public void keyPressed(KeyEvent e) {
        }
    };
    public static void makeCalculations(){
        Expression e1 = new Expression(newCalculator.getEnterOperationsField().getText());
        messageTextArea = newCalculator.getHistoryTextArea().getText();
        if (e1.checkSyntax()) {
            String pom = newCalculator.getEnterOperationsField().getText();
            double planet = e1.calculate();
            String result = MessageFormat.format(
                    "{2}   =   {0,number,integer}. \t Calculated at {1,time} on {1,date}.\n",
                    planet, new Date(), pom);
            messageTextArea += result;
            newCalculator.getHistoryTextArea().setText(messageTextArea);
            lastEvaluation = newCalculator.getEnterOperationsField().getText();

        }
        else {
            String errorMessage = e1.getErrorMessage();
            JOptionPane.showMessageDialog(null, "Składnia działania jest niepoprawna!", "Błąd!",
                    JOptionPane.ERROR_MESSAGE);
            newCalculator.getHistoryTextArea().requestFocusInWindow();
        }
        newCalculator.getEnterOperationsField().setText("");
    }
}
