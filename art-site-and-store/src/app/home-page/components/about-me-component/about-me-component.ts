import {AfterViewInit, Component, ElementRef, inject, OnInit, ViewChild} from '@angular/core';
import {HamburgerMenuService} from '../../services/hamburger-menu-service';
import {ImageService} from '../../../shared/services/image-service';
import {Image} from '../../../shared/models/image';

@Component({
  selector: 'app-about-me-component',
  imports: [],
  templateUrl: './about-me-component.html',
  standalone: true,
  styleUrl: './about-me-component.css'
})
export class AboutMeComponent implements AfterViewInit, OnInit {
  @ViewChild('amSection') amSection! : ElementRef;
  @ViewChild('image') image! : ElementRef;
  hamburgerMenuService = inject(HamburgerMenuService);
  imageService = inject(ImageService);
  images!: Image[];

  ngOnInit() {
    this.imageService.getCarouselData().subscribe({
      next: places => {
        this.images = places.find(p => p.name === "about_me")?.images ?? [];
        console.log(this.imageService.apiUrl + "/" + this.images[0].imageId);
        this.image.nativeElement.src = this.imageService.apiUrl + "/" + this.images[0].imageId;
      },
      error: err => console.log(err)
    });
  }

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
