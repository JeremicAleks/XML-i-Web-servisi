import { Component, OnInit, PipeTransform } from '@angular/core';
import { DecimalPipe } from '@angular/common';
import { Observable } from 'rxjs';
import { FormControl } from '@angular/forms';
import { map, startWith } from 'rxjs/operators';
import { Role } from 'src/app/models/role';


@Component({
  selector: 'app-admin-role-configuration',
  templateUrl: './admin-role-configuration.component.html',
  styleUrls: ['./admin-role-configuration.component.css'],

})

export class AdminRoleConfigurationComponent implements OnInit {

   allRoles : Array<Role>

  constructor() {}

  ngOnInit() { }

}

