import { Component, OnInit } from '@angular/core';
import { NgbModule, NgbModal, NgbTabset, NgbDatepickerNavigateEvent, NgbInputDatepicker } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, Validators, FormControl, FormGroupDirective, NgForm } from '@angular/forms';
import { FileUploader } from "ng2-file-upload"
import { MomentDateAdapter } from '@angular/material-moment-adapter'
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatDatepicker } from '@angular/material/datepicker';
import { Location } from '../models/location';
import * as _moment from 'moment';
import { default as _rollupMoment, Moment } from 'moment';
import { PriceList } from '../models/price-list';
import { RoomServiceService } from '../services/room-service.service';
import { Room } from '../models/room';
import { ReservationService } from '../services/reservation.service';
import { Reservation, ReservationStateEnum } from '../models/reservation';
import { MessageTable } from '../models/message-table';
import { SendMessageDto } from '../models/send-message-dto';
import { AllowReservation } from '../models/allow-reservation';
import { RoomAdditionalServices } from '../models/room-additional-services';
import { RoomType } from '../models/room-type';
import { RoomCategory } from '../models/room-category';
const moment = _rollupMoment || _moment;

export const MY_FORMATS = {
  parse: {
    dateInput: 'MM/YYYY',
  },
  display: {
    dateInput: 'MM/YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};



@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css'],
  providers: [

    { provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE] },

    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS },
  ]
})


export class ReservationsComponent implements OnInit {



  addReservation = new FormGroup({
    locationname: new FormControl('', Validators.required),
    locationLat: new FormControl(new Number, Validators.required),
    locationLng: new FormControl(new Number, Validators.required),
    selectType: new FormControl(new RoomType, Validators.required),
    selectCategory: new FormControl(new RoomCategory, Validators.required),
    roomDescription: new FormControl('', Validators.required),
    numberOfPersons: new FormControl(new Number, Validators.required),
    roomAdditionalServices: new FormControl(new Array<RoomAdditionalServices>(), Validators.required),
    roomCancel: new FormControl(new Number, Validators.required),
    imageUpload: new FormControl(null, Validators.required),
  });

  addRoomRes = new FormGroup({
    selectRoom: new FormControl(new Room, Validators.required),
    from: new FormControl('', Validators.required),
    to: new FormControl('', Validators.required),
  });

  addPrice = new FormGroup({
    date: new FormControl(moment(), Validators.required),
    price: new FormControl('', Validators.required)
  });

  sendMessage = new FormGroup({
    message: new FormControl('', Validators.required)
  });

  public uploader: FileUploader = new FileUploader({
    isHTML5: true
  });

  prices: Array<PriceList>;
  images: Array<FormData>;
  user: any;
  roomString: any;
  rooms: Array<Room>;
  messages: Array<any>;
  reservations: Array<any>;
  types: Array<RoomType>
  categories: Array<RoomCategory>
  addServices: Array<RoomAdditionalServices>

  constructor(private modalService: NgbModal, private roomService: RoomServiceService, private reservationService: ReservationService) {
    this.prices = new Array<PriceList>();
    this.images = new Array<FormData>();
  }

  OpenSendMessage(sendMessageModal: any, i: any) {

    this.user = i;

    this.modalService.open(sendMessageModal, {
      windowClass: 'dark-modal',
      centered: true
    });


  }

  SendMessage() {
    var m = new MessageTable();
    m.messageString = this.sendMessage.get("message").value;
    m.toUser = this.user.messageTable.fromUser;
    var smd = new SendMessageDto(this.user.roomId, this.user.resId, m);

    this.reservationService.sendMessage(smd).subscribe(
      data => {
        alert("Message sent!")
      }
    )

  }


