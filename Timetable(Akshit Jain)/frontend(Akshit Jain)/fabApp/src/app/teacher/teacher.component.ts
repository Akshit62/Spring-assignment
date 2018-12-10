import { ModalserviceService } from '../modalservice.service';
import { Component, OnInit } from '@angular/core';
import { LocalDataSource } from 'ng2-smart-table';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { FabService } from '../fab.service';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css']
})
export class TeacherComponent implements OnInit {
  form: FormGroup;
  constructor(private fabService: FabService, private modalservice: ModalserviceService, private routesnap: ActivatedRoute) { 
    this.form = new FormGroup({
      Name: new FormControl('', Validators.required),
      Content: new FormControl('', Validators.required),
      Type: new FormControl('', Validators.required)
    });
  }
  source;
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
      teacherName: {
        title: 'Teacher Name',
        filter: false
      },
    },
    hideSubHeader: true
  };
  data = [{
    TeacherName : "sample"
  }];
  ngOnInit() {
    this.routesnap.queryParams.subscribe(res=>{
      if(res.id) {
        this.getTeacher(res.id);
      }
    })
  }
  deleteTeacher(teacherId, subjectId) {
    this.fabService.removeTeacher(teacherId,subjectId).subscribe(res => {
      console.log(res, 'response');
    })
  }
  addTeacher(subjectId) {
    this.fabService.addTeacher(subjectId, this.form.value).subscribe(res => {
      console.log(res, 'response');
    })
  }
  getTeacher(id) {
    this.fabService.getTeacher(id).subscribe(res => {
      console.log(res, 'response');
    this.source = new LocalDataSource(res);

    })
  }


}
