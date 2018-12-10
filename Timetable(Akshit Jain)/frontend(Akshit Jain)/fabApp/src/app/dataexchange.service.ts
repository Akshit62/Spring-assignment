import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataexchangeService {
data: any;
  constructor() { }
  getData(): any {
    return this.data;
  }

  setData(data) {
    this.data = data;
  }
}
