import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCodeBookAccommodationCategoryComponent } from './admin-code-book-accommodation-category.component';

describe('AdminCodeBookAccommodationCategoryComponent', () => {
  let component: AdminCodeBookAccommodationCategoryComponent;
  let fixture: ComponentFixture<AdminCodeBookAccommodationCategoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminCodeBookAccommodationCategoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminCodeBookAccommodationCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
