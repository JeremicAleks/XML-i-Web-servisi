import { Component, OnInit } from '@angular/core';
import {NgbModule, NgbModal,NgbTabset, NgbDatepickerNavigateEvent, NgbInputDatepicker } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, Validators, FormControl, FormGroupDirective, NgForm } from '@angular/forms';
import {FileUploader} from "ng2-file-upload"
import {MomentDateAdapter} from '@angular/material-moment-adapter'
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import {MatDatepicker} from '@angular/material/datepicker';
import * as _moment from 'moment';
import { default as _rollupMoment, Moment } from 'moment';
import { PriceList } from '../models/price-list';
const moment = _rollupMoment||_moment;

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
    // `MomentDateAdapter` can be automatically provided by importing `MomentDateModule` in your
    // application's root module. We provide it at the component level here, due to limitations of
    // our example generation script.
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},

    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ]
})


export class ReservationsComponent implements OnInit {

  

  addReservation = new FormGroup({
    locationname: new FormControl('', Validators.required),
    locationCords: new FormControl('', Validators.required),
    selectType: new FormControl('', Validators.required),
    roomDescription: new FormControl('', Validators.required),
    numberOfPersons: new FormControl('', Validators.required),
    roomAdditionalServices: new FormControl(new Array<String>(), Validators.required),
    roomCancel: new FormControl(new Number, Validators.required),
    imageUpload:new FormControl(null, Validators.required),
  });

  addRoomRes  = new FormGroup({
    selectRoom: new FormControl('', Validators.required),
    from: new FormControl('', Validators.required),
    to: new FormControl('', Validators.required),
  });

  addPrice = new FormGroup({
    date:  new FormControl(moment(),Validators.required),
    price: new FormControl('', Validators.required)
  });

  public uploader:FileUploader = new FileUploader({
    isHTML5: true
  });

  prices:Array<PriceList>;
  images:Array<FormData>;
  constructor() { 
    this.prices = new Array<PriceList>();
    this.images = new Array<FormData>();
  }

  ngOnInit() {
    
  }

  uploadSubmit(){
    for (let i = 0; i < this.uploader.queue.length; i++) {
      let fileItem = this.uploader.queue[i]._file;
      if(fileItem.size > 10000000){
        alert("Each File should be less than 10 MB of size.");
        return;
      }
    }
    for (let j = 0; j < this.uploader.queue.length; j++) {
      let data = new FormData();
      let fileItem = this.uploader.queue[j]._file;
      data.append('file', fileItem);
      data.append('fileSeq', 'seq'+j);
      this.images.push(data)
    }
    this.uploader.clearQueue();
}

AddRoom() {

 // alert("LName" + this.addReservation.get("locationname").value+ "locationCords" + this.addReservation.get("locationCords").value + "st" + this.addReservation.get("selectType").value +
 // "rd" +  this.addReservation.get("roomDescription").value + "np" + this.addReservation.get("numberOfPersons").value +
 // "roomAddition" + this.addReservation.get("roomAdditionalServices").value + "rc" + this.addReservation.get("roomCancel").value);
  this.uploadSubmit();

}

AddPrice(){

  alert(this.addPrice.get("price").value);
  alert(this.addPrice.get("date").value);

  this.prices.push(new PriceList(this.addPrice.get("price").value,this.addPrice.get("date").value));

}

RemovePrice(i:any){
  
  var priceTemp = new Array<PriceList>();

  for (let index = 0; index < this.prices.length; index++) {
    alert(this.prices[index].date)
   if(index != i){
      priceTemp.push(this.prices[index]);
    }
  }
  this.prices = priceTemp;
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
