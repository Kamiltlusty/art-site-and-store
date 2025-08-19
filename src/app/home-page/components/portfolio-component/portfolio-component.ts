import {AfterViewInit, Component, ElementRef, inject, ViewChild} from '@angular/core';
import {HamburgerMenuService} from '../../services/hamburger-menu-service';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-portfolio-component',
  imports: [
    RouterLink
  ],
  templateUrl: './portfolio-component.html',
  standalone: true,
  styleUrl: './portfolio-component.css'
})
export class PortfolioComponent implements AfterViewInit{
  @ViewChild('portfolioSection') portfolioSection! : ElementRef;
  hamburgerMenuService = inject(HamburgerMenuService);

  ngAfterViewInit(): void {
    this.hamburgerMenuService.section$.subscribe(sectionName => {
      if (sectionName == "portfolio-section") {
        let top = this.portfolioSection.nativeElement.getBoundingClientRect().top;
        let headerHeight = this.hamburgerMenuService.headerHeight;
        let windowScrollY = window.scrollY;
        console.log("top: " + top + ", headerHeight: " + headerHeight + "windowScrollY: " + windowScrollY);
        let toMove = top + windowScrollY - headerHeight;
        window.scrollTo({top: toMove, behavior: "smooth"});
      }
    })
  }
}
