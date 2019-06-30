import { Component, OnInit } from '@angular/core';
import {RoomService} from '../../services/room.service';
import {RoomDto} from '../../models/room-dto';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {


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
