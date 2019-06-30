import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Role } from 'src/app/models/role';
import { UserService } from 'src/app/services/user.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PrivilegeEnum } from 'src/app/models/privilege-enum';


@Component({
  selector: 'app-admin-role-configuration',
  templateUrl: './admin-role-configuration.component.html',
  styleUrls: ['./admin-role-configuration.component.css'],

})

export class AdminRoleConfigurationComponent implements OnInit {

  allRoles: Array<Role>;
  roleToUpdate: string;

  newRoleForm = new FormGroup({
    roleName: new FormControl('', Validators.required)
  });

  updateRoleForm = new FormGroup({
    // checkbox
    write: new FormControl(false),
    read: new FormControl(false),
    delete: new FormControl(false)
  });

  constructor(private userService: UserService, private modalService: NgbModal) { }

  ngOnInit() {
    this.getAllRoles();
  }

  getAllRoles() {
    this.userService.getAllRoles().subscribe(
      data => {
        this.allRoles = data;
      },
      error => {
        // alert('getAllRoles error');
      }
    );
  }

  deleteRole(role) {
    this.userService.deleteRole(role.name).subscribe(
      data => {
        // alert(data.message);
      },
      error => {
        // alert(error.message);
      }
    );
  }

  newRole(content) {
    this.modalService.open(content, {
      windowClass: 'dark-modal',
      centered: true
    });
  }

  createNewRole() {
    const roleName = this.newRoleForm.get('roleName').value;
    this.userService.createRole(roleName).subscribe(
      data => {
        // alert(data.message);
        this.modalService.dismissAll();
      },
      error => {
        // alert(error.message);
      }
    );
  }

  updateRole(content, role) {

    for (const p of role.privileges) {
      if (p === PrivilegeEnum.write) {
        this.updateRoleForm.get('write').setValue(true);
      } else if (p === PrivilegeEnum.read) {
        this.updateRoleForm.get('read').setValue(true);
      } else if (p === PrivilegeEnum.delete) {
        this.updateRoleForm.get('delete').setValue(true);
      }
    }

    this.modalService.open(content, {
      windowClass: 'dark-modal',
      centered: true
    });

    this.roleToUpdate = role.name;
  }

  saveRoleChanges() {
    const currentPrivileges = new Array<PrivilegeEnum>();

    if (this.updateRoleForm.get('write').value) {
      currentPrivileges.push(PrivilegeEnum.write);
    }

    if (this.updateRoleForm.get('read').value) {
      currentPrivileges.push(PrivilegeEnum.read);
    }

    if (this.updateRoleForm.get('delete').value) {
      currentPrivileges.push(PrivilegeEnum.delete);
    }

    this.userService.updateRole(currentPrivileges, this.roleToUpdate).subscribe(
      data => {
        // alert(data.message);
        this.modalService.dismissAll();
      },
      error => {
        //alert(error.message);
      }
    );
  }

}
