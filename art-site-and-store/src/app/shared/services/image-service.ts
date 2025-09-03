import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Place} from '../models/place';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  apiUrl = "http://localhost:8080/carousel";
  private http = inject(HttpClient);

  getCarouselData(): Observable<Place[]> {
    return this.http.get<Place[]>(this.apiUrl);
  }
}
