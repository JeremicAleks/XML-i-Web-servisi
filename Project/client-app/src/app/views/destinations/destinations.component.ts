import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SearchService } from '../../services/search.service';
import { SearchParams } from '../../models/search-params';
import { FormControl, FormGroup, Validators, FormBuilder, FormArray, AbstractControl } from '@angular/forms';
import { RoomService } from 'src/app/services/room.service';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-destinations',
  templateUrl: './destinations.component.html',
  styleUrls: ['./destinations.component.css']
})
export class DestinationsComponent implements OnInit {

  numOfPeople: number;
  destination: string;
  searchParams: SearchParams;


  searchForm = new FormGroup({
    destination: new FormControl('', [Validators.required]),
    checkIn: new FormControl(new Date(), [Validators.required]),
    checkOut: new FormControl(new Date(), [Validators.required])
  });

  typeForm: FormGroup;
  categoryForm: FormGroup;
  additionalServiceForm: FormGroup;
  rooms:Array<any>;

  types: any;

  constructor(private DomSanitizer: DomSanitizer,private route: ActivatedRoute, private searchService: SearchService, private roomService: RoomService, private router: Router, private _formBuilder: FormBuilder) {

    

    this.typeForm = this._formBuilder.group({
      typeArray: this._formBuilder.array([])
    });

    this.categoryForm = this._formBuilder.group({
      categoryArray: this._formBuilder.array([])
    });

    this.additionalServiceForm = this._formBuilder.group({
      additionalServiceArray: this._formBuilder.array([])
    });

    this.roomService.getTypes().subscribe(
      data => {
        this.types = data;
        data.forEach(element => {
          console.log("Desc " + element.description)
          this.addItemTypeForm();
        });
      }
    )
  }

  get typeArray() {
    console.log("o bogo - get type array")
    return this.typeForm.get('typeArray') as FormArray;
  }

  addItemTypeForm() {
    this.typeArray.push(this._formBuilder.control(new Boolean));
    console.log('item type form - boolean pushed to type array')
  }

  ngOnInit() {

    this.destination = this.route.snapshot.paramMap.get('destination');
    const checkIn = this.route.snapshot.paramMap.get('checkIn');
    //alert(checkIn);
    const checkInDate = new Date(checkIn);
    const checkOut = this.route.snapshot.paramMap.get('checkOut');
    //alert(checkOut);
    const checkOutDate = new Date(checkOut);
    this.searchForm.get('check')
    this.numOfPeople = +this.route.snapshot.paramMap.get('numOfPeople');
    //alert(this.numOfPeople);

    //alert(this.destination);

    if (this.destination === 'null') {
      this.destination = '';
    }

    //alert(this.destination);

    this.searchParams = new SearchParams(this.destination, checkInDate, checkOutDate, this.numOfPeople);

    this.searchForm.get('destination').setValue(this.destination);
    this.searchForm.get('checkIn').setValue(checkInDate);
    this.searchForm.get('checkOut').setValue(checkOutDate);

    this.searchService.getSearchResults(this.searchParams).subscribe(
      data => {
        //alert('nesto se desilo kao');
        this.rooms = data;
        this.DomSanitizer.bypassSecurityTrustUrl(this.rooms[0].image[0]);
        alert(this.rooms[0].location.name)
      },
      error => {
        //alert('jebem ti se s mamom');
      

      }
    );
  }

  reservation(roomId) {
    this.router.navigate(['/reservation/'+this.searchForm.get("checkIn").value+"/"+this.searchForm.get("checkOut").value+"/"+roomId]);
  }

}
