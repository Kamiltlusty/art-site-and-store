import {AfterViewInit, Component, ElementRef, inject, ViewChild} from '@angular/core';
import {HamburgerMenuService} from '../shared/services/hamburger-menu-service';

@Component({
  selector: 'app-about-me-component',
  imports: [],
  templateUrl: './about-me-component.html',
  standalone: true,
  styleUrl: './about-me-component.css'
})
export class AboutMeComponent implements AfterViewInit {
  @ViewChild('amSection') amSection! : ElementRef;
  hamburgerMenuService = inject(HamburgerMenuService);

  ngAfterViewInit(): void {
    this.hamburgerMenuService.section$.subscribe(sectionName => {
      if (sectionName == "about-me-section") {
        let top = this.amSection.nativeElement.getBoundingClientRect().top;
        let headerHeight = this.hamburgerMenuService.headerHeight;
        let windowScrollY = window.scrollY;
        console.log("top: " + top + ", headerHeight: " + headerHeight + "windowScrollY: " + windowScrollY);
        let toMove = top + windowScrollY - headerHeight;
        window.scrollTo({top: toMove, behavior: "smooth"});
      }
    })
  }

}
