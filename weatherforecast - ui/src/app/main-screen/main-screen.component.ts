import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { WeatherService } from '../shared/weather.service';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-main-screen',
  templateUrl: './main-screen.component.html',
  styleUrls: ['./main-screen.component.css']
})
export class MainScreenComponent implements OnInit {

  myControl = new FormControl();
  value: string;
  cities: any[];
  displayedColumns: string[] = ['city', 'country', 'details'];
  dataSource = new MatTableDataSource();

  constructor(private weatherService: WeatherService) { }

  ngOnInit() {
    this.findAll();
  }

  findByName(value: any) {
    var values = '';
    values += value;

    if (values.length > 4) {
      this.weatherService.findByName(values).subscribe(res => {
        this.cities = res;
      });
    }

  }

  findAll() {
    this.weatherService.findAll().subscribe(res => {
      this.dataSource.data = res;
    });
  }

  test(event) {
    console.log(event);
  }

  displayFn(subject) {
    return subject ? `${subject.name}, ${subject.country}` : undefined;
  }



}
