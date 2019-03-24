import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultListModel;
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

public class AdvancedCalculatorLayout{
    private JTextField enterOperationsField;

    JTextField getEnterOperationsField() {
        return enterOperationsField;
    }
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }
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

    public JPanel getListPanel() {
        return listPanel;
    }

    private JPanel listPanel;
    private JFrame calculatorFrame;

    AdvancedCalculatorLayout() {

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
        calculatorFrame.setTitle("Zaawansowany kalkulator");
        calculatorFrame.setContentPane(mainPanel);
        calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculatorFrame.setSize(screenWidth /2, screenHeight /2);

        calculatorMenuBar = new JMenuBar();
        calculatorMenu = new JMenu("Options");
        calculatorMenu.setMnemonic(KeyEvent.VK_O);
        calculatorMenuBar.add(calculatorMenu);

        calculatorMenuItem = new JMenuItem("Reset", KeyEvent.VK_R);
        calculatorMenuItem.addActionListener(new MyActionListener());
        calculatorMenu.add(calculatorMenuItem);

        calculatorMenuItem = new JMenuItem("Exit", KeyEvent.VK_E);
        calculatorMenuItem.addActionListener(new MyActionListener());
        calculatorMenu.add(calculatorMenuItem);
        calculatorFrame.setJMenuBar(calculatorMenuBar);



        calculatorFrame.setVisible(true);
    }

    public class MyActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Reset")){
                enterOperationsField.setText("");
                historyTextArea.setText("");
                AdvancedSwingCalculator.setMessageTextArea("");
            }
            else{
                calculatorFrame.dispose();
            }
        }
    }
}
