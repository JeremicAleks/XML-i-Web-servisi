import { Component, OnInit } from '@angular/core';
import {RoomService} from '../../services/room.service';
import {RoomDto} from '../../models/room-dto';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {

  room: RoomDto = {
    description:'',
    accommodationType:{
      active:false,
      description:'',
      id:0
    },
    accomodationCategory:{
      active:false,
      description:'',
      id:0
    },
    daysForCancel:0,
    image:[],
    location:{
      lat:0,
      lng:0,
      name:'',
      id:0
    },
    numberOfBeds:0,
    priceList:[{
      month:new Date(''),
      price:0,
      id:0
    }],
    reservation:[{
      checkIn:new Date(),
      checkOut:new Date(),
      state:'',
      messageTable:[{
        fromUser:'',
        toUser:'',
        messageString:'',
        id:0
      }]
    }],
    roomAdditionalService:[{
      description:'',
      active:false,
      id:0
    }]
  };
  allRooms: Array<RoomDto> = [];
  i:number;

  constructor(private roomService: RoomService) { }

  ngOnInit() {

    this.getAllRooms();
  }

  getAllRooms() {
    this.roomService.getAllRooms().subscribe(
      data => {
        this.allRooms = data;
        console.log(this.allRooms)
        this.i=this.allRooms.length;
      },
      error => {
        alert('getAllRooms error');
      }
    )
  }
}
