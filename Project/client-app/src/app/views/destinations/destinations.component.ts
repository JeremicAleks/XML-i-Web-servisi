import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SearchService } from '../../services/search.service';
import { SearchParams } from '../../models/search-params';
import { FormControl, FormGroup, Validators, FormBuilder, FormArray, AbstractControl } from '@angular/forms';
import { RoomService } from 'src/app/services/room.service';
import { DomSanitizer } from '@angular/platform-browser';
import { FilterField } from 'src/app/models/filter-field';

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


  rooms: Array<any>;
  roomsCopy: Array<any>;
  categories: Array<FilterField> = new Array<FilterField>();
  additionalServices: Array<FilterField> = new Array<FilterField>();
  types: Array<FilterField> = new Array<FilterField>();

  constructor(private DomSanitizer: DomSanitizer, private route: ActivatedRoute,
    private searchService: SearchService, private roomService: RoomService,
    private router: Router, private _formBuilder: FormBuilder) {

    this.roomService.getTypes().subscribe(
      data => {
        data.forEach(element => {
          this.types.push(element);
        });
      }
    );

    this.roomService.getCategories().subscribe(
      data => {
        data.forEach(element => {
          this.categories.push(element);
        });
      }
    );

    this.roomService.getAdditionalServices().subscribe(
      data => {
        data.forEach(element => {
          this.additionalServices.push(element);
        });
      }
    );
  }

  searchAgain() {
    console.log('searchAgain clicked');
    const checkInDate = this.searchForm.get('checkIn').value;
    const checkOutDate = this.searchForm.get('checkOut').value;
    const destination = this.searchForm.get('destination').value;

    this.searchParams = new SearchParams(destination, checkInDate, checkOutDate, this.numOfPeople);

    this.searchService.getSearchResults(this.searchParams).subscribe(
      data => {
        //alert('nesto se desilo kao');
        this.rooms = data;
        this.roomsCopy = data;
        this.DomSanitizer.bypassSecurityTrustUrl(this.rooms[0].image[0]);
        alert(this.rooms[0].location.name)
      },
      error => {
        //alert('greska u pretrazivanju soba');
      }
    );
  }

  resetFilter() {
    console.log('resetovanje parametara filtriranja...');

    for (let i = 0; i < this.types.length; i++) {
      this.types[i].active = true;
    }
    for (let j = 0; j < this.categories.length; j++) {
      this.categories[j].active = true;
    }
    for (let k = 0; k < this.additionalServices.length; k++) {
      this.additionalServices[k].active = true;
    }

    this.roomsCopy = this.rooms;
  }

  filterClicked() {
    console.log('clicked on room filter...');
    this.FilterAll();
    console.log('sobe su isfiltrirane...');
    for (let r of this.roomsCopy) {
      console.log(r.location.name);
    }
  }

  ChangeMeType(i) {
    if (this.types[i].active) {
      this.types[i].active = false;
    } else {
      this.types[i].active = true;
    }
    alert(this.types[i].active);
  }

  ChangeMeCategories(i) {
    if (this.categories[i].active) {
      this.categories[i].active = false;
    } else {
      this.categories[i].active = true;
    }
    alert(this.categories[i].active);
  }

  ChangeMeAdditionalServices(i) {
    if (this.additionalServices[i].active) {
      this.additionalServices[i].active = false;
    } else {
      this.additionalServices[i].active = true;
    }
    alert(this.additionalServices[i].active);
  }

  FilterAll() {
    //pronadji sve sto je aktivno sto se tice tipa
    const tipovi = Array<FilterField>();
    const categories = Array<FilterField>();
    const additionalServices = Array<FilterField>();

    this.types.forEach(element => {
      if (element.active == false) {
        tipovi.push(element);
      }
    });

    alert(tipovi.length);

    this.categories.forEach(element => {
      if (element.active == false) {
        categories.push(element);
      }
    });
    alert(categories.length);

    this.additionalServices.forEach(element => {
      if (element.active == false) {
        additionalServices.push(element);
      }
    })
    alert(additionalServices.length)

    //filtriraj po
    this.FilterByType(tipovi, categories, additionalServices);

  }

  public FilterByType(types, categories, additionalServices) {

    this.roomsCopy = new Array<any>();

    this.rooms.forEach(element => {
      let booleanRoom = false;

      types.forEach(tipovi => {
        if (element.accommodationType.description == tipovi.description) {
          booleanRoom = true;
          alert('pronasao u tipovima');
        }
      });

      categories.forEach(kategorije => {
        if (element.accommodationCategory.description == kategorije.description) {
          booleanRoom = true;
          alert('pronasao u kategorijama');
        }
      });

      if (additionalServices == null) {
        booleanRoom = true;
        alert('nema servisa');
      } else {
        additionalServices.forEach(servisi => {
          if (element.additionalService.description == servisi.description) {
            booleanRoom = true;
            alert('pronasao u servisima');
          }
        });
      }

      if (booleanRoom) {
        this.roomsCopy.push(element);
        alert('dodata bar jedna soba');
      }
    });
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
        this.roomsCopy = data;
        this.DomSanitizer.bypassSecurityTrustUrl(this.rooms[0].image[0]);
        alert(this.rooms[0].location.name)
      },
      error => {
        //alert('jebem ti se s mamom');


      }
    );
  }

  reservation(roomId) {
    this.router.navigate(['/reservation/' + this.searchForm.get("checkIn").value + "/" + this.searchForm.get("checkOut").value + "/" + roomId]);
  }

}
