import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { UserLoginDTO } from 'src/app/models/user-login-dto';
import { AgentDTO } from 'src/app/models/agent-dto';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Role } from 'src/app/models/role';
import { allowPreviousPlayerStylesMerge } from '@angular/animations/browser/src/util';

@Component({
  selector: 'app-admin-user-configuration',
  templateUrl: './admin-user-configuration.component.html',
  styleUrls: ['./admin-user-configuration.component.css']
})
export class AdminUserConfigurationComponent implements OnInit {

  allUsers: Array<UserLoginDTO>;
  allRoles: Array<Role>;

  userToUpdate: string;
  userToPromote: string;

  newAgentForm = new FormGroup({
    agentName: new FormControl(''),
    agentLastName: new FormControl(''),
    agentAddress: new FormControl('', Validators.required),
    agentBusinessRegNumber: new FormControl('', Validators.required)
  });

  updateUserForm = new FormGroup({
    role: new FormControl('')
  });

  constructor(private userService: UserService, private modalService: NgbModal) { }

  ngOnInit() {
    this.getAllUsers();
    this.getAllRoles();
  }

  createNewAgent() {
    const agentAddress = this.newAgentForm.get('agentAddress').value;
    const agentBusinessRegNumber = this.newAgentForm.get('agentBusinessRegNumber').value;
    const newAgent = new AgentDTO();
    newAgent.userToPromote = this.userToPromote;
    newAgent.businessRegNumber = agentBusinessRegNumber;
    newAgent.address = agentAddress;

    this.userService.createNewAgent(newAgent).subscribe(
      data => {
        //alert(data.message);
        this.modalService.dismissAll();
        this.getAllUsers();
      },
      error => {
        //alert('createNewAgent - error');
      }
    );
  }

  newAgent(content, user) {
    this.modalService.open(content, {
      windowClass: 'dark-modal',
      centered: true
    });
    this.userToPromote = user.username;
    this.newAgentForm.controls.agentName.setValue(user.name);
    this.newAgentForm.controls.agentLastName.setValue(user.lastName);
  }

  activateUser(user) {
    const userToActivate = user.username;
    //alert('Activating: ' + userToActivate + ' ...');
    this.userService.activateUser(userToActivate).subscribe(
      data => {
        // alert(data.message);
        this.getAllUsers();
      },
      error => {
        // alert('activateUser - error');
      }
    );
  }

  blockUser(user) {
    const userToBlock = user.username;
    //alert('Blocking: ' + userToBlock + ' ...');
    this.userService.blockUser(userToBlock).subscribe(
      data => {
        // alert(data.message);
        this.getAllUsers();
      },
      error => {
        // alert('blockUser - error');
      }
    );
  }

  deleteUser(user) {
    const userToDelete = user.username;
    //alert('Deleting: ' + userToDelete + ' ...');
    this.userService.deleteUser(userToDelete).subscribe(
      data => {
        // alert(data.message);
        this.getAllUsers();
      },
      error => {
        // alert('deleteUser - error');
      }
    );
  }

  saveUserChanges() {
    this.userService.updateUser(this.userToUpdate, this.UserRole.value).subscribe(
      data => {
        // alert(data.message);
        this.modalService.dismissAll();
        this.getAllUsers();
      },
      error => {
        // alert('saveUserChanges - error');
      }
    );
  }

  openUpdateUserModal(updateUserModal, user) {
    this.userToUpdate = user.username;
    this.modalService.open(updateUserModal, {
      windowClass: 'dark-modal',
      centered: true
    });

    this.updateUserForm.get('role').setValue(user.role);
  }

  getAllUsers() {
    this.userService.getAllUsers().subscribe(
      data => {
        this.allUsers = data;
      },
      error => {
        //alert('getAllUsers error');
      }
    );
  }

  getAllRoles() {
    this.userService.getAllRoles().subscribe(
      data => {
        this.allRoles = data;
      },
      error => {
        //alert('getAllRoles error');
      }
    );
  }

  // Geteri
  get UserRole() { return this.updateUserForm.get('role'); }

}
