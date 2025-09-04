import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthorizedPage } from './authorized-page';

describe('AuthorizedPage', () => {
  let component: AuthorizedPage;
  let fixture: ComponentFixture<AuthorizedPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuthorizedPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuthorizedPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
