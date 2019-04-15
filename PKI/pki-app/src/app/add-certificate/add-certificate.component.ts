import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-add-certificate',
  templateUrl: './add-certificate.component.html',
  styleUrls: ['./add-certificate.component.css']
})
export class AddCertificateComponent implements OnInit {

  certificateForm = new FormGroup({
    description: new FormControl(''),
    nameIssuer: new FormControl(''),
    beginDate: new FormControl(''),
    expirationDate: new FormControl(''),
    nameSubject: new FormControl(''),
    version : new FormControl(''),
    signature: new FormControl('')
  });

  constructor() { }

  ngOnInit() {
  }

}
