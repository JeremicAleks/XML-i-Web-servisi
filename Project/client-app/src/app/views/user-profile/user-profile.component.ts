import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { UserLoginDTO } from '../../models/user-login-dto';
import { AuthentificationService } from '../../services/authentification.service';
import { Reservation } from 'src/app/models/reservation';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {ReservationService} from '../../services/reservation.service';
import {Router} from '@angular/router';
import {Review} from '../../models/review';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  userLoginDTO: UserLoginDTO;
  review: Review={
    roomId:0,
    username:'',
    reservationId:0,
    rate:0,
    comment:''
  };
  isLogin: boolean;
  i:number;
  j:number;
  k:number;
  allUserReservations: Array<Reservation>;
  expiredReservations: Array<Reservation>;
  pendingReservations: Array<Reservation>;
  reservationForMessage: Reservation;
  time: Date;
  idRes:number;

  sendMessageForm = new FormGroup({
    messageText: new FormControl('', [Validators.required]),
    rating: new FormControl('', [Validators.required])
  });

  constructor(private authService: AuthentificationService, private modalService: NgbModal, private reservationService: ReservationService,private router: Router) { }

  ngOnInit() {
    this.isLogin = this.authService.checkSessionUser();
    if (this.isLogin) {
      this.userLoginDTO = this.authService.getSessionUser();
    }
    this.pendingReservations = [];
    this.expiredReservations = [];
    console.log(this.expiredReservations.length);
    console.log(this.pendingReservations.length);
    this.i=0;
    this.j=0;
    this.k=0;
    this.getAllReservations();
    console.log(this.expiredReservations);
    console.log(this.pendingReservations);

  }

  sendMessage() {
    console.log('Send Message to Agent clicked...');
    this.review.comment=this.sendMessageForm.get('messageText').value;
    this.review.rate=this.sendMessageForm.get('rating').value;
    this.review.reservationId=this.idRes;
    this.review.username=this.userLoginDTO.username;
    this.review.roomId=0;
    console.log('review koji saljem!')
    console.log(this.review)
    this.reservationService.sendReview(this.review).subscribe(
      data => {
        console.log('mozda uspesno poslato!')
      },
      error => {
        alert('review error');
      }
    )
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
reviewReservation(sendMessageModal,id){
    this.idRes=id;
    console.log('SendMessageModal has just opened...');
    this.modalService.open(sendMessageModal, {
      windowClass: 'dark-modal',
      centered: true,
      size: 'lg'
    });

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
            this.pendingReservations[this.k] = this.allUserReservations[this.i]
            this.k++;
          }
        }
        console.log(this.expiredReservations);
        console.log(this.pendingReservations);
      },
      error => {
        alert('getAllReservations error');
      }
    )
  }
}
