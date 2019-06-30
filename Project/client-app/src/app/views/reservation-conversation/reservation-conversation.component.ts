import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ClientSendMessageDTO } from 'src/app/models/client-send-message-dto';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-reservation-conversation',
  templateUrl: './reservation-conversation.component.html',
  styleUrls: ['./reservation-conversation.component.css']
})
export class ReservationConversationComponent implements OnInit {

  reservationId: number;
  showMessageForUserDTO: any;

  sendMessageForm = new FormGroup({
    messageText: new FormControl('', [Validators.required])
  });

  constructor(private route: ActivatedRoute, private modalService: NgbModal, private reservationService: ReservationService) { }

  ngOnInit() {
    this.reservationId = +this.route.snapshot.paramMap.get('id');
    alert(this.reservationId);
    this.getMessagesForReservation();
  }

  sendMessageClicked() {
    console.log('Send Message to Agent clicked...');
    const messageToSend = new ClientSendMessageDTO();
    messageToSend.idReservation = this.reservationId;
    messageToSend.message = this.sendMessageForm.get('messageText').value;
    console.log('Message to send: ' + messageToSend.message);

    this.reservationService.sendMessage(messageToSend).subscribe(
      data => {
        this.showMessageForUserDTO = data;
        alert('poruka poslata');
        this.getMessagesForReservation();
      },
      error => {
        alert('duckanje - poruka nije poslata');
        alert(error.message);
      });
  }

  OpenSendMessageModal(sendMessageModal) {
    console.log('SendMessageModal has just opened...');
    this.modalService.open(sendMessageModal, {
      windowClass: 'dark-modal',
      centered: true,
      size: 'lg'
    });
  }

  getMessagesForReservation() {
    console.log('getMessagesForReservation');
    this.reservationService.getMessagesForReservation(this.reservationId).subscribe(
      data => {
        this.showMessageForUserDTO = data;
      },
      error => {
        alert(error.message);
      }
    )
  }

}
