import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CalculatorGUI {
    private JTextField enterOperationsField;

    JTextField getEnterOperationsField() {
        return enterOperationsField;
    }

    private JPanel mainPanel;
    private JButton evaluateButton;

    JButton getEvaluateButton() {
        return evaluateButton;
    }

    private JScrollPane scrollContainerPane;
    private JTextArea historyTextArea;

    public JList getOptionList() {
        return optionList;
    }

    private JList optionList;

    JTextArea getHistoryTextArea() {
        return historyTextArea;
    }

    private JPanel listPanel;
    private JFrame calculatorFrame;

    CalculatorGUI() {

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        JMenuBar calculatorMenuBar;
        JMenuItem calculatorMenuItem;
        JMenu calculatorMenu;

        mainPanel = new JPanel();

        calculatorFrame = new JFrame();
        calculatorFrame.setTitle("Advanced calculator");
        calculatorFrame.setContentPane(mainPanel);
        calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculatorFrame.setSize(screenWidth / 2, screenHeight / 2);

        calculatorMenuBar = new JMenuBar();
        calculatorMenu = new JMenu("Options");
        calculatorMenu.setMnemonic(KeyEvent.VK_O);
        calculatorMenuBar.add(calculatorMenu);

        calculatorMenuItem = new JMenuItem("Reset", KeyEvent.VK_R);
        calculatorMenuItem.addActionListener(e -> {
            enterOperationsField.setText("");
            historyTextArea.setText("");
            MainCalculator.setMessageTextArea("");
        });
        calculatorMenu.add(calculatorMenuItem);

        calculatorMenuItem = new JMenuItem("Exit", KeyEvent.VK_E);
        calculatorMenuItem.addActionListener(e -> {
            calculatorFrame.dispose();
        });
        calculatorMenu.add(calculatorMenuItem);
        calculatorFrame.setJMenuBar(calculatorMenuBar);


        calculatorFrame.setVisible(true);
    }
}
