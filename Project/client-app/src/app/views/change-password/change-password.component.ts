import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import { AuthentificationService } from 'src/app/services/authentification.service';
import { ActivatedRoute, Router } from '@angular/router';



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
  token:string;

  constructor(private modalService: NgbModal,private authService :AuthentificationService,private route: ActivatedRoute,private router:Router) { }

  ngOnInit() {
    this.token = this.route.snapshot.paramMap.get('token');
      this.authService.checkTokenForForgottenPassword(this.token).subscribe(
        data=>{
        },
        error=>{
          this.router.navigate(['']);
        }
      )
  }

  ChangeMe(){
    this.authService.changePassword(this.token,this.changepassForm.get('newPassword').value,this.changepassForm.get('confirmPassword').value).subscribe(
      data=>
      {
        alert(data.message);
        this.router.navigate(['']);
      },
      error=>{
        alert(error.message);
      }
      )
  }
  openChangePasswordModal(changepass) {
    this.modalService.open(changepass, {
      windowClass: 'dark-modal',
      centered: true
    });
  }

}
