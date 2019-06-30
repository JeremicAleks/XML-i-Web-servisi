import { Component, OnInit } from '@angular/core';
import { NgbModule, NgbModal, NgbTabset } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { AccommodationDTO } from 'src/app/models/accommodation-dto';
import { AccommodationService } from 'src/app/services/accommodation.service';

@Component({
  selector: 'app-admin-code-book-accommodation-category',
  templateUrl: './admin-code-book-accommodation-category.component.html',
  styleUrls: ['./admin-code-book-accommodation-category.component.css']
})
export class AdminCodeBookAccommodationCategoryComponent implements OnInit {

  accommodationCategories: Array<AccommodationDTO>;
  accommodationCategoryToUpdate: AccommodationDTO;

  newAccommodationCategoryForm = new FormGroup({
    categoryDescription: new FormControl('', Validators.required)
  });

  updateAccommodationCategoryForm = new FormGroup({
    categoryDescription: new FormControl('', Validators.required)
  });

  constructor(private accommodationService: AccommodationService, private modalService: NgbModal) { }

  ngOnInit() {
    this.getAccommodationCategories();
  }

  updateOldAccommodationCategory() {
    this.accommodationCategoryToUpdate.description = this.updateAccommodationCategoryForm.get('categoryDescription').value;
    this.accommodationService.updateAccommondationCategory(this.accommodationCategoryToUpdate).subscribe(
      data => {
        // alert(data.message);
        this.modalService.dismissAll();
        this.getAccommodationCategories();
      },
      error => {
        // alert(error.message);
      }
    );
  }

  updateAccommodationCategory(content, accommodationCategory: AccommodationDTO) {
    this.updateAccommodationCategoryForm.get('categoryDescription').setValue(accommodationCategory.description);
    this.accommodationCategoryToUpdate = accommodationCategory;

    this.modalService.open(content, {
      windowClass: 'dark-modal',
      centered: true
    });
  }

  newAccommodationCategory(content) {
    this.modalService.open(content, {
      windowClass: 'dark-modal',
      centered: true
    });
  }

  createNewAccommodationCategory() {
    const accommodationCategory = new AccommodationDTO();
    accommodationCategory.description = this.newAccommodationCategoryForm.get('categoryDescription').value;
    console.log(accommodationCategory.description);
    this.accommodationService.createAccommodationCategory(accommodationCategory).subscribe(
      data => {
        // alert(data.message);
        this.getAccommodationCategories();
        this.modalService.dismissAll();
      },
      error => {
        // alert(error.message);
      }
    );
  }

  deleteAccommodationCategory(accommodationCategory: AccommodationDTO) {
    console.log('deleteAccommType clicked - id: ' + accommodationCategory.id);
    this.accommodationService.deleteAccommodationCategory(accommodationCategory).subscribe(
      data => {
        // alert(data.message);
        this.getAccommodationCategories();
      },
      error => {
        // alert(error.message);
      }
    );
  }

  getAccommodationCategories() {
    console.log('get accommodation categories');
    this.accommodationService.getAccommodationCategories().subscribe(
      data => {
        this.accommodationCategories = data;
        // alert(data.message);
      },
      error => {
        // alert(error.message);
      }
    );
  }

}
