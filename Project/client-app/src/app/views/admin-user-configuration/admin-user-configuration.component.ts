import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { UserLoginDTO } from 'src/app/models/user-login-dto';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Role } from 'src/app/models/role';

@Component({
  selector: 'app-admin-user-configuration',
  templateUrl: './admin-user-configuration.component.html',
  styleUrls: ['./admin-user-configuration.component.css']
})
export class AdminUserConfigurationComponent implements OnInit {

  allUsers: Array<UserLoginDTO>
  allRoles: Array<Role>

  userToUpdate: String

  updateUserForm = new FormGroup({
    role: new FormControl('')
  })

  constructor(private userService: UserService, private modalService: NgbModal) { }

  ngOnInit() {
    this.getAllUsers()
    this.getAllRoles()
  }

  saveUserChanges() {

    this.userService.updateUser(this.userToUpdate, this.UserRole.value).subscribe(
      data => {
        alert(data.message)
        this.modalService.dismissAll()
        this.getAllUsers();
      },
      error => {
        alert(error.message)
      }
    )

  }

  openUpdateUserModal(updateUserModal, user) {
    this.userToUpdate = user.username
    this.modalService.open(updateUserModal, {
      windowClass: 'dark-modal',
      centered: true
    });

    this.updateUserForm.get('role').setValue(user.role)
  }

  getAllUsers() {
    this.userService.getAllUsers().subscribe(
      data => {
        this.allUsers = data;
      },
      error => {
        alert("getAllUsers error")
      }
    )
  }

  getAllRoles() {
    this.userService.getAllRoles().subscribe(
      data => {
        this.allRoles = data;
      },
      error => {
        alert("getAllRoles error")
      }
    )
  }

  // Geteri 
  get UserRole() { return this.updateUserForm.get('role') }

}
