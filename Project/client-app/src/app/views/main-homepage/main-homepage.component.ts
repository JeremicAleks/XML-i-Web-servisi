import { Component, OnInit } from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-main-homepage',
  templateUrl: './main-homepage.component.html',
  styleUrls: ['./main-homepage.component.css']
})
export class MainHomepageComponent implements OnInit {
  brojsoba: number;

  constructor(private modalService: NgbModal) { }

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
}
