import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationConversationComponent } from './reservation-conversation.component';

describe('ReservationConversationComponent', () => {
  let component: ReservationConversationComponent;
  let fixture: ComponentFixture<ReservationConversationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReservationConversationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationConversationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
