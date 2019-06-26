import { Component, OnInit } from '@angular/core';
import { NgbModule, NgbModal, NgbTabset } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { AccommodationDTO } from 'src/app/models/accommodation-dto';
import { AccommodationService } from 'src/app/services/accommodation.service';

@Component({
  selector: 'app-admin-code-book-additional-service',
  templateUrl: './admin-code-book-additional-service.component.html',
  styleUrls: ['./admin-code-book-additional-service.component.css']
})
export class AdminCodeBookAdditionalServiceComponent implements OnInit {

  accommodationServices: Array<AccommodationDTO>;
  accommodationServiceToUpdate: AccommodationDTO;

  newAdditionalServiceForm = new FormGroup({
    serviceDescription: new FormControl('', Validators.required)
  });

  updateAdditionalServiceForm = new FormGroup({
    serviceDescription: new FormControl('', Validators.required)
  });

  constructor(private accommodationService: AccommodationService, private modalService: NgbModal) { }

  ngOnInit() {
    this.getAccommodationServices();
  }

  updateOldAdditionalService() {
    this.accommodationServiceToUpdate.description = this.updateAdditionalServiceForm.get('serviceDescription').value;
    this.accommodationService.updateAccommondationCategory(this.accommodationServiceToUpdate).subscribe(
      data => {
        alert(data.message);
        this.modalService.dismissAll();
        this.getAccommodationServices();
      },
      error => {
        alert(error.message);
      }
    );
  }

  updateAdditionalService(content, additionalService: AccommodationDTO) {
    this.updateAdditionalServiceForm.get('serviceDescription').setValue(additionalService.description);
    this.accommodationServiceToUpdate = additionalService;

    this.modalService.open(content, {
      windowClass: 'dark-modal',
      centered: true
    });
  }

  newAdditionalService(content) {
    this.modalService.open(content, {
      windowClass: 'dark-modal',
      centered: true
    });
  }

  createNewAdditionalService() {
    const additionalService = new AccommodationDTO();
    additionalService.description = this.newAdditionalServiceForm.get('serviceDescription').value;
    console.log(additionalService.description);
    this.accommodationService.createAdditionalService(additionalService).subscribe(
      data => {
        alert(data.message);
        this.getAccommodationServices();
        this.modalService.dismissAll();
      },
      error => {
        alert(error.message);
      }
    );
  }

  deleteAdditionalService(additionalService: AccommodationDTO) {
    console.log('deleteAdditionalService clicked - id: ' + additionalService.id);
    this.accommodationService.deleteAdditionalService(additionalService).subscribe(
      data => {
        alert(data.message);
        this.getAccommodationServices();
      },
      error => {
        alert(error.message);
      }
    );
  }

  getAccommodationServices() {
    console.log('get additional services');
    this.accommodationService.getAdditionalServices().subscribe(
      data => {
        this.accommodationServices = data;
        alert(data.message);
      },
      error => {
        alert(error.message);
      }
    );
  }

}
