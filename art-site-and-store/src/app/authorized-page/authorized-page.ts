import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../shared/services/auth-service';
import {OAuthService} from 'angular-oauth2-oidc';

@Component({
  selector: 'app-authorized-page',
  imports: [],
  templateUrl: './authorized-page.html',
  standalone: true,
  styleUrl: './authorized-page.css'
})
export class AuthorizedPage implements OnInit {
  constructor(private oauthService: OAuthService, private router: Router) {}

  ngOnInit() {
    // Sprawdzenie, czy wróciliśmy z Authorization Server i czy mamy code
    this.oauthService.loadDiscoveryDocumentAndTryLogin().then(() => {
      if (this.oauthService.hasValidAccessToken()) {
        console.log('Token:', this.oauthService.getAccessToken());
        // Przekierowanie na stronę główną
        this.router.navigate(['/']);
      } else {
        // Jeśli coś poszło nie tak, wracamy do login-page
        this.router.navigate(['/login']);
      }
    });
  }

}
