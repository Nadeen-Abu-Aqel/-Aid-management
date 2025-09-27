package project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MyFrame extends JFrame implements ActionListener {
    private JLabel label1, label2, label3;
    private JTextField tf;
    private JPasswordField pass;
    private JButton button;
    JPanel p1, p2, p3;

    public MyFrame() {
        setSize(500, 400);
        setTitle("Community Resource Management System");
        Color color = new Color(79, 23, 49);
        getContentPane().setBackground(color);
        setLayout(new BorderLayout(20, 10));
        setLocationRelativeTo(null);

        label3 = new JLabel("WELCOME");
        label1 = new JLabel("Username: ");
        label2 = new JLabel("Password: ");
        tf = new JTextField(10);
        pass = new JPasswordField(10);
        button = new JButton("Login");
        button.addActionListener(this);

        p1 = new JPanel();
        p1.add(label3);
        add(p1, BorderLayout.NORTH);

        p2 = new JPanel();
        p2.add(label1);
        p2.add(tf);
        p2.add(label2);
        p2.add(pass);
        add(p2, BorderLayout.CENTER);

        p3 = new JPanel();
        p3.add(button);
        add(p3, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = tf.getText();
        String password = new String(pass.getPassword());
        if (username.equals("Engineer") && password.equals("123")) {
            dispose();
            new MainMenuWindow();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }
    }

    class MainMenuWindow extends JFrame {
        private JButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12;
        private JPanel p1;

        public MainMenuWindow() {
            setTitle("Main Menu");
            setSize(1200, 600);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Color color = new Color(133, 113, 122);
            getContentPane().setBackground(color);
            setLayout(new BorderLayout(30, 30));

            p1 = new JPanel(new GridLayout(2, 6, 10, 10));
            p1.setBackground(color);

            button1 = new JButton("Beneficiary");
            button2 = new JButton("Volunteer");
            button3 = new JButton("OrganizationStaff");
            button4 = new JButton("Food");
            button5 = new JButton("Request Aid");
            button6 = new JButton("Assign Aid");
            button7 = new JButton("Show Available Aid Items");
            button8 = new JButton("View Distribution Report");
            button9 = new JButton("Exit Window");
            button10 = new JButton("Medicine");
            button11 = new JButton("Book");
            button12 = new JButton("Clothing");

            p1.add(button1);
            p1.add(button2);
            p1.add(button3);
            p1.add(button4);
            p1.add(button11);
            p1.add(button12);
            p1.add(button10);
            p1.add(button5);
            p1.add(button6);
            p1.add(button7);
            p1.add(button8);
            p1.add(button9);

            add(p1, BorderLayout.NORTH);

            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new GenericWindow("Beneficiary",
                            new String[]{"Name", "ID", "Location", "Phone", "Family Size"});
                }
            });

            button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new GenericWindow("Volunteer",
                            new String[]{"Name", "ID", "Location", "Phone", "Sector"});
                }
            });

            button3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new GenericWindow("OrganizationStaff",
                            new String[]{"Name", "ID", "Location", "Phone", "Role", "Organization"});
                }
            });

            button4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new GenericWindow("Food",
                            new String[]{"Aid ID", "Name", "Quantity", "Priority", "Expiry", "Prescription"});
                }
            });

            button10.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new GenericWindow("Medicine",
                            new String[]{"Aid ID", "Name", "Quantity", "Priority", "Expiry", "Prescription"});
                }
            });

            button11.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new GenericWindow("Book",
                            new String[]{"Aid ID", "Name", "Quantity", "Priority", "Subject", "Author", "Title"});
                }
            });

            button12.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new GenericWindow("Clothing",
                            new String[]{"Aid ID", "Name", "Quantity", "Priority", "Size", "Winter?"});
                }
            });

            button5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new Askforhelp();
                }
            });

            button6.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new Showhelp();
                }
            });

            button7.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ArrayList<String[]> allData = GenericWindowManager.getAllData();
                    if (allData.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No data available!");
                    } else {
                        new DataViewerWindow("Available Aid Items", allData, GenericWindowManager.getAllColumnNames());
                    }
                }
            });

            button8.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ArrayList<String[]> allData = GenericWindowManager.getAllData();
                    if (allData.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No data available!");
                    } else {
                        new DataViewerWindow("Distribution Report", allData, GenericWindowManager.getAllColumnNames());
                    }
                }
            });

            button9.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new ExitWindow();
                }
            });

            setVisible(true);
        }
    }

    static class GenericWindow extends JFrame implements ActionListener {
        private JTextField[] fields;
        private JButton saveButton, deleteButton;
        private ArrayList<String[]> dataList = new ArrayList<>();
        private String[] fieldNames;

        public GenericWindow(String title, String[] fieldNames) {
            this.fieldNames = fieldNames;
            setTitle(title);
            setSize(400, 50 + fieldNames.length * 50);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(fieldNames.length + 1, 2, 5, 5));

            fields = new JTextField[fieldNames.length];

            for (int i = 0; i < fieldNames.length; i++) {
                add(new JLabel("Enter " + fieldNames[i] + ":"));
                fields[i] = new JTextField();
                add(fields[i]);
            }

            saveButton = new JButton("Save");
            deleteButton = new JButton("Delete");
            add(saveButton);
            add(deleteButton);

            saveButton.addActionListener(this);
            deleteButton.addActionListener(this);

            GenericWindowManager.registerWindow(this, fieldNames);

            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == saveButton) {
                String[] entry = new String[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    entry[i] = fields[i].getText();
                }
                dataList.add(entry);
                JOptionPane.showMessageDialog(this, "Data Saved!");
                clearFields();
            } else if (e.getSource() == deleteButton) {
                if (!dataList.isEmpty()) {
                    dataList.remove(dataList.size() - 1);
                    JOptionPane.showMessageDialog(this, "Last Entry Deleted!");
                } else {
                    JOptionPane.showMessageDialog(this, "No Data to Delete!");
                }
            }
        }

        private void clearFields() {
            for (JTextField f : fields) f.setText("");
        }

        public ArrayList<String[]> getDataList() {
            return dataList;
        }
    }

    static class GenericWindowManager {
        private static ArrayList<GenericWindow> windows = new ArrayList<>();
        private static ArrayList<String[]> columnNamesList = new ArrayList<>();

        public static void registerWindow(GenericWindow gw, String[] columnNames) {
            windows.add(gw);
            columnNamesList.add(columnNames);
        }

        public static ArrayList<GenericWindow> getWindows() {
            return windows;
        }

        public static ArrayList<String[]> getAllData() {
            ArrayList<String[]> allData = new ArrayList<>();
            for (GenericWindow gw : windows) {
                allData.addAll(gw.getDataList());
            }
            return allData;
        }

        public static String[] getAllColumnNames() {
            if (!columnNamesList.isEmpty()) {
                return columnNamesList.get(0); 
            } else return new String[]{};
        }
    }

    static class DataViewerWindow extends JFrame {
        public DataViewerWindow(String title, ArrayList<String[]> data, String[] columnNames) {
            setTitle(title);
            setSize(600, 400);
            setLocationRelativeTo(null);

            String[][] tableData = new String[data.size()][columnNames.length];
            for (int i = 0; i < data.size(); i++) {
                tableData[i] = data.get(i);
            }

            JTable table = new JTable(tableData, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane);

            setVisible(true);
        }
    }

    class Askforhelp extends JFrame implements ActionListener {
        private JTextField t01, t02, t03;
        private JButton saveButtonn;
        public Askforhelp() {
            setTitle("Request Aid");
            setSize(300, 200);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(4, 2));
            t01 = new JTextField(); t02 = new JTextField(); t03 = new JTextField();
            saveButtonn = new JButton("add");
            add(new JLabel("Enter beneficiary name:")); add(t01);
            add(new JLabel("Enter aid name to request:")); add(t02);
            add(new JLabel("requested aid:")); add(t03);
            add(saveButtonn);
            saveButtonn.addActionListener(this);
            setVisible(true);
        }
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(this, "Ask for help is done");
            dispose();
        }
    }

    class Showhelp extends JFrame implements ActionListener {
        private JTextField t01, t02, t03;
        private JButton saveButton;
        public Showhelp() {
            setTitle("Assign Aid");
            setSize(300, 200);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(4, 2));
            t01 = new JTextField(); t02 = new JTextField(); t03 = new JTextField();
            saveButton = new JButton("add");
            add(new JLabel("Enter beneficiary name:")); add(t01);
            add(new JLabel("Available aid items:")); add(t02);
            add(new JLabel("Enter aid name to assign:")); add(t03);
            add(saveButton);
            saveButton.addActionListener(this);
            setVisible(true);
        }
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(this, "Ask for help is done");
            dispose();
        }
    }

    class ExitWindow extends JFrame implements ActionListener {
        private JLabel Label;
        private JButton confirm, cancel;
        public ExitWindow() {
            setTitle("Exit Confirmation");
            setSize(300, 150);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(2, 1));
            Label = new JLabel("Are you sure you want to exit?", SwingConstants.CENTER);
            add(Label);
            JPanel buttonPanel = new JPanel();
            confirm = new JButton("Yes, Exit");
            cancel = new JButton("Cancel");
            confirm.addActionListener(this);
            cancel.addActionListener(this);
            buttonPanel.add(confirm);
            buttonPanel.add(cancel);
            add(buttonPanel);
            setVisible(true);
        }
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == confirm) {
                JOptionPane.showMessageDialog(this, "Exit Window");
                System.exit(0);
            } else if (e.getSource() == cancel) {
                JOptionPane.showMessageDialog(this, "No Exit Window");
                dispose();
            }
        }
    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}