import { Component } from '@angular/core';
import {CardComponent} from '../card-component/card-component';

@Component({
  selector: 'app-card-container-component',
  imports: [CardComponent],
  templateUrl: './card-container-component.html',
  standalone: true,
  styleUrl: './card-container-component.css'
})
export class CardContainerComponent {
  images = [
    {id : 1, img: ''},
    {id : 2, img: ''},
    {id : 3, img: ''},
    {id : 4, img: ''},
    {id : 5, img: ''},
    {id : 6, img: ''},
    {id : 7, img: ''},
    {id : 8, img: ''},
    {id : 9, img: ''},
    {id : 10, img: ''},
    {id : 11, img: ''},
    {id : 12, img: ''}
  ]
}
