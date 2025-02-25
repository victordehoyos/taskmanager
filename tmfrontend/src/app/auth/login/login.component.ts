import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NbAuthService, NbLoginComponent } from '@nebular/auth';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'ngx-login',
  templateUrl: './login.component.html',
})
export class NgxLoginComponent implements OnInit {
  user = { email: ''};
  errors: string[] = [];
  messages: string[] = [];
  submitted: boolean = false;
  showMessages = { success: false, error: false };

  constructor(private authService: AuthService, private router: Router) {}

  login(): void {
    this.submitted = true;
    this.messages = [];
    this.errors = [];

    if(!this.user.email) {
      this.errors.push('El Correo es obligatorio');
      this.showMessages.error = true;
      this.submitted = false;
      return;
    }

    this.authService.login(this.user.email ).subscribe(
      (result) => {
        if (result.email && result.name) {
          localStorage.setItem('user', JSON.stringify(result));
          localStorage.setItem('role', result.roleName);
          this.messages.push(`Bienvenido, ${result.name}`);
          this.showMessages.success = true;
          this.router.navigate(['/pages/tasks']).then(() => {
            window.location.reload();
          });
        } else {
          this.showMessages.error = true;
          this.errors = result.getErrors();
        }
        this.submitted = false;
      },
      (error) => {
        this.errors.push('Error al autenticar');
        this.showMessages.error = true;
        this.submitted = false;
      }
    );
  }

  ngOnInit() {
    this.authService.logout();
  }
}