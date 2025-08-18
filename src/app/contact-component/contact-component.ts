import {AfterViewInit, Component, ElementRef, inject, ViewChild} from '@angular/core';
import {HamburgerMenuService} from '../shared/services/hamburger-menu-service';

@Component({
    selector: 'app-contact-component',
    imports: [],
    templateUrl: './contact-component.html',
    standalone: true,
    styleUrl: './contact-component.css'
})
export class ContactComponent implements AfterViewInit {
  @ViewChild('contactSection') contactSection! : ElementRef;
  hamburgerMenuService = inject(HamburgerMenuService);

  ngAfterViewInit(): void {
    this.hamburgerMenuService.section$.subscribe(sectionName => {
      if (sectionName == "contact-section") {
        let top = this.contactSection.nativeElement.getBoundingClientRect().top;
        let headerHeight = this.hamburgerMenuService.headerHeight;
        let windowScrollY = window.scrollY;
        console.log("top: " + top + ", headerHeight: " + headerHeight);
        let toMove = top + windowScrollY - headerHeight;
        window.scrollTo({top: toMove, behavior: "smooth"});
      }
    })
  }

}
