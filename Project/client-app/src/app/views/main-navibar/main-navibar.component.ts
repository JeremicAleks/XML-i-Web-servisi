import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthentificationService } from 'src/app/services/authentification.service';
import { UserLoginDTO } from 'src/app/models/user-login-dto';
import { matchOtherValidator } from 'src/app/utils/match-passwords';

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

  registerForm = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    registerUsername: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    registerPassword: new FormControl('', [Validators.required, Validators.minLength(4)]),
    confirmPassword: new FormControl('', [Validators.required, matchOtherValidator('registerPassword')])

  })

  constructor(private modalService: NgbModal, private route: ActivatedRoute, private router: Router,
    private authService: AuthentificationService) {
    this.isLogin = false;
  }

  ngOnInit() {
    console.log("ngOnInit")
    console.log("isLogin: " + this.isLogin)
  }

  // SIGN IN PART
  onSignIn() {
    alert("sign in - to be tested")

    if (this.loginForm.invalid) {
      return;
    }

    this.clearSignInCredentialsErrors();
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

  openSignInModal(signIn) {
    this.modalService.open(signIn, {
      windowClass: 'dark-modal',
      centered: true
    });
  }

  private clearSignInCredentialsErrors() {
    this.username.setErrors({ 'usernameError': null });
    this.username.updateValueAndValidity();
    this.password.setErrors({ 'passwordError': null });
    this.password.updateValueAndValidity();
  }

  // Geteri za polja username i password  - sign in
  get username() { return this.loginForm.get('username') }
  get password() { return this.loginForm.get('password') }

  // REGISTRATION PART
  onRegistration() {
    alert("registration - to be tested")
    
    if (this.registerForm.invalid) {
      return;
    }

    this.clearRegistrationErrors();
    this.authService.register(this.firstName.value, this.lastName.value, this.email.value, 
        this.registrationUsername.value, this.registrationPassword.value, this.confirmPassword.value).subscribe(
      data => {
        this.modalService.dismissAll()
        this.openSignInModal('signIn')
      },
      error => {
        this.errorMessage = error;
        if (error.includes('Password')) {
          this.password.setErrors({ 'registrationPasswordError': error });
        } else if (error.includes('Username')) {
          this.username.setErrors({ 'registrationUsernameError': error });
        } else if (error.includes('Re-password')) {
          this.confirmPassword.setErrors({ 'confirmPasswordError': error });
        } else if (error.includes('First name')) {
          this.firstName.setErrors({ 'firstNameError': error });
        } else if (error.includes('Last name')) {
          this.lastName.setErrors({ 'lastNameError': error });
        } else if (error.includes('Email')) {
          this.email.setErrors({ 'emailError': error });
        }
      }
    )
  }

  openRegistrationModal(registration) {
    this.modalService.open(registration, {
      windowClass: 'dark-modal',
      centered: true
    });
  }

  // Metoda za proveru poklapanja password-a
  checkPasswords() {
    let registrationPassword = this.registrationPassword.value;
    let confirmPassword = this.confirmPassword.value;

    return registrationPassword === confirmPassword ? null : { notSame: true }
  }

  private clearRegistrationErrors() {
    this.registrationUsername.setErrors({ 'registrationUsernameError': null });
    this.registrationUsername.updateValueAndValidity();
    this.registrationPassword.setErrors({ 'registrationPasswordError': null });
    this.registrationPassword.updateValueAndValidity();
    this.confirmPassword.setErrors({ 'confirmPasswordError': null });
    this.confirmPassword.updateValueAndValidity();
    this.firstName.setErrors({ 'firstNameError': null });
    this.firstName.updateValueAndValidity();
    this.lastName.setErrors({ 'lastNameError': null });
    this.lastName.updateValueAndValidity();
    this.email.setErrors({ 'emailError': null });
    this.email.updateValueAndValidity();

  }

  // Geteri za polja firstName, lastName, username, email, password, repeatPassword
  get firstName() { return this.registerForm.get('firstName') }
  get lastName() { return this.registerForm.get('lastName') }
  get email() { return this.registerForm.get('email') }
  get registrationUsername() { return this.registerForm.get('registrationUsername') }
  get registrationPassword() { return this.registerForm.get('registrationPassword') }
  get confirmPassword() { return this.registerForm.get('confirmPassword') }


  // TEST FUNCTIONS 
  dropDownMenu() {
    alert("ne zatvaraj, kujo")
    console.log("isLogin: " + this.isLogin)
  }

}
