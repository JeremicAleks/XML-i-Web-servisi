import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainNavibarComponent } from './main-navibar.component';

describe('MainNavibarComponent', () => {
  let component: MainNavibarComponent;
  let fixture: ComponentFixture<MainNavibarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainNavibarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainNavibarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
