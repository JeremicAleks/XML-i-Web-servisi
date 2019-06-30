import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { UserLoginDTO } from '../../models/user-login-dto';
import { AuthentificationService } from '../../services/authentification.service';
import { Reservation } from 'src/app/models/reservation';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {ReservationService} from '../../services/reservation.service';
import {Router} from '@angular/router';

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

  constructor(private authService: AuthentificationService, private modalService: NgbModal, private reservationService: ReservationService,private router: Router) { }

  ngOnInit() {
    this.isLogin = this.authService.checkSessionUser();
    if (this.isLogin) {
      this.userLoginDTO = this.authService.getSessionUser();
    }
    this.getAllReservations()
  }

  sendMessage() {
    console.log('Send Message to Agent clicked...');

  }

  openSendMessageModal(id) {
    console.log('SendMessageModal has just opened...');
    this.router.navigate(['reservation/'+id+'/conversation']);

  }

  getAllReservations() {
    this.reservationService.getAllReservations().subscribe(
      data => {
        this.allUserReservations = data;

      },
      error => {
        alert('getAllReservations error');
      }
    )
  }
}
