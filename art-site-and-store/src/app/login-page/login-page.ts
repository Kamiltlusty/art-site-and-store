import {Component, inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {LoginService} from './services/login-service';
import {AuthConfig, OAuthService} from 'angular-oauth2-oidc';
import {environment} from '../../environments/environment.development';

@Component({
  selector: 'app-login-page',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './login-page.html',
  standalone: true,
  styleUrl: './login-page.css'
})
export class LoginPage implements OnInit {
  constructor(private oauthService: OAuthService) {
    this.oauthService.configure(environment.authConfig);
  }

  ngOnInit() {
    this.oauthService.loadDiscoveryDocumentAndTryLogin().then(() => {
      if (!this.oauthService.hasValidAccessToken()) {
        // Brak tokena → redirect do Authorization Server
        this.oauthService.initLoginFlow();
      }
    });
  }

  // apiUrl = "http://localhost:8080/login";
  // private http = inject(HttpClient);
  // loginService = inject(LoginService);
  //
  // applyForm = new FormGroup({
  //   username: new FormControl('', {nonNullable: true}),
  //   password: new FormControl('', {nonNullable: true})
  // })

  // submitForm() {
  //   if (this.applyForm.valid) {
  //     // const loginForm: LoginForm = this.applyForm.getRawValue();
  //     // this.loginService.submitForm(loginForm).subscribe({
  //     //     next: response => {
  //     //
  //     //     },
  //     //     error: err => {
  //     //       console.log(err);
  //     //     }
  //     //   })
  //
  //     this.loginService.login();
  //   }
  // }
}
