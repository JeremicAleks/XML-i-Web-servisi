import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { UserLoginDTO } from 'src/app/models/user-login-dto';

@Component({
  selector: 'app-admin-user-configuration',
  templateUrl: './admin-user-configuration.component.html',
  styleUrls: ['./admin-user-configuration.component.css']
})
export class AdminUserConfigurationComponent implements OnInit {

  allUsers : Array<UserLoginDTO>

  constructor(private userService : UserService) { }

  ngOnInit() {
    this.getAllUsers()
  }

  getAllUsers() {
    this.userService.getAllUsers().subscribe(

      data => {
        this.allUsers = data;
        alert("bravo, dobio si usere")
      },
      error => {
        alert("getAllUsers error")
      }
    )
  }

}
