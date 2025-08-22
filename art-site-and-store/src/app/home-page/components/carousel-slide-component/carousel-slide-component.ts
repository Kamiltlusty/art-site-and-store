import {AfterViewInit, Component, ElementRef, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {BehaviorSubject, filter, take} from 'rxjs';
import {AsyncPipe} from '@angular/common';

@Component({
  selector: 'app-carousel-slide-component',
  imports: [
    AsyncPipe
  ],
  templateUrl: './carousel-slide-component.html',
  standalone: true,
  styleUrl: './carousel-slide-component.css'
})
export class CarouselSlideComponent implements AfterViewInit {
  @Input() transform!: string;
  @Input() idx!: number;
  @Output() widthCollected = new EventEmitter<number>();
  @ViewChild('slide') slide!: ElementRef<HTMLDivElement>
  @ViewChild('image') image!: ElementRef<HTMLImageElement>
  slideWidth = 0;
  @Input() selectedFile$!: BehaviorSubject<File | null>;

  ngAfterViewInit() {
    this.slideWidth = this.slide.nativeElement.offsetWidth;
    this.widthCollected.emit(this.slideWidth);
    console.log('Slide width: ', this.slideWidth);
    this.selectedFile$
      .pipe(take(1))
      .subscribe(() => {
        this.addOnClickEventOnSlides()
      });
  }

  handleAddImageToSlide() {
    console.log("jestem tu")
    let objectUrl = URL.createObjectURL(<File>this.selectedFile$.getValue());
    this.image.nativeElement.src = objectUrl.toString();
    console.log("url: " + objectUrl.toString())
    this.selectedFile$.next(null);
  }

  addOnClickEventOnSlides() {
    console.log("wszedłem")
    this.slide.nativeElement.addEventListener("click", () => {
      this.handleAddImageToSlide()
    })
  }
}
