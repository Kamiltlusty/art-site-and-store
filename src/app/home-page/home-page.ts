import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {AboutMeComponent} from './components/about-me-component/about-me-component';
import {CarouselComponent} from './components/carousel-component/carousel-component';
import {ContactComponent} from './components/contact-component/contact-component';
import {FooterComponent} from './components/footer-component/footer-component';
import {HamburgerMenuComponent} from './components/hamburger-menu-component/hamburger-menu-component';
import {HeaderComponent} from './components/header-component/header-component';
import {PortfolioComponent} from './components/portfolio-component/portfolio-component';

@Component({
  selector: 'app-home-page',
  imports: [RouterOutlet, AboutMeComponent, CarouselComponent, ContactComponent, FooterComponent, HamburgerMenuComponent, HeaderComponent, PortfolioComponent],
  templateUrl: './home-page.html',
  standalone: true,
  styleUrl: './home-page.css'
})
export class HomePage {

}
