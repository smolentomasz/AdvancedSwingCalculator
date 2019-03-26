import org.mariuszgromada.math.mxparser.Expression;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.MessageFormat;
import java.util.Date;


import javax.swing.JOptionPane;

import javax.swing.SwingUtilities;


public class MainCalculator {
    private static CalculatorGUI newCalculator;
    private static String lastEvaluation = "";
    private static String lastResult = "";

    public static double getDoneCalculation() {
        return doneCalculation;
    }

    private static double doneCalculation;
    public static void setMessageTextArea(String messageTextArea) {
        MainCalculator.messageTextArea = messageTextArea;
    }

    private static String messageTextArea;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            newCalculator = new CalculatorGUI();
            new AddFunctionList(newCalculator.getOptionList(), newCalculator.getEnterOperationsField(), newCalculator.getHistoryTextArea());
            newCalculator.getEvaluateButton().addActionListener(e -> {
                makeCalculations();
            });
            newCalculator.getEnterOperationsField().addKeyListener(myKeyListener);
        });

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
            String getTextFromField = newCalculator.getEnterOperationsField().getText();
            doneCalculation = e1.calculate();
            String result = MessageFormat.format(
                    "{2}   =   {0,number}. \t Calculated at {1,time} on {1,date}.\n",
                    doneCalculation, new Date(), getTextFromField);
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
