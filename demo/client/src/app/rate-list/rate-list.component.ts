import { Component, OnInit } from '@angular/core';
import {RateService} from "../shared";

@Component({
  selector: 'app-rate-list',
  templateUrl: './rate-list.component.html',
  styleUrls: ['./rate-list.component.css'],
  providers: [RateService]
})
export class RateListComponent implements OnInit {
  rates: Array<any>;
  constructor(private rateService: RateService) { }

  ngOnInit() {

    this.rateService.getAll().subscribe(
      data => {
        this.rates = data;
      },
      error => console.log(error)
    )
  }

}
