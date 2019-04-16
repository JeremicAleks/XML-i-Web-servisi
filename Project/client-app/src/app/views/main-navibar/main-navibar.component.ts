import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-main-navibar',
  templateUrl: './main-navibar.component.html',
  styleUrls: ['./main-navibar.component.css']
})
export class MainNavibarComponent implements OnInit {

  isLogin : boolean
  errorMessage: string;

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required, Validators.minLength(5)])
  })

  constructor(private modalService : NgbModal) { 
    this.isLogin = false;
  }

  ngOnInit() { }

  onSubmit() {
    alert("to be implemented")
  }

  openVerticallyCentered(content) {
    this.modalService.open(content, { 
      windowClass: 'dark-modal',
      centered: true
    });
  }

}
