# Angular To-Do Application – Project Documentation

## 1. Introduction
This project demonstrates the development of a simple To-Do application using Angular (latest version with standalone components). The main objective is to understand how frontend components, forms, and services work together to create a dynamic user interface.

---

## 2. What is Angular?
Angular is a frontend framework used to build dynamic web applications. It helps developers create reusable components and manage application logic efficiently.

---

## 3. Angular Components

### What is a Component?
A component is the building block of an Angular application. Each component controls a part of the user interface.

### Key Parts of a Component:
- **TypeScript file (.ts)** → Contains logic
- **HTML file (.html)** → Defines UI structure
- **CSS file (.css)** → Styles the UI

### In this project:
- TaskFormComponent → Handles user input
- TaskListComponent → Displays tasks
- App Component → Main container

---

## 4. Angular Forms

Angular provides ways to handle user input using forms.

### Template-driven Forms (used here):
- Uses `ngModel` for two-way data binding
- Automatically syncs UI and data

### Example:
User types in input → value stored in variable → used in logic

---

## 5. Project Structure and File Explanation

### 5.1 Service File

**task.ts**
- Stores all tasks
- Acts as shared data storage
- Methods:
  - addTask()
  - deleteTask()
  - updateTask()

---

### 5.2 Task Form Component

**task-form.ts**
- Handles adding tasks
- Uses TaskService to store data

**task-form.html**
- Input field for entering task
- Button to add task

**task-form.css**
- Styles input and button

---

### 5.3 Task List Component

**task-list.ts**
- Displays tasks from service
- Handles delete and edit operations

**task-list.html**
- Shows list using loop (*ngFor)
- Includes Edit, Save, Delete buttons

**task-list.css**
- Styles task list UI

---

### 5.4 Main App Component

**app.ts**
- Root component
- Connects all components

**app.html**
- Displays title and components

**app.css**
- Styles main layout

---

## 6. To-Do Application Functionality

### Features Implemented:

1. Add Task
- User enters task in input field
- Task is stored in service
- UI updates instantly

2. View Tasks
- All tasks displayed in list format

3. Delete Task
- Removes task from list
- Updates UI immediately

4. Edit Task
- User can modify existing task
- Changes reflected instantly

---

## 7. Application Flow

User Action → Angular Component → Service → UI Update

Example:
- User clicks Add → Form component sends data → Service stores task → List updates automatically

---

## 8. Key Concepts Learned

- Angular standalone components
- Component-based architecture
- Template-driven forms
- Two-way data binding (ngModel)
- Service-based data sharing
- Dynamic UI updates without refresh

---

## 9. Conclusion

This project demonstrates how Angular components, forms, and services work together to build a complete frontend application. The To-Do app provides a clear understanding of user interaction, data handling, and UI updates in real-time.

---

## 10. Future Enhancements

- Connect to backend (Spring Boot)
- Store data in database
- Add authentication
- Improve UI design

---

End of Document

