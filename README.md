To-Do List Application (Java)
1. Introduction
The To-Do List Application is a desktop application developed using Java and Swing GUI.
It allows users to manage daily tasks by adding, deleting, and updating their status.
The application helps users:
•	Organize tasks
•	Track deadlines
•	Identify overdue tasks
•	Monitor task priority
________________________________________
2. Technologies Used
Technology	Purpose
Java	Main programming language
Swing	GUI development
LocalDateTime	Handle date and time
Enum	Define task priority and status
JTable	Display tasks
Timer	Refresh tasks automatically
________________________________________
3. System Architecture
The application follows a simple layered architecture:
Main
   |
   v
MainFrame (UI Layer)
   |
   v
TaskManager (Service Layer)
   |
   v
Task + Enums (Model Layer)



Layers Explanation
Layer	Description
UI Layer	Handles user interaction
Service Layer	Manages business logic
Model Layer	Represents data structures
________________________________________
4. Model Layer
The Model represents the data structure of the application.
Priority Enum
public enum Priority {
    HIGH, MEDIUM, LOW
}
Purpose:
Defines the importance level of each task.
Example:
•	HIGH → urgent task
•	MEDIUM → normal task
•	LOW → optional task
________________________________________
Status Enum
public enum Status {
    PENDING, IN_PROGRESS, DONE, OVERDUE
}
Purpose:
Represents the current state of the task.
Status meanings:
Status	Meaning
PENDING	Task not started
IN_PROGRESS	Task is being worked on
DONE	Task completed
OVERDUE	Task deadline passed
________________________________________
5. Service Layer (TaskManager)
The TaskManager class controls all task operations.
Main responsibilities:
•	Add tasks
•	Delete tasks
•	Retrieve tasks
•	Detect overdue tasks
________________________________________
Task Storage
private List<Task> tasks = new ArrayList<>();
Tasks are stored in a dynamic list.
________________________________________
Adding a Task
public void addTask(String title, String description,
                    Priority priority,
                    LocalDateTime dueDateTime)
Steps:
1.	Create a new Task
2.	Assign unique ID
3.	Set default status → PENDING
4.	Add task to list
________________________________________
Deleting a Task
public void deleteTask(int id)
This method removes the task based on its ID.
________________________________________
Checking Overdue Tasks
public void checkOverdue()
This function:
1.	Gets the current time
2.	Compares with task due date
3.	If overdue → change status to OVERDUE
________________________________________
6. User Interface (MainFrame)
The GUI is implemented using Java Swing.
Main components:
Component	Purpose
JTable	Display tasks
JButton	Perform actions
JPanel	Organize buttons
JOptionPane	Input dialogs
________________________________________
Table Columns
The task table displays:
Column	Description
ID	Task identifier
Title	Task name
Priority	Task importance
Due	Deadline
Status	Current state
________________________________________
Buttons
The application provides 3 main buttons:
Button	Function
Add Task	Create new task
Delete Task	Remove selected task
Mark Done	Mark task as completed
________________________________________
7. Add Task Process
When the user clicks Add Task:
1.	Enter title
2.	Enter description
3.	Choose priority
4.	Enter due date
5.	Task is stored in the system
6.	Table refreshes automatically
________________________________________
8. Automatic Overdue Detection
The system automatically checks for overdue tasks every 60 seconds.
Timer timer = new Timer(60000, e -> refreshTable());
This ensures the task list is always updated.
________________________________________
9. Visual Highlight for Overdue Tasks
Overdue tasks are displayed in red color.
if(status.equals("OVERDUE")) {
    c.setForeground(Color.RED);
}
Purpose:
•	Improve task visibility
•	Alert the user
________________________________________
10. Main Class
The Main class starts the application.
SwingUtilities.invokeLater(() -> {
    new MainFrame().setVisible(true);
});
This ensures the GUI runs in the Event Dispatch Thread (EDT).
________________________________________
11. Features of the Application
Main features include:
✔ Add tasks
✔ Delete tasks
✔ Mark tasks as done
✔ Task priority system
✔ Deadline tracking
✔ Automatic overdue detection
✔ GUI interface
✔ Table display of tasks
________________________________________
12. Future Improvements
Possible enhancements:
•	Edit existing tasks
•	Save tasks to file / database
•	Search tasks
•	Task categories
•	Notifications
•	Drag & drop task sorting
________________________________________

