import { Component, OnInit } from '@angular/core';
import {UserLoginDTO} from '../../models/user-login-dto';
import {AuthentificationService} from '../../services/authentification.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  userLoginDTO: UserLoginDTO;
  isLogin: boolean;

  constructor(private authService: AuthentificationService) { }

  ngOnInit() {
    this.isLogin = this.authService.checkSessionUser();
    if (this.isLogin) {
      this.userLoginDTO = this.authService.getSessionUser();
    }
  }

}
