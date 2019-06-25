import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { AccommodationDTO } from 'src/app/models/accommodation-dto';
import { AccommodationService } from 'src/app/services/accommodation.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-admin-code-book',
  templateUrl: './admin-code-book.component.html',
  styleUrls: ['./admin-code-book.component.css']
})
export class AdminCodeBookComponent implements OnInit {

  accommodationTypes: Array<AccommodationDTO>;
  accommodationCategories: Array<AccommodationDTO>;

  newAccommodationTypeForm = new FormGroup({
    typeDescription: new FormControl('', Validators.required)
  });

  constructor(private accommodationService: AccommodationService, private modalService: NgbModal) { }

  ngOnInit() {
    this.getAccommodationTypes();
  }

  newAccommodationType(content) {
    this.modalService.open(content, {
      windowClass: 'dark-modal',
      centered: true
    });
  }

  createNewAccommodationType() {
    const accommodationType = new AccommodationDTO();
    accommodationType.description = this.newAccommodationTypeForm.get('typeDescription').value;
    console.log(accommodationType.description);
    this.accommodationService.createAccommodationType(accommodationType).subscribe(
      data => {
        alert(data.message);
        this.getAccommodationTypes();
        this.modalService.dismissAll();
      },
      error => {
        alert(error.message);
      }
    );
  }

  deleteAccommodationType(id: number) {
    console.log('deleteAccommType clicked - id: ' + id);
    this.accommodationService.deleteAccommodationType(id).subscribe(
      data => {
        alert(data.message);
        this.getAccommodationTypes();
      },
      error => {
        alert(error.message);
      }
    );
  }

  getAccommodationTypes() {
    console.log('get accommodation types');
    this.accommodationService.getAccommodationTypes().subscribe(
      data => {
        this.accommodationTypes = data;
        alert(data.message);
      },
      error => {
        alert(error.message);
      }
    );
  }

  getAccommodationCategories() {
    console.log('get accommodation categories');
  }

  getAdditionalServices() {
    console.log('get additional services');
  }

}
