import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HamburgerMenuService {
  isOpen = false;
  private sectionToMoveTo = new BehaviorSubject<string>("");
  section$ = this.sectionToMoveTo.asObservable();
  headerHeight! : number;

  setScrollToY(newSection : string) {
    this.sectionToMoveTo.next(newSection);
  }

  toggleMenu() {
    this.isOpen = !this.isOpen
    if (this.isOpen) {
      document.body.style.overflow = 'hidden';
    } else {
      document.body.style.overflow = 'auto';
    }
  }
}
