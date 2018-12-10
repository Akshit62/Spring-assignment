import { Component, OnInit } from '@angular/core';
import { FabService } from '../fab.service';

@Component({
  selector: 'app-timetable',
  templateUrl: './timetable.component.html',
  styleUrls: ['./timetable.component.css']
})
export class TimetableComponent implements OnInit {

  constructor(private fabservice: FabService) { }
allClasses;
class = "";
section = "";
classOnly = '';
  ngOnInit() {
    this.fabservice.getAllClass().subscribe(res=>{
      this.allClasses = res
    })
  }

}
