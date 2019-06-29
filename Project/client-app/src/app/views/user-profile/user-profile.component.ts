import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { UserLoginDTO } from '../../models/user-login-dto';
import { AuthentificationService } from '../../services/authentification.service';
import { Reservation } from 'src/app/models/reservation';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  userLoginDTO: UserLoginDTO;
  isLogin: boolean;

  allUserReservations: Array<Reservation>;
  reservationForMessage: Reservation;

  sendMessageForm = new FormGroup({
    messageText: new FormControl('', [Validators.required])
  });

  constructor(private authService: AuthentificationService, private modalService: NgbModal) { }

  ngOnInit() {
    this.isLogin = this.authService.checkSessionUser();
    if (this.isLogin) {
      this.userLoginDTO = this.authService.getSessionUser();
    }
  }

  sendMessage() {
    console.log('Send Message to Agent clicked...');
  }

  openSendMessageModal(sendMessageModal) {
    console.log('SendMessageModal has just opened...');
    //this.reservationForMessage = r;
    this.modalService.open(sendMessageModal, {
      windowClass: 'dark-modal',
      centered: true,
      size: 'lg'
    });
  }

}