  ngOnInit() {
    this.roomService.getRooms().subscribe(
      data => {
        this.rooms = data;
      },
      error => {
        alert("Cant get rooms");
      }
    )
    this.reservationService.getMessages().subscribe(
      data => {
        this.messages = data;
      },
      error => {
        alert("Cant get messages");
      }
    )
    this.reservationService.getReservations().subscribe(
      data => {
        this.reservations = data;
      },
      error => {
        alert("Cant get reservations");
      }
    )
    this.roomService.getCategories().subscribe(
      data => {
        this.categories = data
      }
    )
    this.roomService.getTypes().subscribe(
      data => {
        this.types = data
      }
    )
    this.roomService.getAdditionalServices().subscribe(
      data => {
        this.addServices = data
      }
    )

  }

  uploadSubmit() {

    for (let j = 0; j < this.uploader.queue.length; j++) {
      let file = new FormData();
      let fileItem = this.uploader.queue[j]._file;
      file.append('file', fileItem);
      file.append('roomString', this.roomString);
      this.roomService.addFiles(file).subscribe(
        data => {
          alert("We have finished with files uploading");
        }
      )
    }
  }

  AddReservation() {
    var res = new Reservation(this.addRoomRes.get("from").value, this.addRoomRes.get("to").value, ReservationStateEnum.ALLOWED);

    this.reservationService.addReservation(res, this.addRoomRes.get("selectRoom").value.id).subscribe(
      data => {
        alert("Reservation has been successfully added!");
      }
    )

  }

  AddRoom() {
    var room = new Room();
    room.roomAdditionalService = this.addReservation.get("roomAdditionalServices").value;
    room.accommodationCategory = room.accommodationType = this.addReservation.get("selectCategory").value
    room.location = new Location(this.addReservation.get("locationLat").value, this.addReservation.get("locationLng").value, this.addReservation.get("locationname").value)
    room.numberOfBeds = this.addReservation.get("numberOfPersons").value;
    room.priceList = this.prices;
    room.accommodationType = this.addReservation.get("selectType").value
    room.description = this.addReservation.get("roomDescription").value;
    room.daysForCancel = this.addReservation.get("roomCancel").value;
    room.image = new Array<string>();
    this.roomString = this.generateANumber();

    for (let j = 0; j < this.uploader.queue.length; j++) {
      room.image.push(this.roomString + "-" + this.uploader.queue[j]._file.name);
    }





    this.roomService.addRoom(room).subscribe(
      data => {
        this.uploadSubmit();
        alert("Successfuly added room!");
      })

  }

  AddPrice() {
    this.prices.push(new PriceList(this.addPrice.get("price").value, this.addPrice.get("date").value));
  }

  Allow(p) {
    var allow = new AllowReservation(p.reservation.id, ReservationStateEnum.ALLOWED);
    this.reservationService.allowReservation(allow).subscribe(
      data => {
        this.reservations = data;
      }
    )
  }
  Deny(p) {
    var allow = new AllowReservation(p.reservation.id, ReservationStateEnum.NOTALLOWED);
    this.reservationService.allowReservation(allow).subscribe(
      data => {
        this.reservations = data;
      }
    )

  }

  RemovePrice(i: any) {
    var priceTemp = new Array<PriceList>();

    for (let index = 0; index < this.prices.length; index++) {
      if (index != i) {
        priceTemp.push(this.prices[index]);
      }
    }
    this.prices = priceTemp;
  }

  generateANumber() {
    return Math.floor(Math.random() * (50000000000000 - 0 + 1)) + 0
  }



  chosenYearHandler(normalizedYear: Moment) {
    const ctrlValue = this.addPrice.value.date;
    ctrlValue.year(normalizedYear.year());
    this.addPrice.get("date").setValue(ctrlValue);
  }

  chosenMonthHandler(normalizedMonth: Moment, datepicker: MatDatepicker<Moment>) {
    const ctrlValue = this.addPrice.get("date").value;
    ctrlValue.month(normalizedMonth.month());
    this.addPrice.get("date").setValue(ctrlValue);
    datepicker.close();
  }

}
