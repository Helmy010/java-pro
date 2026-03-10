package ui;

import model.Priority;
import model.Task;
import service.TaskManager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class MainFrame extends JFrame {

    private TaskManager manager = new TaskManager();

    private DefaultTableModel tableModel;
    private JTable table;

    public MainFrame(){

        setTitle("Todo List App");
        setSize(700,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
                new String[]{"ID","Title","Priority","Due","Status"},0);

        table = new JTable(tableModel);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row,
                                                   int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String status = table.getModel().getValueAt(row, 4).toString();
        if(status.equals("OVERDUE")) {
            c.setForeground(Color.RED);
        }
        else if(status.equals("IN_PROGRESS")){
            c.setForeground(Color.BLUE);
        }
        else if(status.equals("DONE")){
            c.setForeground(Color.GREEN);
        }
        else{
            c.setForeground(Color.BLACK);
        }
            return c;
        }
        });

        add(new JScrollPane(table),BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton addBtn = new JButton("Add Task");
        JButton deleteBtn = new JButton("Delete Task");
        JButton startBtn = new JButton("In_Progress");
        JButton doneBtn = new JButton("Mark Done");

        buttonPanel.add(addBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(startBtn);
        buttonPanel.add(doneBtn);

        add(buttonPanel,BorderLayout.SOUTH);

        addBtn.addActionListener(e -> addTask());
        deleteBtn.addActionListener(e -> deleteTask());
        startBtn.addActionListener(e -> markInProgress());
        doneBtn.addActionListener(e -> markDone());

        refreshTable();
        Timer timer = new Timer(60000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTable();  
        }
        });
        timer.start();
    }

    private void addTask() {

    String title = JOptionPane.showInputDialog(this, "Task Title:");
    if(title == null || title.isEmpty()) return;

    String description = JOptionPane.showInputDialog(this, "Description:");
    if(description == null) description = "";

    Priority priority = (Priority) JOptionPane.showInputDialog(
            this,
            "Select Priority",
            "Priority",
            JOptionPane.QUESTION_MESSAGE,
            null,
            Priority.values(),
            Priority.MEDIUM
    );

    String dueInput = JOptionPane.showInputDialog(this, "Due date (yyyy-MM-ddTHH:mm)");
    if(dueInput == null || dueInput.isEmpty()) return;

    LocalDateTime dueDateTime = LocalDateTime.parse(dueInput);

   
    manager.addTask(title, description, priority, dueDateTime);

    
    refreshTable();
}

    private void deleteTask(){

        int row = table.getSelectedRow();

        if(row == -1)
            return;

        int id = (int)tableModel.getValueAt(row,0);

        manager.deleteTask(id);
        refreshTable();
    }

    private void markInProgress(){

        int row = table.getSelectedRow();

        if(row == -1)
            return;

        int id = (int)tableModel.getValueAt(row,0);

        manager.getTasks().stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .ifPresent(t -> t.setStatus(model.Status.IN_PROGRESS));

        refreshTable();
    }

    private void markDone(){

        int row = table.getSelectedRow();

        if(row == -1)
            return;

        int id = (int)tableModel.getValueAt(row,0);

        manager.getTasks().stream()
                .filter(t -> t.getId()==id)
                .findFirst()
                .ifPresent(Task::markDone);

        refreshTable();
    }

    private void refreshTable(){

        tableModel.setRowCount(0);

        manager.checkOverdue();

        for(Task t : manager.getTasks()){

            tableModel.addRow(new Object[]{
                    t.getId(),
                    t.getTitle(),
                    t.getPriority(),
                    t.getDueDateTime(),
                    t.getStatus()
            });
        }
    }
}