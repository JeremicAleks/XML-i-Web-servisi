import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthentificationService } from 'src/app/services/authentification.service';
import { UserLoginDTO } from 'src/app/models/user-login-dto';

@Component({
  selector: 'app-main-navibar',
  templateUrl: './main-navibar.component.html',
  styleUrls: ['./main-navibar.component.css']
})
export class MainNavibarComponent implements OnInit {

  userLoginDTO: UserLoginDTO

  isLogin: boolean
  errorMessage: string;

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required, Validators.minLength(4)])
  })

  constructor(private modalService: NgbModal, private route: ActivatedRoute, private router: Router,
    private authService: AuthentificationService) {
    this.isLogin = false;
  }

  ngOnInit() {
    console.log("ngOnInit")
    console.log("isLogin: " + this.isLogin)
  }

  onSubmit() {
    alert("to be tested")

    if (this.loginForm.invalid) {
      return;
    }

    this.clearCredentialsErrors();
    this.authService.signIn(this.username.value, this.password.value).subscribe(

      data => {
        this.userLoginDTO = data;
        if (data != null) {
          this.isLogin = true
          this.modalService.dismissAll()
        }
      },

      error => {
        this.errorMessage = error;
        if (error.includes("password")) {
          this.password.setErrors({ 'passwordError': error });
        } else {
          this.username.setErrors({ 'usernameError': error });
        }
      }
    )
  }

  openVerticallyCentered(content) {
    this.modalService.open(content, {
      windowClass: 'dark-modal',
      centered: true
    });
  }

  private clearCredentialsErrors() {
    this.username.setErrors({ 'usernameError': null });
    this.username.updateValueAndValidity();
    this.password.setErrors({ 'passwordError': null });
    this.password.updateValueAndValidity();
  }

  // Geteri za polja username i password
  get username() { return this.loginForm.get('username') }
  get password() { return this.loginForm.get('password') }

  dropDownMenu() {
    alert("ne zatvaraj, kujo")
    console.log("isLogin: " + this.isLogin)
  }

}
