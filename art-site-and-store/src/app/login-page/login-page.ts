import {Component, inject} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {LoginService} from './services/login-service';
import {LoginForm} from '../shared/models/login-form';

@Component({
  selector: 'app-login-page',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './login-page.html',
  standalone: true,
  styleUrl: './login-page.css'
})
export class LoginPage {
  loginService = inject(LoginService);

  applyForm = new FormGroup({
    username: new FormControl('', {nonNullable: true}),
    password: new FormControl('', {nonNullable: true})
  })

  submitForm() {
    if (this.applyForm.valid) {
      const loginForm: LoginForm = this.applyForm.getRawValue();
      this.loginService.submitForm(loginForm).subscribe({
          next: response => {

          },
          error: err => {
            console.log(err);
          }
        })
    }
  }

}
