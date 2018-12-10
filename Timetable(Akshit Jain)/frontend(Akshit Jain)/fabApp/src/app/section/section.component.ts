import { ModalserviceService } from './../modalservice.service';
import { Component, OnInit, ElementRef, HostListener } from '@angular/core';
import { FabService } from '../fab.service';
import { LocalDataSource } from 'ng2-smart-table';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {
  source: LocalDataSource;
  form: FormGroup;
  constructor(private fabService: FabService, private elementref: ElementRef, private modalservice: ModalserviceService, private router: Router, private routerSnapshot: ActivatedRoute) {
    this.form = new FormGroup({
      sectionName: new FormControl('', Validators.required)
    });
  }
  settings = {
    actions: {
      position: 'right',
      columnTitle: 'ADD',
      class: '',
      edit: false
    },
    hideSubHeader: true,
    attr: {
      class: ''
    },
    delete: {
      confirmDelete: true,
      deleteButtonContent: '<i class="glyphicon glyphicon-trash"></i>'
    },
    columns: {
      sectionName: {
        title: 'sectionName',
        filter: false
      }
    }
  };
  data;
  classId;
  ngOnInit() {
    this.routerSnapshot.queryParams.subscribe(res=>{
      console.log(res);
      if(res.id) {
        this.classId = res.id
        this.getSection(res.id);
      }
      
    })
  }
  createNew() {
    console.log(this.form.value);
    this.fabService.addSection(this.classId, this.form.value).subscribe(res=>{
      console.log(res);
      
    })
    this.modalservice.close('addNew');
  }

  getSection(id) {
    this.fabService.getSection(id).subscribe(res=>{
      console.log(res);
      this.data = new LocalDataSource(res)
    })
  }
  addNew(id) {
    this.modalservice.open(id);
  }
  @HostListener('click')
  didTapOnComponent($event) {
    const target: any = event.target;
    if (target.className === 'ng2-smart-title' && target.parentElement.className === 'ng2-smart-actions') {
      this.addNew('addNew');
    }
  }
  justClose(id: string) {
    this.modalservice.close(id);
  }
  deleteSection(event) {
    console.log(event);
    
    this.fabService.removeSection(this.classId,event.data.sectionId).subscribe(res=>{
      console.log(res);
      
    })
  }
}
