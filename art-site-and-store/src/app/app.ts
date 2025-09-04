import {Component, OnInit, signal} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {HeaderComponent} from './home-page/components/header-component/header-component';
import {CarouselComponent} from './home-page/components/carousel-component/carousel-component';
import {AboutMeComponent} from './home-page/components/about-me-component/about-me-component';
import {PortfolioComponent} from './home-page/components/portfolio-component/portfolio-component';
import {ContactComponent} from './home-page/components/contact-component/contact-component';
import {FooterComponent} from './home-page/components/footer-component/footer-component';
import {HamburgerMenuComponent} from './home-page/components/hamburger-menu-component/hamburger-menu-component';
import {AuthorizedPage} from './authorized-page/authorized-page';
import {OAuthService} from 'angular-oauth2-oidc';
import {environment} from '../environments/environment.development';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    HeaderComponent,
    CarouselComponent,
    AboutMeComponent,
    PortfolioComponent,
    ContactComponent,
    FooterComponent,
    HamburgerMenuComponent,
    AuthorizedPage
  ],
  templateUrl: './app.html',
  standalone: true,
  styleUrl: './app.css'
})
export class App implements OnInit {
  protected readonly title= signal('art-site-and-store');

  constructor(private oauthService: OAuthService) {} // Add constructor

  ngOnInit() {
    // Simple one-line configuration
    this.oauthService.configure(environment.authConfig);
  }
}
