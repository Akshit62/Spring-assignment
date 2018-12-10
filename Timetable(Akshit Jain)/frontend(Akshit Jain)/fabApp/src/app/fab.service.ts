import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { map } from 'rxjs/operators/map';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Constants } from './constants';
// import {RequestOptions } from '@angular/http'
@Injectable({
  providedIn: 'root'
})
export class FabService {

  constructor(private http: HttpClient, private config: Constants) { }
  
  // Semseters
  getAllSemester(): Observable<any> {
    return this.http.get(this.config.baseUrl + this.config.apiUrls.semester).pipe(map(result => result));
  }
  addSemester(code): Observable<any> {
    return this.http.post(this.config.baseUrl + this.config.apiUrls.semester, code).pipe(map(result => result));
  }
  deleteSemester(id): Observable<any> {
    return this.http.delete(this.config.baseUrl + this.config.apiUrls.semester + '/' + id).pipe(map(result => result));
  }
  getSemester(id): Observable<any> {
    return this.http.get(this.config.baseUrl + this.config.apiUrls.semester + '/' + id).pipe(map(result => result));
  }


  // Holidays
  addHolidays(id, data): Observable<any> {
    return this.http.post(this.config.baseUrl + this.config.apiUrls.semester + '/' + id + '/holiday', data).pipe(map(result => result));
  }
  getHolidays(id): Observable<any> {
    return this.http.get(this.config.baseUrl + this.config.apiUrls.semester + '/' + id + '/holiday').pipe(map(result => result));
  }
  removeHolidays(semId, holidayId): Observable<any> {
    return this.http.delete(this.config.baseUrl + this.config.apiUrls.semester + semId + '/holiday/' + holidayId).pipe(map(result => result));
  }
  getWorkingDays(id) {
    return this.http.get(this.config.baseUrl + this.config.apiUrls.semester + '/' + id + '/working').pipe(map(result => result));
  }


  // class

  addClass(data): Observable<any> {
    let headers =  {headers: new  HttpHeaders({ 'Content-Type': 'application/json'})};
    // const options = new RequestOptions({headers:headers});
    return this.http.post(this.config.baseUrl + this.config.apiUrls.class, data, headers).pipe(map(result => result));
  }
  removeClass(id): Observable<any> {
    return this.http.delete(this.config.baseUrl + this.config.apiUrls.class + '/' + id).pipe(map(result => result));
  }
  getAllClass(): Observable<any> {
    return this.http.get(this.config.baseUrl + this.config.apiUrls.class).pipe(map(result => result));
  }
  getClass(id): Observable<any> {
    return this.http.get(this.config.baseUrl + this.config.apiUrls.class + '/' + id).pipe(map(result => result));
  }

  // section

  addSection(id, data): Observable<any> {
    console.log(id);
    
    return this.http.post(this.config.baseUrl + this.config.apiUrls.class + '/' + id + '/section', data).pipe(map(result => result));
  }
  removeSection(classId, sectionId): Observable<any> {
    return this.http.delete(this.config.baseUrl + this.config.apiUrls.class + '/' + classId + '/section/' + sectionId).pipe(map(result => result));
  }
  getSection(id): Observable<any> {
    return this.http.get(this.config.baseUrl + this.config.apiUrls.class + '/' + id + '/section').pipe(map(result => result));
  }


  // subject


  addSubject(data): Observable<any> {
    return this.http.post(this.config.baseUrl + this.config.apiUrls.subject, data).pipe(map(result => result));
  }
  removeSubject(id): Observable<any> {
    return this.http.delete(this.config.baseUrl + this.config.apiUrls.subject + '/' + id).pipe(map(result => result));
  }
  getAllSubject(): Observable<any> {
    return this.http.get(this.config.baseUrl + this.config.apiUrls.subject).pipe(map(result => result));
  }
  getSubject(id): Observable<any> {
    return this.http.get(this.config.baseUrl + this.config.apiUrls.subject + '/' + id).pipe(map(result => result));
  }

  // teacher

  addTeacher(subjectId, data): Observable<any> {
    return this.http.post(this.config.baseUrl + this.config.apiUrls.subject + '/' + subjectId + '/teacher', data).pipe(map(result => result));
  }
  removeTeacher(subjectId, teacherId): Observable<any> {
    return this.http.delete(this.config.baseUrl + this.config.apiUrls.subject + '/' + subjectId + '/teacher' + teacherId).pipe(map(result => result));
  }
  getTeacher(subjectId): Observable<any> {
    return this.http.get(this.config.baseUrl + this.config.apiUrls.subject + '/' + subjectId + '/teacher').pipe(map(result => result));
  }

  // timeTable

  addTimetableForSection(sectionId, data): Observable<any> {
    return this.http.post(this.config.baseUrl + this.config.apiUrls.section + '/' + sectionId + '/timetable', data).pipe(map(result => result));
  }
  addTimetableForClass(classId, data): Observable<any> {
    return this.http.post(this.config.baseUrl + this.config.apiUrls.class + '/' + classId + '/timetable', data).pipe(map(result => result));
  }
  addTimetableForSubject(subjectId, data): Observable<any> {
    return this.http.post(this.config.baseUrl + this.config.apiUrls.section + '/' + subjectId + '/timetable', data).pipe(map(result => result));
  }

  updateTimetableForSection(sectionId, data): Observable<any> {
    return this.http.put(this.config.baseUrl + this.config.apiUrls.section + '/' + sectionId + '/timetable', data).pipe(map(result => result));
  }
  updateTimetableForClass(classId, data): Observable<any> {
    return this.http.put(this.config.baseUrl + this.config.apiUrls.class + '/' + classId + '/timetable', data).pipe(map(result => result));
  }
  updateTimetableForSubject(subjectId, data): Observable<any> {
    return this.http.put(this.config.baseUrl + this.config.apiUrls.section + '/' + subjectId + '/timetable', data).pipe(map(result => result));
  }

  deleteTimetableForSection(sectionId, data): Observable<any> {
    return this.http.delete(this.config.baseUrl + this.config.apiUrls.section + '/' + sectionId + '/timetable', data).pipe(map(result => result));
  }
  deleteTimetableForClass(classId, data): Observable<any> {
    return this.http.delete(this.config.baseUrl + this.config.apiUrls.class + '/' + classId + '/timetable', data).pipe(map(result => result));
  }
  deleteTimetableForSubject(subjectId, data): Observable<any> {
    return this.http.delete(this.config.baseUrl + this.config.apiUrls.section + '/' + subjectId + '/timetable', data).pipe(map(result => result));
  }

  viewTimetableforSection(data): Observable<any> {
    return this.http.post(this.config.baseUrl + this.config.apiUrls.section + '/timetable', data).pipe(map(result => result));
  } // send teacher Id or subject Id according to the requirement

}
