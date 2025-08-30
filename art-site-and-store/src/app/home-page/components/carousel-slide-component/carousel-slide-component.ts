import {
  AfterViewInit,
  Component,
  ElementRef,
  EventEmitter,
  inject,
  Input,
  OnInit,
  Output,
  ViewChild
} from '@angular/core';
import {BehaviorSubject, take} from 'rxjs';
import {AsyncPipe} from '@angular/common';
import {CarouselDataService} from '../../services/carousel-data-service';
import {ImageService} from '../../../shared/services/image-service';
import {Image} from '../../../shared/models/image';

@Component({
  selector: 'app-carousel-slide-component',
  imports: [
    AsyncPipe
  ],
  templateUrl: './carousel-slide-component.html',
  standalone: true,
  styleUrl: './carousel-slide-component.css'
})
export class CarouselSlideComponent implements AfterViewInit, OnInit {
  @Input() transform!: string;
  @Input() img!: Image;
  @Output() widthCollected = new EventEmitter<number>();
  @ViewChild('slide') slide!: ElementRef<HTMLDivElement>
  @ViewChild('image') image!: ElementRef<HTMLImageElement>
  slideWidth = 0;
  @Input() selectedFile$!: BehaviorSubject<File | null>;
  carouselDataService = inject(CarouselDataService);
  imageService = inject(ImageService);
  private addImageHandler = () => this.addImageToSlide();
  private removeImageHandler = () => this.removeImageFromSlide();

  ngOnInit() {
    this.carouselDataService.isDeleteModeOn$.subscribe(v => {
      if (v) {
        this.addEventOnRemoveModeOn()
      }
    })
  }

  ngAfterViewInit() {
    this.slideWidth = this.slide.nativeElement.offsetWidth;
    this.widthCollected.emit(this.slideWidth);
    console.log('Slide width: ', this.slideWidth);
    this.selectedFile$
      .subscribe(() => {
        this.addOnClickEventOnSlides()
      });
    this.image.nativeElement.src = this.imageService.apiUrl + "/" + this.img.imageId;
  }

  addImageToSlide() {
    console.log("jestem tu")
    let objectUrl = URL.createObjectURL(<File>this.selectedFile$.getValue());
    this.image.nativeElement.src = objectUrl.toString();
    console.log("wszedłem tu kolejny raz")
    this.updateAmountOfImages()
    console.log("url: " + objectUrl.toString())
    this.selectedFile$.next(null);
  }

  removeImageFromSlide() {
    console.log("obecny tu")
    if (this.image.nativeElement.src !== "" && this.image.nativeElement.src != window.location.href) {
      console.log("Obecny: " + this.image.nativeElement.src);
      this.image.nativeElement.src = "";
      this.carouselDataService.amountOfImages--;
      this.carouselDataService.isDeleteModeOn$.next(false);
    } else {
      console.log("Nieobecny");
    }
  }

  updateAmountOfImages() {
    this.carouselDataService.amountOfImages++
    console.log("updated: " + this.carouselDataService);
  }

  addEventOnRemoveModeOn() {
    console.log("Jestem w addEventOnRemoveModeOn");
    if (this.carouselDataService.isDeleteModeOn$.getValue()) {
      console.log("RemoveModeOn" + this.carouselDataService.isDeleteModeOn$.getValue());
      this.slide.nativeElement.removeEventListener("click", this.addImageHandler)
      this.slide.nativeElement.addEventListener("click", this.removeImageHandler);
    }
  }

  addOnClickEventOnSlides() {
    console.log("wszedłem")
    if (!this.carouselDataService.isDeleteModeOn$.getValue()) {
      this.slide.nativeElement.removeEventListener("click", this.removeImageHandler);
      this.slide.nativeElement.addEventListener("click", this.addImageHandler);
    }
  }
}
