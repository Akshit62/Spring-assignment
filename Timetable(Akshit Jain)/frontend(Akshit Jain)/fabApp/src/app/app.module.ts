import { BrowserModule } from '@angular/platform-browser';
import { NgModule} from '@angular/core';
import { Ng2SmartTableModule } from 'ng2-smart-table';


import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { Constants } from './constants';
import { RouterModule} from '@angular/router';


import { SectionComponent } from './section/section.component';
import { TeacherComponent } from './teacher/teacher.component';

import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { ModalComponent } from './modal/modal.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { SubjectComponent } from './subject/subject.component';
import { SemesterComponent } from './semester/semester.component';
import { ClassComponent } from './class/class.component';

import { routes } from './routes';
import { HomeComponent } from './home/home.component';
import { HolidaysComponent } from './holidays/holidays.component';
import { TimetableComponent } from './timetable/timetable.component';

@NgModule({
  declarations: [
    AppComponent,
    ClassComponent,
    SectionComponent,
    TeacherComponent,
    ModalComponent,
    PagenotfoundComponent,
    SemesterComponent,
    SubjectComponent,
    HomeComponent,
    HolidaysComponent,
    TimetableComponent,
  ],
  imports: [
    BrowserModule,
    Ng2SmartTableModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [Constants],
  bootstrap: [AppComponent]
})
export class AppModule { }
