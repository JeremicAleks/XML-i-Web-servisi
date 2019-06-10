import { Component, OnInit } from '@angular/core';
import {NgbModule, NgbModal,NgbTabset, NgbDatepickerNavigateEvent} from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, Validators, FormControl, FormGroupDirective, NgForm } from '@angular/forms';
import {FileUploader} from "ng2-file-upload"

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
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

  addPrice = new FormGroup({
    date: new FormControl(new Date, Validators.required),
    price: new FormControl('', Validators.required)
  });

  public uploader:FileUploader = new FileUploader({
    isHTML5: true
  });

  constructor(private modalService: NgbModal) { }

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
      alert(fileItem);
      data.append('file', fileItem);
      data.append('fileSeq', 'seq'+j);
      data.append( 'dataType', this.addReservation.controls.imageUpload.value);
    }
    this.uploader.clearQueue();
}

dateNavigate($event: NgbDatepickerNavigateEvent) {
  console.log($event.next.month);
  console.log($event.next.year);
  // old value is contained in $event.current
}

openPriceList(content) {
  this.modalService.open(content, {
    windowClass: 'dark-modal',
    centered: true
  });
}

}
