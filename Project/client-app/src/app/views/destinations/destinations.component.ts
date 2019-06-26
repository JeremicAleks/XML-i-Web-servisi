import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {SearchService} from "../../services/search.service";
import {SearchParams} from "../../models/search-params";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-destinations',
  templateUrl: './destinations.component.html',
  styleUrls: ['./destinations.component.css']
})
export class DestinationsComponent implements OnInit {

  brojsoba: number;
  dest: string;
  searchParams: SearchParams;


  searchForm = new FormGroup({
    destination: new FormControl('', [Validators.required]),
    checkIn: new FormControl(new Date(''), [Validators.required]),
    checkOut: new FormControl(new Date(''), [Validators.required])
  })

  constructor(private route: ActivatedRoute, private searchService: SearchService) { }

  ngOnInit() {

    this.dest = this.route.snapshot.paramMap.get('destination');
    //this.brojsoba = this.route.snapshot.paramMap.get('numOfPeople');
    let ngbDate = this.searchForm.get("checkIn").value;
    let myDate = new Date(ngbDate.year, ngbDate.month-1, ngbDate.day);
    let ngbDate2 = this.searchForm.get("checkOut").value;
    let myDate2 = new Date(ngbDate2.year, ngbDate2.month-1, ngbDate2.day);
    this.searchParams = new SearchParams(this.searchForm.get("destination").value,myDate,myDate2,this.brojsoba);
    /*this.searchService.getSearchResults(this.searchParams).subscribe(
      data=>{
        alert('nesto se desilo kao ')
      }
    )*/
  }

}
