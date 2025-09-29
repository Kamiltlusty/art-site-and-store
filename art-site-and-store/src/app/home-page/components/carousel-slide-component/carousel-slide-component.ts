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
  @Input() imageData!: Image;
  @Output() widthCollected = new EventEmitter<number>();
  @ViewChild('slide') slide!: ElementRef<HTMLDivElement>
  @ViewChild('image') imageRef!: ElementRef<HTMLImageElement>
  slideWidth = 0;
  @Input() selectedFile$!: BehaviorSubject<File | null>;
  imageService = inject(ImageService);
  private addImageHandler = () => this.addImageToSlide();
  private removeImageHandler = () => this.removeImageFromSlide();
  @Input() amountOfImages!: BehaviorSubject<number>;

  ngOnInit() {
    this.imageService.isDeleteModeOn$.subscribe(v => {
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
    this.imageRef.nativeElement.src = this.imageService.apiUrl + "/" + this.imageData.imageId;
  }

  addImageToSlide() {
    console.log("jestem tu")
    let objectUrl = URL.createObjectURL(<File>this.selectedFile$.getValue());
    this.imageRef.nativeElement.src = objectUrl.toString();
    console.log("wszedłem tu kolejny raz")
    const currentAmountOfImages = this.amountOfImages.value;
    this.amountOfImages.next(currentAmountOfImages + 1);

    console.log("url: " + objectUrl.toString())
    this.selectedFile$.next(null);
  }

  removeImageFromSlide() {
    console.log("obecny tu")
    if (this.imageRef.nativeElement.src !== "" && this.imageRef.nativeElement.src != window.location.href) {
      console.log("Obecny: " + this.imageRef.nativeElement.src);
      this.imageRef.nativeElement.src = "";

      this.imageService.deleteImage(this.imageData.imageId).subscribe({
          next: response => {
            console.log(response);
          },
          error: err => {
            console.log("Delete failed: ", err)
          }
        }
      );

      const currentAmountOfImages = this.amountOfImages.value;
      this.amountOfImages.next(currentAmountOfImages - 1);

      this.imageService.isDeleteModeOn$.next(false);
    } else {
      console.log("Nieobecny");
    }
  }


  addEventOnRemoveModeOn() {
    console.log("Jestem w addEventOnRemoveModeOn");
    if (this.imageService.isDeleteModeOn$.getValue()) {
      console.log("RemoveModeOn" + this.imageService.isDeleteModeOn$.getValue());
      this.slide.nativeElement.removeEventListener("click", this.addImageHandler)
      this.slide.nativeElement.addEventListener("click", this.removeImageHandler);
    }
  }

  addOnClickEventOnSlides() {
    console.log("wszedłem")
    if (!this.imageService.isDeleteModeOn$.getValue()) {
      this.slide.nativeElement.removeEventListener("click", this.removeImageHandler);
      this.slide.nativeElement.addEventListener("click", this.addImageHandler);
    }
  }
}
