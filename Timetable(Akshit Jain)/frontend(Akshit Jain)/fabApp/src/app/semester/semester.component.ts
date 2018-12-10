import { Component, OnInit, HostListener } from '@angular/core';
import { LocalDataSource } from 'ng2-smart-table';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ModalserviceService } from '../modalservice.service';
import { DefaultEditor, ViewCell } from 'ng2-smart-table';
import {FabService} from '../fab.service';

@Component({
  selector: 'app-semester',
  templateUrl: './semester.component.html',
  styleUrls: ['./semester.component.css']
})
export class SemesterComponent implements OnInit {
  source: LocalDataSource;
  formGroup: FormGroup;
  constructor(private router : Router, private fabService: FabService, private fb: FormBuilder, private modalservice: ModalserviceService) { 
    this.formGroup = this.fb.group({
      semesterName: ['', [Validators.required]],
      startOfSem: ['', [Validators.required]],
      isSaturdayWorking: ['', [Validators.required]]
    })
  }
  settings = {
    actions: {
      position: 'right',
      columnTitle: 'ADD',
      class: '',
      delete: true,
      edit: false
    },
    hideSubHeader: true,
    attr: {
      class: ''
    },
    columns: {
      semesterName: {
        title: 'Semester',
        filter: false
      },
      startOfSem: {
        title : 'startOfSem',
        filter: false
      },  
      isSaturdayWorking: {
        title: 'isSaturdayWorking',
        defaultValue: true,
        filter: false,
        editor: {
          type: 'list',
          config: {
            list: [{ value: true, title: true }, { value: false, title: false }]
          }
        }
      },
    }
  };
  data = [{
    semesterName : "one",
    startOfSem: "22/11/1999",
    isSaturdayWorking: false
    },{
      semesterName : "one",
      startOfSem: "22/11/1999",
      isSaturdayWorking: false
      },{
        semesterName : "one",
        startOfSem: "22/11/1999",
        isSaturdayWorking: false
        },]
  ngOnInit() {
    // this.source = new LocalDataSource(this.data);
    console.log('nside')
    this.getAllSemester();
  }
  selectSemester(event) { 
    console.log(event);
    this.router.navigate(['/holidays'],{ queryParams: { id: event.data.semesterId }} );
  }
  addNew(id) {
    this.modalservice.open(id);
  }
  createNew() {
    console.log(this.formGroup.value);
    this.fabService.addSemester(this.formGroup.value).subscribe(res=>{
      console.log(res);
      
    })
    this.modalservice.close('addNew');
  }
  justClose(id: string) {
    this.modalservice.close(id);
  }
  @HostListener('click')
  didTapOnComponent($event) {
    const target: any = event.target;
    if (target.className === 'ng2-smart-title' && target.parentElement.className === 'ng2-smart-actions') {
      this.addNew('addNew');
    }

  }


  // semester 

  getAllSemester() {
    this.fabService.getAllSemester().subscribe(res => {
      console.log(res, 'ersponse');
    this.source = new LocalDataSource(res);

    })
  }
  removeSemester(id) {
    this.fabService.deleteSemester(id).subscribe(res => {
      console.log(res, 'response');
    })
  }
  addSemester() {
    this.fabService.addSemester(this.formGroup.value).subscribe(res => {
      console.log(res, 'response');
    })
  }
  getSemester(id) {
    this.fabService.getSemester(id).subscribe(res => {
      console.log(res, 'ersponse');
    })
  }
}
