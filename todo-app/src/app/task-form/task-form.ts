import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TaskService } from '../task';

@Component({
  selector: 'app-task-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './task-form.html',
  styleUrl: './task-form.css'
})
export class TaskFormComponent {
  taskName: string = '';

  constructor(private taskService: TaskService) {}

  addTask() {
    this.taskService.addTask(this.taskName);
    this.taskName = '';
  }
}