import { Component, Inject, OnInit } from '@angular/core';
import { NbDialogRef, NB_DIALOG_CONFIG } from '@nebular/theme';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'ngx-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent implements OnInit {
  user: any = { name: '', email: '', roleId: null };
  roles: any[] = [];

  constructor(
    protected dialogRef: NbDialogRef<CreateUserComponent>,
    private userService: UserService
  ) {}

  ngOnInit() {
    this.userService.getRoles().subscribe(roles => {
      this.roles = roles;
    });
  }

  save() {
    const newUser = { ...this.user, role: { id: this.user.roleId } };
    this.userService.createUser(newUser).subscribe(() => {
      this.dialogRef.close(true);
    });
  }

  close() {
    this.dialogRef.close(false);
  }
}
