import {Component, signal} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {HeaderComponent} from './header-component/header-component';
import {CarouselComponent} from './carousel-component/carousel-component';
import {AboutMeComponent} from './about-me-component/about-me-component';
import {PortfolioComponent} from './portfolio-component/portfolio-component';
import {ContactComponent} from './contact-component/contact-component';
import {FooterComponent} from './footer-component/footer-component';
import {HamburgerMenuComponent} from './hamburger-menu-component/hamburger-menu-component';

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
    HamburgerMenuComponent
  ],
  templateUrl: './app.html',
  standalone: true,
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('art-site-and-store');
}
