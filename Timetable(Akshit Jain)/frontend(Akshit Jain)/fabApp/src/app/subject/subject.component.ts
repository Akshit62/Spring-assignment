import { DataexchangeService } from '../dataexchange.service';
import { ModalserviceService } from '../modalservice.service';
import { Component, OnInit, HostListener } from '@angular/core';
import { LocalDataSource } from 'ng2-smart-table';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FabService } from '../fab.service';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css']
})
export class SubjectComponent implements OnInit {
  source: LocalDataSource;
  form: FormGroup;
  constructor(private modalservice: ModalserviceService, private fabService: FabService, private router: Router, private dataExchange: DataexchangeService) {
    this.form = new FormGroup({
      Name: new FormControl('', Validators.required),
      Content: new FormControl('', Validators.required),
      Type: new FormControl('', Validators.required)
    });

  }
  settings = {
    actions: {
      columnTitle: 'ADD',
      position: 'right',
      edit : false
    },
    attr: {
      class: ''
    },
    add: {
      confirmCreate: true,
    },
    delete: {
      confirmDelete: true,
      deleteButtonContent : '<i class="glyphicon glyphicon-trash"></i>'
    },
    columns: {
      subjectName: {
        title: 'Subject Name',
        filter: false
      },
    },
    hideSubHeader: true
  };
  data = [{
    SubjectName : "sample"
  }];

  ngOnInit() {
    // this.source = new LocalDataSource(this.data);
    this.getAllSubjects();
  }


  getAllSubjects() {
    this.fabService.getAllSubject().subscribe(res => {
      console.log(res, 'response');
      this.source = new LocalDataSource(res);
    })
  }
  deleteSubject(id) {
    this.fabService.removeSubject(id).subscribe(res => {
      console.log(res, 'response');
    })
  }
  addSubject() {
    this.fabService.addSubject(this.form.value).subscribe(res => {
      console.log(res, 'response');
    })
  }
  getSubject(id) {
    this.fabService.getSubject(id).subscribe(res => {
      console.log(res, 'response');
    })
  }


  createNew() {
    console.log(this.form.value);

  }

  editRow(event) {
    console.log(event);
    this.dataExchange.setData(event);
    this.router.navigate(['/editnotifications']);
  }

  addNew(id) {
    this.modalservice.open(id);
  }
  selectRow(event) {
    console.log(event, 'event');
  }
  onSearch(query) {
    this.source.setFilter([
      // fields we want to include in the search
      {
        field: 'Name',
        search: query
      }
    ]);
  }

  deleteNotification(data) {
    console.log(data);
  }
  @HostListener('click')
  didTapOnComponent($event) {
    const target: any = event.target;
    if (target.className === 'ng2-smart-title' && target.parentElement.className === 'ng2-smart-actions') {
      this.addNew('addNew');
    }

  }
  selectSubject(event) {
    console.log(event);
    this.router.navigate(['/teachers'], { queryParams: { id: event.data.subjectId }});
  }

  justClose(id: string) {
    this.modalservice.close(id);
  }
}

