import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCommentConfigComponent } from './admin-comment-config.component';

describe('AdminCommentConfigComponent', () => {
  let component: AdminCommentConfigComponent;
  let fixture: ComponentFixture<AdminCommentConfigComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminCommentConfigComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminCommentConfigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
