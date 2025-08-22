import {Component, inject} from '@angular/core';
import {HamburgerMenuService} from '../../services/hamburger-menu-service';

@Component({
    selector: 'app-hamburger-menu-component',
    imports: [],
    templateUrl: './hamburger-menu-component.html',
    standalone: true,
    styleUrl: './hamburger-menu-component.css'
})
export class HamburgerMenuComponent {
  hamburgerMenuService = inject(HamburgerMenuService);

  onClickMoveToPositionY(sectionName : string) {
    if (sectionName === "about-me-section") {
      this.hamburgerMenuService.setScrollToY("about-me-section");
    } else if (sectionName === "contact-section") {
      this.hamburgerMenuService.setScrollToY("contact-section");
    } else if (sectionName === "portfolio-section") {
      this.hamburgerMenuService.setScrollToY("portfolio-section");
    }
  }

}
