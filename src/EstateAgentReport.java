import javax.swing.*;
import java.awt.*;
import java.io.*;

public class EstateAgentReport extends JFrame {

    private EstateAgent agent = new EstateAgent();

    private JComboBox<String> cmbLocation = new JComboBox<>(
            new String[]{"Cape Town", "Durban", "Pretoria"});
    private JTextField txtName = new JTextField();
    private JTextField txtPrice = new JTextField();
    private JTextField txtCommission = new JTextField();
    private JTextArea txtReport = new JTextArea(7, 25);

    public EstateAgentReport() {
        setTitle("Estate Agent Report");
        setSize(420, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // Menus
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu tools = new JMenu("Tools");
        JMenuItem miExit = new JMenuItem("Exit");
        JMenuItem miProcess = new JMenuItem("Process Report");
        JMenuItem miClear = new JMenuItem("Clear");
        JMenuItem miSave = new JMenuItem("Save Report");
        file.add(miExit);
        tools.add(miProcess);
        tools.add(miClear);
        tools.add(miSave);
        bar.add(file);
        bar.add(tools);
        setJMenuBar(bar);

        // Input panel
        JPanel input = new JPanel(new GridLayout(5, 2, 5, 5));
        input.add(new JLabel("AGENT LOCATION:"));
        input.add(cmbLocation);
        input.add(new JLabel("ESTATE AGENT NAME:"));
        input.add(txtName);
        input.add(new JLabel("PROPERTY PRICE:"));
        input.add(txtPrice);
        input.add(new JLabel("COMMISSION PERCENTAGE:"));
        input.add(txtCommission);
        input.add(new JLabel("ESTATE AGENT REPORT:"));
        add(input, BorderLayout.NORTH);

        txtReport.setEditable(false);
        add(new JScrollPane(txtReport), BorderLayout.CENTER);

        // Events
        miExit.addActionListener(e -> System.exit(0));        // Q.2.3
        miProcess.addActionListener(e -> processReport());    // Q.2.4
        miSave.addActionListener(e -> saveReport());          // Q.2.5
        miClear.addActionListener(e -> clearForm());          // Q.2.6
    }

    // Q.2.4: validate, calculate and display the report
    private void processReport() {
        String location = (String) cmbLocation.getSelectedItem();
        Data data = new Data(location, txtName.getText(),
                txtPrice.getText(), txtCommission.getText());

        if (!agent.ValidateData(data)) {
            JOptionPane.showMessageDialog(this,
                    "Invalid data. Name is required, and price and commission must be numbers greater than 0.");
            return;
        }

        double price = Double.parseDouble(data.getPrice());
        double percentage = Double.parseDouble(data.getCommission());
        double commission = agent.CalculateCommission(data.getPrice(), data.getCommission());

        txtReport.setText(
                "AGENT LOCATION: " + location + "\n" +
                        "AGENT NAME: " + data.getName().trim() + "\n" +
                        "PROPERTY PRICE: R " + price + "\n" +
                        "COMMISSION PERCENTAGE: " + percentage + "%\n" +
                        "CALCULATED COMMISSION: R " + commission + "\n");
    }

    // Q.2.5: save the report to report.txt
    private void saveReport() {
        if (txtReport.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Process the report before saving.");
            return;
        }
        try (PrintWriter out = new PrintWriter(new FileWriter("report.txt"))) {
            out.println("ESTATE AGENT REPORT");
            out.println("**********************************");
            out.print(txtReport.getText());
            out.println("**********************************");
            JOptionPane.showMessageDialog(this, "Report saved to report.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not save: " + ex.getMessage());
        }
    }

    // Q.2.6: reset everything to the default state
    private void clearForm() {
        cmbLocation.setSelectedIndex(0);
        txtName.setText("");
        txtPrice.setText("");
        txtCommission.setText("");
        txtReport.setText("");
    }

    public static void main(String[] args) {
        new EstateAgentReport().setVisible(true);
    }
}