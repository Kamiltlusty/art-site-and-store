import { Component } from '@angular/core';
import {CarouselSlideComponent} from '../carousel-slide-component/carousel-slide-component';

@Component({
  selector: 'app-carousel-component',
  imports: [CarouselSlideComponent],
  templateUrl: './carousel-component.html',
  standalone: true,
  styleUrl: './carousel-component.css'
})
export class CarouselComponent {
  slides = new Array(6).fill(null);
  offset = 0;
  transform = 'translate(0px)';

  moveSlidesRight() : void {
    console.log("moving slides to the right");

    this.offset += 450;
    this.transform = `translate(${this.offset}px)`
  }

  moveSlidesLeft() : void {
    console.log("moving slides to the left");

    this.offset -= 450;
    this.transform = `translate(${this.offset}px)`
  }

  getTransform() : string {
    return this.transform;
  }

  getSlideWidth(slideWidth: number) {
    console.log("Slide width: " + slideWidth);
  }
}
