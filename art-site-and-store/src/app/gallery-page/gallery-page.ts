import { Component } from '@angular/core';
import {CardContainerComponent} from './components/card-container-component/card-container-component';

@Component({
  selector: 'app-gallery-page',
  imports: [CardContainerComponent],
  templateUrl: './gallery-page.html',
  standalone: true,
  styleUrl: './gallery-page.css'
})
export class GalleryPage {

}
