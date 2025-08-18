import {AfterViewInit, Component, ElementRef, ViewChild} from '@angular/core';
import {CarouselSlideComponent} from '../carousel-slide-component/carousel-slide-component';

@Component({
  selector: 'app-carousel-component',
  imports: [CarouselSlideComponent],
  templateUrl: './carousel-component.html',
  standalone: true,
  styleUrl: './carousel-component.css'
})
export class CarouselComponent implements AfterViewInit {
  @ViewChild('imgContainer') imgContainer! : ElementRef;
  slides = [
    {id : 1, img: ''},
    {id : 2, img: ''},
    {id : 3, img: ''},
    {id : 4, img: ''},
    {id : 5, img: ''},
    {id : 6, img: ''}
  ]
  offset = 0;
  transform = 'translate(0px)';
  slideWidth!: number;
  slideGap!: number;

  ngAfterViewInit(): void {
    this.slideGap = parseFloat(getComputedStyle(this.imgContainer.nativeElement).gap);
  }

  moveSlidesRight() : void {
    console.log("moving slides to the right");
    console.log(this.slideWidth + this.slideGap)
    console.log('Przed:', this.slides.map(s => s.id));

    const last = this.slides.pop();
    if (last) this.slides.unshift(last);

    console.log('Po:', this.slides.map(s => s.id));
  }

  moveSlidesLeft() : void {
    console.log("moving slides to the left");

    const first = this.slides.shift();
    if (first) this.slides.push(first);
  }

  getSlideWidth(slideWidth: number) {
    this.slideWidth = slideWidth;
  }
}
