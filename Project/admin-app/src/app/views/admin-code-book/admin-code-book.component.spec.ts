import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCodeBookComponent } from './admin-code-book.component';

describe('AdminCodeBookComponent', () => {
  let component: AdminCodeBookComponent;
  let fixture: ComponentFixture<AdminCodeBookComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminCodeBookComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminCodeBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
