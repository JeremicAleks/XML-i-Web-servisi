import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminRoleConfigurationComponent } from './admin-role-configuration.component';

describe('AdminRoleConfigurationComponent', () => {
  let component: AdminRoleConfigurationComponent;
  let fixture: ComponentFixture<AdminRoleConfigurationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminRoleConfigurationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminRoleConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
