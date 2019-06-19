import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainHomepageComponent } from './main-homepage.component';

describe('MainHomepageComponent', () => {
  let component: MainHomepageComponent;
  let fixture: ComponentFixture<MainHomepageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainHomepageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainHomepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
