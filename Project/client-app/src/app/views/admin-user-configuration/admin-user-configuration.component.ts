import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin-user-configuration',
  templateUrl: './admin-user-configuration.component.html',
  styleUrls: ['./admin-user-configuration.component.css']
})
export class AdminUserConfigurationComponent implements OnInit {

   

  constructor(private userService : UserService) { }

  ngOnInit() {
    
  }

}
