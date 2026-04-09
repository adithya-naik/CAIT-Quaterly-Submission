import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TaskService } from '../task';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './task-list.html',
  styleUrl: './task-list.css'
})
export class TaskListComponent {
  editingId: number | null = null;
  editedName: string = '';

  constructor(public taskService: TaskService) {}

  deleteTask(id: number) {
    this.taskService.deleteTask(id);
  }

  startEdit(task: any) {
    this.editingId = task.id;
    this.editedName = task.name;
  }

  saveEdit(id: number) {
    this.taskService.updateTask(id, this.editedName);
    this.editingId = null;
  }
}