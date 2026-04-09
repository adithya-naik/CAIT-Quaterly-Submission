import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  tasks: any[] = [];

  addTask(taskName: string) {
    if (!taskName.trim()) return;

    this.tasks.push({
      id: Date.now(),
      name: taskName
    });
  }

  deleteTask(id: number) {
    this.tasks = this.tasks.filter(task => task.id !== id);
  }

  updateTask(id: number, newName: string) {
    const task = this.tasks.find(t => t.id === id);
    if (task && newName.trim()) {
      task.name = newName;
    }
  }
}