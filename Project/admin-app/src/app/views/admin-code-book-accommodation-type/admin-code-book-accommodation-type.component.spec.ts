import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCodeBookAccommodationTypeComponent } from './admin-code-book-accommodation-type.component';

describe('AdminCodeBookComponent', () => {
  let component: AdminCodeBookAccommodationTypeComponent;
  let fixture: ComponentFixture<AdminCodeBookAccommodationTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminCodeBookAccommodationTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminCodeBookAccommodationTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
