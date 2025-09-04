import {Injectable} from '@angular/core';
import {AuthConfig, OAuthService} from 'angular-oauth2-oidc';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  // authConfig: AuthConfig = {
  //   issuer: 'http://localhost:8080',
  //   responseType: 'code',
  //   clientId: 'client',
  //   scope: 'openid',
  //   redirectUri: 'http://localhost:4200/authorized',
  //   showDebugInformation: true,
  //   requireHttps: false
  // }
  // apiUrl = "http://localhost:8080/login";
  // private http = inject(HttpClient);
  //
  // constructor(private oauthService: OAuthService) {
  //   this.oauthService.configure(this.authConfig);
  // }

  // login() {
  //   this.oauthService.initLoginFlow();
  // }
  //
  // submitForm(loginForm : LoginForm): Observable<string> {
  //   const headers = new Headers();
  //   console.log(loginForm.username + loginForm.password)
  //   return this.http.post<string>(this.apiUrl, loginForm);
  // }
}
