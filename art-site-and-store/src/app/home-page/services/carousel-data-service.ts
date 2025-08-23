import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarouselDataService {
  amountOfImages = 0;
  isDeleteModeOn$ :BehaviorSubject<boolean> = new BehaviorSubject(false);
}
