import {AfterViewInit, Component, ElementRef, inject, ViewChild} from '@angular/core';
import {HamburgerMenuService} from '../../services/hamburger-menu-service';

@Component({
  selector: 'app-header-component',
  imports: [],
  templateUrl: './header-component.html',
  standalone: true,
  styleUrl: './header-component.css'
})
export class HeaderComponent implements AfterViewInit {
  @ViewChild('header') header! : ElementRef;
  hamburgerMenuService = inject(HamburgerMenuService);

  ngAfterViewInit(): void {
    this.hamburgerMenuService.headerHeight = this.header.nativeElement.offsetHeight;
  }

  onHamburgerClick() {
    this.hamburgerMenuService.toggleMenu();
  }

}
