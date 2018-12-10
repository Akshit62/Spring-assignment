import { ModalserviceService } from './../modalservice.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { LocalDataSource } from 'ng2-smart-table';
import { FabService } from '../fab.service';
import { Component, OnInit, ElementRef, HostListener } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-class',
  templateUrl: './class.component.html',
  styleUrls: ['./class.component.css']
})
export class ClassComponent implements OnInit {
  source: LocalDataSource;
  form: FormGroup;
  constructor(private fabService: FabService, private elementref: ElementRef, private modalservice: ModalserviceService, private router: Router) {
    this.form = new FormGroup({
      className: new FormControl('', Validators.required)
    });
  }
  settings = {
    hideSubHeader: true,
    actions: {
      columnTitle: 'ADD',
      position: 'right',
      edit: false
    },
    attr: {
      class: ''
    },
    add: {
      confirmCreate: true,
    },
    delete: {
      confirmDelete: true,
      deleteButtonContent: '<i class="glyphicon glyphicon-trash"></i>'
    },
    columns: {
      className: {
        title: 'Name',
        filter: false
      }
    }
  };
  data = [{
    className : "kartik"
  }];
  ngOnInit() {
    this.getAllClasses();
    // this.source = new LocalDataSource(this.data);
  }
  onSearch(query) {
    this.source.setFilter([
      // fields we want to include in the search
      {
        field: 'area_code',
        search: query
      }
    ]);
  }
  createNew() {
    this.modalservice.close('addNew');
       this.fabService.addClass(this.form.value).subscribe(res => {
      if (res.success) {

        // this.elementref.nativeElement.querySelector('.ng2-smart-action-add-cancel').click();
        this.getAllClasses();
      }
    })

  }
  getAllClasses() {
        this.fabService.getAllClass().subscribe(res => {
      console.log(res, 'all Classes');
      this.source = new LocalDataSource(res);
    });
  }
  removeClass(data) {
    var confirmDel = confirm("Are you sure?");
    if (confirmDel) {
            this.fabService.removeClass(data.id).subscribe(res => {
        if (res.success) {
          this.getAllClasses();
        }
      });
    }
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
  selectClass(event) {
    console.log(event, "evet");
    
    this.router.navigate(['/sections'], { queryParams: { id: event.data.classId }} )
  }
}
