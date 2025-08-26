import {DestroyRef, inject, Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class ImageService implements OnInit {
    private apiUrl = "http://localhost:8080";
    private http = inject(HttpClient);
    private destroyRef = inject(DestroyRef);

    ngOnInit(): void {
        const subscription = this.http.get<Blob>(this.apiUrl).subscribe({
            next: (resData) => {

            }
        });

        this.destroyRef.onDestroy(() => {
            subscription.unsubscribe()
        });
    }

}
