import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {SearchService} from '../../services/search.service';
import {SearchParams} from '../../models/search-params';
import {FormControl, FormGroup, Validators} from '@angular/forms';

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

  constructor(private route: ActivatedRoute, private searchService: SearchService) { }

  ngOnInit() {

    this.destination = this.route.snapshot.paramMap.get('destination');
    const checkIn = this.route.snapshot.paramMap.get('checkIn');
    alert(checkIn);
    const checkInDate = new Date(checkIn);
    const checkOut = this.route.snapshot.paramMap.get('checkOut');
    alert(checkOut);
    const checkOutDate = new Date(checkOut);
    this.numOfPeople = +this.route.snapshot.paramMap.get('numOfPeople');
    alert(this.numOfPeople);

    alert(this.destination);

    if (this.destination === 'null') {
      this.destination = '';
    }

    alert(this.destination);

    this.searchParams = new SearchParams(this.destination, checkInDate, checkOutDate, this.numOfPeople);
    this.searchService.getSearchResults(this.searchParams).subscribe(
      data => {
        alert('nesto se desilo kao');
      },
      error => {
        alert('jebem ti se s mamom');
      }
    );
  }

}
