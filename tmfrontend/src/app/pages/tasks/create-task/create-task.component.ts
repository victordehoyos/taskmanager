import { Component, OnInit } from '@angular/core';
import { NbDialogRef } from '@nebular/theme';
import { TaskService } from '../../../services/task.service';

@Component({
  selector: 'ngx-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.scss']
})
export class CreateTaskComponent implements OnInit {
  task: any = { title: '', description: '', user: {}, status: 'Pendiente', priority: 'Baja' };
  usersList: any[] = [];

  constructor(private taskService: TaskService, protected dialogRef: NbDialogRef<CreateTaskComponent>) {}

  ngOnInit() {
    this.taskService.getUsers().subscribe(users => this.usersList = users);
  }

  save() {
    this.taskService.createTask(this.task).subscribe(() => {
      this.dialogRef.close(true);
    });
  }

  close() {
    this.dialogRef.close(false);
  }
}
