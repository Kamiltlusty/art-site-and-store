import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {AboutMeComponent} from '../about-me-component/about-me-component';
import {CarouselComponent} from '../carousel-component/carousel-component';
import {ContactComponent} from '../contact-component/contact-component';
import {FooterComponent} from '../footer-component/footer-component';
import {HamburgerMenuComponent} from '../hamburger-menu-component/hamburger-menu-component';
import {HeaderComponent} from '../header-component/header-component';
import {PortfolioComponent} from '../portfolio-component/portfolio-component';

@Component({
  selector: 'app-home-page',
  imports: [RouterOutlet, AboutMeComponent, CarouselComponent, ContactComponent, FooterComponent, HamburgerMenuComponent, HeaderComponent, PortfolioComponent],
  templateUrl: './home-page.html',
  standalone: true,
  styleUrl: './home-page.css'
})
export class HomePage {

}
