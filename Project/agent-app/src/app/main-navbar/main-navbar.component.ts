import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import {NgbModule, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { UserLoginDTO } from '../models/user-login-dto';
import { matchOtherValidator } from '../utils/match-passwords';
import { AuthentificationService } from '../services/authentification.service';

@Component({
  selector: 'app-main-navbar',
  templateUrl: './main-navbar.component.html',
  styleUrls: ['./main-navbar.component.css']
})
export class MainNavbarComponent implements OnInit {


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
    registrationUsername: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    registrationPassword: new FormControl('', [Validators.required, Validators.minLength(4)]),
    confirmPassword: new FormControl('', [Validators.required, matchOtherValidator('registrationPassword')])

  })

  constructor(private modalService: NgbModal, private route: ActivatedRoute, private router: Router,
    private authService: AuthentificationService) {
    this.isLogin = false;
  }

  ngOnInit() {
    this.isLogin = this.authService.checkSessionUser()
    if (this.isLogin == true) {
      this.userLoginDTO = this.authService.getSessionUser()
    }
  }

  // SIGN IN PART
  onSignIn() {

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
          alert("Successfull sign in!")
          this.router.navigate(['/panel']);
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

  openSignInModal(content) {
    this.modalService.open(content, {
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
    // alert("registration - to be tested")

    if (this.registerForm.invalid) {
      return;
    }

    this.clearRegistrationErrors();
    this.authService.register(this.FirstName.value, this.LastName.value, this.Email.value,
      this.RegistrationUsername.value, this.RegistrationPassword.value, this.ConfirmPassword.value).subscribe(
        data => {
          this.modalService.dismissAll()
          // alert(data.message)
        },
        error => {
          this.errorMessage = error;
          if (error.includes('Password')) {
            this.RegistrationPassword.setErrors({ 'registrationPasswordError': error });
          } else if (error.includes('Username')) {
            this.RegistrationUsername.setErrors({ 'registrationUsernameError': error });
          } else if (error.includes('Re-password')) {
            this.ConfirmPassword.setErrors({ 'confirmPasswordError': error });
          } else if (error.includes('First name')) {
            this.FirstName.setErrors({ 'firstNameError': error });
          } else if (error.includes('Last name')) {
            this.LastName.setErrors({ 'lastNameError': error });
          } else if (error.includes('Email')) {
            this.Email.setErrors({ 'emailError': error });
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
    let registrationPassword = this.RegistrationPassword.value;
    let confirmPassword = this.ConfirmPassword.value;

    return registrationPassword === confirmPassword ? null : { notSame: true }
  }

  private clearRegistrationErrors() {
    this.RegistrationUsername.setErrors({ 'registrationUsernameError': null });
    this.RegistrationUsername.updateValueAndValidity();
    this.RegistrationPassword.setErrors({ 'registrationPasswordError': null });
    this.RegistrationPassword.updateValueAndValidity();
    this.ConfirmPassword.setErrors({ 'confirmPasswordError': null });
    this.ConfirmPassword.updateValueAndValidity();
    this.FirstName.setErrors({ 'firstNameError': null });
    this.FirstName.updateValueAndValidity();
    this.LastName.setErrors({ 'lastNameError': null });
    this.LastName.updateValueAndValidity();
    this.Email.setErrors({ 'emailError': null });
    this.Email.updateValueAndValidity();

  }

  // Geteri za polja firstName, lastName, username, email, password, repeatPassword
  get FirstName() { return this.registerForm.get('firstName') }
  get LastName() { return this.registerForm.get('lastName') }
  get Email() { return this.registerForm.get('email') }
  get RegistrationUsername() { return this.registerForm.get('registrationUsername') }
  get RegistrationPassword() { return this.registerForm.get('registrationPassword') }
  get ConfirmPassword() { return this.registerForm.get('confirmPassword') }

  // LOG OUT
  logOut() {
    this.authService.logOut().subscribe(
      data => {
        localStorage.removeItem('sessionUser')
        this.isLogin = false;
        alert(data.message);
      },
      error => {
        console.log("log out error")
      }
    )

  }
}
