import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {LoginForm} from '../../shared/models/login-form';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  apiUrl = "http://localhost:8080/login";
  private http = inject(HttpClient);

  submitForm(loginForm : LoginForm): Observable<string> {
    const headers = new Headers();
    console.log(loginForm.username + loginForm.password)
    return this.http.post<string>(this.apiUrl, loginForm);
  }
}
