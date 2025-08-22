import {Routes} from '@angular/router';
import {HomePage} from './home-page/home-page';
import {GalleryPage} from './gallery-page/gallery-page';
import {ItemPage} from './item-page/item-page';

export const routes: Routes = [
  {
    path: '',
    component: HomePage,
    title: 'Home Page'
  },
  {
    path: 'gallery',
    component: GalleryPage,
    title: 'Gallery Page'
  },
  {
    path: 'item',
    component: ItemPage,
    title: 'Item Page'
  }
];
