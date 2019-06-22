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
  brojsoba: number;

  searchParams: SearchParams;


  searchForm = new FormGroup({
    destination: new FormControl('', [Validators.required]),
    checkIn: new FormControl(new Date(), [Validators.required]),
    checkOut: new FormControl(new Date(), [Validators.required])
  });

  constructor(private modalService: NgbModal, private router: Router, private searchService: SearchService) { }

  ngOnInit() {
    this.brojsoba = 1;
  }

  openSignInModal(content) {
    this.modalService.open(content, {
      windowClass: 'dark-modal',
      centered: true
    });
  }

  addSoba() {
    this.brojsoba++;
  }

  removeSoba() {
    if (this.brojsoba > 0) {
      this.brojsoba--;
    }
  }

  search() {
    const destination = this.searchForm.get('destination').value;
    const ngbDate = this.searchForm.get('checkIn').value;
    const myDate = new Date(ngbDate.year, ngbDate.month - 1, ngbDate.day);
    const ngbDate2 = this.searchForm.get('checkOut').value;
    const myDate2 = new Date(ngbDate2.year, ngbDate2.month - 1, ngbDate2.day);

    this.router.navigate(['/searchResult' + '/' + destination + '/' + myDate + '/' + myDate2  + '/' + this.brojsoba]);
  }
}
