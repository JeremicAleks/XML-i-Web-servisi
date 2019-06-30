import { Component, OnInit } from '@angular/core';
import { NgbModule, NgbModal, NgbTabset } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { AccommodationDTO } from 'src/app/models/accommodation-dto';
import { AccommodationService } from 'src/app/services/accommodation.service';

@Component({
  selector: 'app-admin-code-book-accommodation-type',
  templateUrl: './admin-code-book-accommodation-type.component.html',
  styleUrls: ['./admin-code-book-accommodation-type.component.css']
})
export class AdminCodeBookAccommodationTypeComponent implements OnInit {

  accommodationTypes: Array<AccommodationDTO>;
  accommodationTypeToUpdate: AccommodationDTO;

  accommodationCategories: Array<AccommodationDTO>;

  newAccommodationTypeForm = new FormGroup({
    typeDescription: new FormControl('', Validators.required)
  });

  updateAccommodationTypeForm = new FormGroup({
    typeDescription: new FormControl('', Validators.required)
  });

  constructor(private accommodationService: AccommodationService, private modalService: NgbModal) { }

  ngOnInit() {
    this.getAccommodationTypes();
  }

  updateOldAccommodationType() {
    this.accommodationTypeToUpdate.description = this.updateAccommodationTypeForm.get('typeDescription').value;
    this.accommodationService.updateAccommondationType(this.accommodationTypeToUpdate).subscribe(
      data => {
        // alert(data.message);
        this.modalService.dismissAll();
        this.getAccommodationTypes();
      },
      error => {
        // alert(error.message);
      }
    );
  }

  updateAccommodationType(content, accommodationType: AccommodationDTO) {
    this.updateAccommodationTypeForm.get('typeDescription').setValue(accommodationType.description);
    this.accommodationTypeToUpdate = accommodationType;

    this.modalService.open(content, {
      windowClass: 'dark-modal',
      centered: true
    });
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
        // alert(data.message);
        this.getAccommodationTypes();
        this.modalService.dismissAll();
      },
      error => {
        // alert(error.message);
      }
    );
  }

  deleteAccommodationType(accommodationType: AccommodationDTO) {
    console.log('deleteAccommType clicked - id: ' + accommodationType.id);

    this.accommodationService.deleteAccommodationType(accommodationType).subscribe(
      data => {
        // alert(data.message);
        this.getAccommodationTypes();
      },
      error => {
        // alert(error.message);
      }
    );
  }

  getAccommodationTypes() {
    console.log('get accommodation types');
    this.accommodationService.getAccommodationTypes().subscribe(
      data => {
        this.accommodationTypes = data;
        // alert(data.message);
      },
      error => {
        // alert(error.message);
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
