import {AfterViewInit, Component, ElementRef, EventEmitter, Input, Output, ViewChild} from '@angular/core';

@Component({
  selector: 'app-carousel-slide-component',
  imports: [],
  templateUrl: './carousel-slide-component.html',
  standalone: true,
  styleUrl: './carousel-slide-component.css'
})
export class CarouselSlideComponent implements AfterViewInit {
  @Input() transform!: string;
  @Output() widthCollected = new EventEmitter<number>();
  @ViewChild('slide') slide!: ElementRef<HTMLDivElement>

  slideWidth = 0;

  ngAfterViewInit() {
    this.slideWidth = this.slide.nativeElement.offsetWidth;
    this.widthCollected.emit(this.slideWidth);
    console.log('Slide width: ', this.slideWidth);
  }
}
