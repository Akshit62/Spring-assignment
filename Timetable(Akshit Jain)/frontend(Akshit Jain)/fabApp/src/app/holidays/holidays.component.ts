import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FabService } from '../fab.service';

@Component({
  selector: 'app-holidays',
  templateUrl: './holidays.component.html',
  styleUrls: ['./holidays.component.css']
})
export class HolidaysComponent implements OnInit {

  constructor(private routersnap: ActivatedRoute, private fabservice: FabService) { }
nonWorkingDays;
publicHolidays;
saturdayHolidays;
sundayHolidays;
  ngOnInit() {
    this.routersnap.queryParams.subscribe(res=>{
      if(res.id) {
        this.fabservice.getHolidays(res.id).subscribe(res=>{
          console.log(res);
          this.nonWorkingDays = res['Non-Working Days'];
          this.publicHolidays = res['Public Holidays'];
          this.saturdayHolidays = res['Saturday Holidays'];
          this.sundayHolidays = res['Sunday Holidays'];
          console.log(this.nonWorkingDays);
          
        })
      }
    })
  }

}
