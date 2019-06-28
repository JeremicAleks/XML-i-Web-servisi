import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";



@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {


  changepassForm = new FormGroup({
    newPassword: new FormControl('', [Validators.required, Validators.minLength(4)]),
    confirmPassword: new FormControl('', [Validators.required, Validators.minLength(4)])
  });

  errorMessage: string;

  constructor(private modalService: NgbModal) { }

  ngOnInit() {

  }

  openChangePasswordModal(changepass) {
    this.modalService.open(changepass, {
      windowClass: 'dark-modal',
      centered: true
    });
  }

}
