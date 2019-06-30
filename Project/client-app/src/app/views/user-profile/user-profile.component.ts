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
  i:number;
  j:number;
  allUserReservations: Array<Reservation>;
  expiredReservations: Array<Reservation>;
  pendingReservations: Array<Reservation>;
  reservationForMessage: Reservation;
  time: Date;

  sendMessageForm = new FormGroup({
    messageText: new FormControl('', [Validators.required])
  });

  constructor(private authService: AuthentificationService, private modalService: NgbModal, private reservationService: ReservationService,private router: Router) { }

  ngOnInit() {
    this.isLogin = this.authService.checkSessionUser();
    if (this.isLogin) {
      this.userLoginDTO = this.authService.getSessionUser();
    }
    this.pendingReservations = [];
    this.expiredReservations = [];
    this.i=0;
    this.j=0;
    this.getAllReservations();
    console.log(this.expiredReservations);
    console.log(this.pendingReservations);
  }

  sendMessage() {
    console.log('Send Message to Agent clicked...');

  }

  openSendMessageModal(id) {
    console.log('SendMessageModal has just opened...');
    console.log(id);
    this.router.navigate(['reservation/'+id+'/conversation']);

  }

  cancelReservation(id) {
  console.log('Resevation canceled...')
  console.log(id);

      this.reservationService.cancelReservation(id).subscribe(
        data => {
          this.reservationForMessage = data;
          console.log(this.reservationForMessage)
        },
        error => {
          alert('getAllRooms error');
        }
      )

}

  getAllReservations() {
    this.reservationService.getAllReservations().subscribe(
      data => {
        this.allUserReservations = data;
        this.time = new Date();
        console.log(this.time)
        for(this.i=0; this.i<this.allUserReservations.length; this.i++){
          if(this.allUserReservations[this.i].checkOut <= this.time){
            this.expiredReservations[this.j] = this.allUserReservations[this.i]
            this.j++;
          }else{
            this.pendingReservations[this.j] = this.allUserReservations[this.i]
            this.j++;
          }
        }

      },
      error => {
        alert('getAllReservations error');
      }
    )
  }
}
