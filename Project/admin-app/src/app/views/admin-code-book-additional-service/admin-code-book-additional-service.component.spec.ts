import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCodeBookAdditionalServiceComponent } from './admin-code-book-additional-service.component';

describe('AdminCodeBookAdditionalServiceComponent', () => {
  let component: AdminCodeBookAdditionalServiceComponent;
  let fixture: ComponentFixture<AdminCodeBookAdditionalServiceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminCodeBookAdditionalServiceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminCodeBookAdditionalServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
