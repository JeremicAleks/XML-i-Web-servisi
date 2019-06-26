import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SearchParams } from '../../models/search-params';
import { SearchService } from '../../services/search.service';

@Component({
  selector: 'app-main-homepage',
  templateUrl: './main-homepage.component.html',
  styleUrls: ['./main-homepage.component.css']
})
export class MainHomepageComponent implements OnInit {

  searchParams: SearchParams;
  numOfPeople: number;

  searchForm = new FormGroup({
    destination: new FormControl('', [Validators.required]),
    checkIn: new FormControl(new Date(), [Validators.required]),
    checkOut: new FormControl(new Date(), [Validators.required])
  });

  constructor(private modalService: NgbModal, private router: Router, private searchService: SearchService) { }

  ngOnInit() {
    this.numOfPeople = 1;
  }

  openSignInModal(content) {
    this.modalService.open(content, {
      windowClass: 'dark-modal',
      centered: true
    });
  }

  addPeople() {
    this.numOfPeople++;
  }

  removePeople() {
    if (this.numOfPeople > 0) {
      this.numOfPeople--;
    }
  }

  search() {
    let destination = this.searchForm.get('destination').value;
    const ngbDate = this.searchForm.get('checkIn').value;
    const myDate = new Date(ngbDate.year, ngbDate.month, ngbDate.day);
    const ngbDate2 = this.searchForm.get('checkOut').value;
    const myDate2 = new Date(ngbDate2.year, ngbDate2.month, ngbDate2.day);

    if (destination === '') {
      destination = 'null';
    }


   // this.router.navigate(['/searchResult'+'/'+this.searchParams.destination + '/' + this.searchParams.checkIn + '/' + this.searchParams.checkOut + '/' + this.searchParams.numOfPeople])

    this.router.navigate(['/searchResult' + '/' + destination + '/' + myDate + '/' + myDate2  + '/' + this.numOfPeople]);

  }
}
