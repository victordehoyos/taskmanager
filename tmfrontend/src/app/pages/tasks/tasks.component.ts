import { Component, OnInit } from '@angular/core';
import { LocalDataSource } from 'ng2-smart-table';
import { NbDialogService } from '@nebular/theme';
import { TaskService } from '../../services/task.service';
import { CreateTaskComponent } from './create-task/create-task.component';

@Component({
  selector: 'ngx-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.scss']
})
export class TasksComponent implements OnInit {

  source: LocalDataSource = new LocalDataSource();
  usersList: any[] = [];
  role: string = ''; 
  user: any = {}; 
  statusFilter = 'all';
  userFilter = 'all';

  settings: any = {};

  constructor(private taskService: TaskService, private dialogService: NbDialogService) {}

  ngOnInit() {
    this.role = localStorage.getItem('role') || '';
    this.user = JSON.parse( localStorage.getItem('user') || '' );
    this.loadUsers();
    this.loadTasks();
    this.initTableSettings();
  }

  loadUsers() {
    this.taskService.getUsers().subscribe(users => {
      this.usersList = users.map(usr => ({
        email: usr.email,
        name: usr.name,
      }));
      this.settings.columns.user.editor.config.list = users.map(usr => ({
        value: usr.email,
        title: usr.name,
      }));
      this.settings = { ...this.settings }; 
    });
  }

  loadTasks() {
    this.taskService.getTasks(this.role, this.user.email).subscribe(tasks => {
      this.source.load(tasks);
    });
  }

  onEditConfirm(event: any) {
    
    if (event.newData?.user?.email) {
      event.newData.user = {
        email: event.newData.user.email 
      };
    } else {
      event.newData.user = {
        email: event.newData.user 
      };
    }

    this.taskService.updateTask(event.newData).subscribe(() => {
      event.confirm.resolve(event.newData);
      this.loadTasks();
    });
  }

  filterTasks() {
    this.taskService.getTasksByFilters(this.statusFilter, this.userFilter).subscribe(tasks => {
      this.source.load(tasks);
    });
  }

  openCreateTaskDialog() {
    this.dialogService.open(CreateTaskComponent).onClose.subscribe(result => {
      if (result) {
        this.loadTasks();
      }
    });
  }

  initTableSettings() {
    this.settings = {
      actions: { add: false, edit: true, delete: false },
      edit: { 
        editButtonContent: '<i class="nb-edit"></i>',
        saveButtonContent: '<i class="nb-checkmark"></i>',
        cancelButtonContent: '<i class="nb-close"></i>',
        confirmSave: true,
      },
      columns: {
        id: { title: 'ID', type: 'number', editable: false },
        title: { title: 'TÍTULO', type: 'string', editable: this.role === 'Líder Técnico' },
        description: { title: 'DESCRIPCIÓN', type: 'string', editable: this.role === 'Líder Técnico' },
        priority: {
          title: 'PRIORIDAD',
          type: 'list',
          editable: this.role === 'Líder Técnico',
          editor: {
            type: 'list',
            config: {
              list: [
                { value: 'BAJA', title: 'Baja' },
                { value: 'MEDIA', title: 'Media' },
                { value: 'ALTA', title: 'Alta' },
              ],
            },
          },
        },
        user: {
          title: 'USUARIO ASIGNADO',
          type: 'list',
          valuePrepareFunction: (cell, row) => row.user?.name || 'Sin asignar',
          editable: this.role === 'Líder Técnico', 
          editor: {
            type: 'list',
            config: { list: [] }, 
          },
        },
        status: {
          title: 'ESTADO',
          type: 'list',
          editable: true,
          editor: {
            type: 'list',
            config: {
              list: [
                { value: 'Pendiente', title: 'Pendiente' },
                { value: 'En Progreso', title: 'En Progreso' },
                { value: 'Completada', title: 'Completada' },
              ],
            },
          },
        },
      },
    };
  }
}