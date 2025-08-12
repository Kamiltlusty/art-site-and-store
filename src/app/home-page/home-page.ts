import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-home-page',
  imports: [RouterOutlet],
  templateUrl: './home-page.html',
  standalone: true,
  styleUrl: './home-page.css'
})
export class HomePage {

}
