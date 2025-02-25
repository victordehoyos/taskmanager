import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { LocalDataSource } from 'ng2-smart-table';
import { CreateUserComponent } from './create-user/create-user.component';
import { NbDialogService } from '@nebular/theme';
import { filter } from 'rxjs-compat/operator/filter';


@Component({
  selector: 'ngx-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  source: LocalDataSource = new LocalDataSource();
  rolesList = [];
  selectedRoleId = 'all';

  settings = {
    actions: {
      add: false,
      edit: false,
      delete: false
    }, 
    columns: {
      name: {
        title: 'NOMBRE',
        type: 'string',
        filter: false,
      },
      email: {
        title: 'EMAIL',
        type: 'string',
        filter: false,
      },
      role: {
        title: 'ROL',
        type: 'string',
        valuePrepareFunction: (cell, row) => row.role.name,
        filter: false,
      },
    },
  };

  constructor(private userService: UserService, private dialogService: NbDialogService) {}

  ngOnInit(): void {
      this.loadUsers();
      this.loadRoles();
  }

  loadUsers() {
    this.userService.getUsers().subscribe(users => {
      this.source.load(users);
    });
  }

  loadRoles() {
    this.userService.getRoles().subscribe(roles => {
      this.rolesList = roles.map(role => ({
        value: role.id,
        title: role.name,
      }));

      this.settings = { ...this.settings }; 
    });
  }

  filterByRole(roleId: string) {
    this.selectedRoleId = roleId;
    if (roleId === 'all') {
      this.loadUsers();
    } else {
      this.userService.getRole(roleId).subscribe(users => {
        this.source.load(users);
      });
    }
  }

  openCreateUserDialog() {
    this.dialogService.open(CreateUserComponent)
      .onClose.subscribe(result => {
        if (result) {
          this.filterByRole(this.selectedRoleId);
        }
      });
  }

}
