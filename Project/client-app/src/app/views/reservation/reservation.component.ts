import { Component, OnInit } from '@angular/core';
import { RoomService } from 'src/app/services/room.service';
import {DomSanitizer} from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthentificationService } from 'src/app/services/authentification.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  constructor(private DomSanitizer: DomSanitizer,private route: ActivatedRoute,private roomService: RoomService,private authService:AuthentificationService) { }
checkIn;
checkOut;
room:any;
  ngOnInit() {

    const checkIn = this.route.snapshot.paramMap.get('checkIn');
    //alert(checkIn);
    this.checkIn = new Date(checkIn);
    const checkOut = this.route.snapshot.paramMap.get('checkOut');
    //alert(checkOut);
    this.checkOut = new Date(checkOut);
    const roomId = +this.route.snapshot.paramMap.get('roomId');
    this.roomService.getRoom(this.checkIn,this.checkOut,roomId).subscribe(data=>{
      this.room =data;
      this.DomSanitizer.bypassSecurityTrustUrl(this.room.room.image[0]);
    })
  }

  Reserve(){

    if(!this.authService.getSessionUser()){
      alert("Please login first!")
    }
    else if(this.room.priceList==null){
      alert("No available price list for room!")
    }
    else{
      this.roomService.reserveRoom(this.checkIn,this.checkOut,this.room.room.id).subscribe(data=>
        {
          alert("Successfully reserved room");
        },
        error=>{
          alert(error);
        })
    }

  }

}
