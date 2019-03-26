import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddFunctionList extends JList {
    public AddFunctionList(final JList<FunctionElementList> lista, final JTextField enterOperationsField, final JTextArea mainTextArea) {
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

        final DefaultListModel<FunctionElementList> listModel = new DefaultListModel<>();
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
                JList list = (JList) e.getSource();
                if (e.getClickCount() == 2) {
                    if (list.getSelectedIndex() <= 5) {
                        enterOperationsField.setText(lista.getModel().getElementAt(list.getSelectedIndex()).getExpression());
                        String txt = enterOperationsField.getText();
                        enterOperationsField.requestFocusInWindow();
                        enterOperationsField.setCaretPosition(txt.length() - 1);
                    } else if (list.getSelectedIndex() >= 6 && list.getSelectedIndex() <= 8) {
                        String expressionToField = lista.getModel().getElementAt(list.getSelectedIndex()).getExpression();
                        enterOperationsField.setText(expressionToField);
                        enterOperationsField.requestFocusInWindow();
                        enterOperationsField.setCaretPosition(enterOperationsField.getText().length());
                    } else {
                        enterOperationsField.setText(String.valueOf(MainCalculator.getDoneCalculation()));
                    }
                }
            }
        });
    }
}