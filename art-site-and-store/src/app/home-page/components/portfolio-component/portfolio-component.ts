import {AfterViewInit, Component, ElementRef, inject, OnInit, ViewChild} from '@angular/core';
import {HamburgerMenuService} from '../../services/hamburger-menu-service';
import {RouterLink} from '@angular/router';
import {ImageService} from '../../../shared/services/image-service';
import {Image} from '../../../shared/models/image';

@Component({
  selector: 'app-portfolio-component',
  imports: [
    RouterLink
  ],
  templateUrl: './portfolio-component.html',
  standalone: true,
  styleUrl: './portfolio-component.css'
})
export class PortfolioComponent implements AfterViewInit, OnInit {
  @ViewChild('portfolioSection') portfolioSection! : ElementRef;
  @ViewChild('image') image! : ElementRef;
  hamburgerMenuService = inject(HamburgerMenuService);
  imageService = inject(ImageService);
  images!: Image[];

  ngOnInit() {
    this.imageService.getCarouselData().subscribe({
      next: places => {
        this.images = places.find(p => p.name === "portfolio")?.images ?? [];
        this.image.nativeElement.src = this.imageService.apiUrl + "/" + this.images[0].imageId;
      },
      error: err => console.log(err)
    })
  }

  ngAfterViewInit(): void {
    this.hamburgerMenuService.section$.subscribe(sectionName => {
      if (sectionName == "portfolio-section") {
        let top = this.portfolioSection.nativeElement.getBoundingClientRect().top;
        let headerHeight = this.hamburgerMenuService.headerHeight;
        let windowScrollY = window.scrollY;
        console.log("top: " + top + ", headerHeight: " + headerHeight + "windowScrollY: " + windowScrollY);
        let toMove = top + windowScrollY - headerHeight;
        window.scrollTo({top: toMove, behavior: "smooth"});
      }
    })
  }
}
