import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-card-component',
  imports: [
    RouterLink
  ],
  templateUrl: './card-component.html',
  standalone: true,
  styleUrl: './card-component.css'
})
export class CardComponent {

}
