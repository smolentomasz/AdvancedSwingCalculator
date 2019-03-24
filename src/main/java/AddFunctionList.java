
import org.mariuszgromada.math.mxparser.Expression;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddFunctionList extends JList {
    AdvancedCalculatorLayout calculateFunction;
    public  AddFunctionList(final JList<FunctionElementList> lista, final JTextField enterOperationsField, final JTextArea mainTextArea) {
        FunctionElementList sinusFunction = new FunctionElementList("Sinus", "sin()");
        FunctionElementList cosinusFunction = new FunctionElementList("Cosinus", "cos()");
        FunctionElementList tangensFunction = new FunctionElementList("Tangens", "tan()");
        FunctionElementList logarithmFunction = new FunctionElementList("Logarithm (a,b)", "log()");
        FunctionElementList moduloFunction = new FunctionElementList("Modulo (a,b)", "mod()");
        FunctionElementList eulerFunction = new FunctionElementList("Euler number (n,k)", "Euler()");
        FunctionElementList PIFunction = new FunctionElementList("PI", "pi");
        FunctionElementList EFunction = new FunctionElementList("e", "e");
        FunctionElementList eulerMascheroniFunction = new FunctionElementList("Euler-Mascheroni", "[gam]");
        FunctionElementList lastResultFunction = new FunctionElementList("Last result", "last_result");

        final DefaultListModel<FunctionElementList> listModel = new DefaultListModel<FunctionElementList>();
        listModel.addElement(sinusFunction);
        listModel.addElement(cosinusFunction);
        listModel.addElement(tangensFunction);
        listModel.addElement(logarithmFunction);
        listModel.addElement(moduloFunction);
        listModel.addElement(eulerFunction);
        listModel.addElement(PIFunction);
        listModel.addElement(EFunction);
        listModel.addElement(eulerMascheroniFunction);
        listModel.addElement(lastResultFunction);
        lista.setModel(listModel);

        lista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList)e.getSource();
                if (e.getClickCount() == 2) {
                    if(list.getSelectedIndex() >= 0 && list.getSelectedIndex() <=5){
                    enterOperationsField.setText(lista.getModel().getElementAt(list.getSelectedIndex()).getExpression());
                    String txt = enterOperationsField.getText();
                    enterOperationsField.requestFocusInWindow();
                    enterOperationsField.setCaretPosition(txt.length() - 1);
                    }
                    else if(list.getSelectedIndex() >=6 && list.getSelectedIndex() <=8){
                        String actualTextinTextArea = mainTextArea.getText();
                        Expression e1 = new Expression(lista.getModel().getElementAt(list.getSelectedIndex()).getExpression());
                        String pom = lista.getModel().getElementAt(list.getSelectedIndex()).toString();
                        double expressionResult = e1.calculate();
                        String result = MessageFormat.format(
                                "{2}   =   {0,number,integer}. \t Calculated at {1,time} on {1,date}.\n",
                                expressionResult, new Date(), pom);
                        actualTextinTextArea += result;
                        mainTextArea.setText(actualTextinTextArea);
                    }
                    else{
                        String actualTextinTextAreaRes = mainTextArea.getText();
                        String[] partsOfText = actualTextinTextAreaRes.split("\\.");
                        enterOperationsField.setText(partsOfText[partsOfText.length - 3]);
                    }
                }
            }
        });
    }
}
