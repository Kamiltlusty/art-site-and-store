import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {Place} from '../models/place';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  apiUrl = "http://localhost:8080/carousel";
  private http = inject(HttpClient);

  isDeleteModeOn$ :BehaviorSubject<boolean> = new BehaviorSubject(false);

  getCarouselData(): Observable<Place[]> {
    return this.http.get<Place[]>(this.apiUrl);
  }

  deleteImage(imageUUID : string) : Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/manage/${imageUUID}`);
  }
}
