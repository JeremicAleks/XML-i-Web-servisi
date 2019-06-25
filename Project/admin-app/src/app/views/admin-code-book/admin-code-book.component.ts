import { Component, OnInit } from '@angular/core';
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

  constructor(private accommodationService: AccommodationService, private modalService: NgbModal) { }

  ngOnInit() {
    this.getAccommodationTypes();
  }

  deleteAccomodationType(id: string) {
    console.log('deleteAccommType clicked - id: ' + id);
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
