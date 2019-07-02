import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { WeatherService } from '../shared/weather.service';
import { MatTableDataSource, MatSnackBar } from '@angular/material';
import { RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-main-screen',
  templateUrl: './main-screen.component.html',
  styleUrls: ['./main-screen.component.css']
})
export class MainScreenComponent implements OnInit {

  cityId: number;
  myControl = new FormControl();
  searchValue: string;
  cities: any[];
  displayedColumns: string[] = ['city', 'country', 'details', 'delete'];
  dataSource = new MatTableDataSource();

  constructor(
    private weatherService: WeatherService,
    private _snackBar: MatSnackBar,
    private _route: Router
  ) { }

  ngOnInit() {
    this.findAll();
  }

  findByName() {
    if (this.searchValue.length >= 2) {
      this.weatherService.findByName(this.searchValue).subscribe(res => {
        const citiesRegistered = this.dataSource.data;
        const citiesRegisteredIds = citiesRegistered.map<string>((obj:any) => obj.city_open_weather.id);
        this.cities = res.filter( obj => !citiesRegisteredIds.some(id => id === obj.id));
      });
    }
  }

  findAll() {
    this.weatherService.findAll().subscribe(res => {
      this.dataSource.data = res;
    });
  }

  displayFn(subject) {
    return subject ? `${subject.name}, ${subject.country}` : undefined;
  }

  citySelected(event: any) {
    this.cityId = event.option.value.id;
  }

  registerCity() {

    if(this.cityId){
      this.weatherService.registerCity(this.cityId).subscribe(city => {
        this._snackBar.open('City successfully registered', null, { duration: 3000 });
        this.cityId = null;
        this.searchValue = '';
        this.cities = [];
        this.findAll();
      },
        err => {
          this._snackBar.open(err.error.message, null, { duration: 3000 });
        });
    }

  }

  deleteById(cityId: number) {
    this.weatherService.deleteById(cityId).subscribe(res => {
      this.findAll();
    });
  }

  detail(cityId: number) {
    console.log(cityId);
    this.weatherService.detail(cityId).subscribe(res => {
      this._route.navigate([`details/${cityId}`]);
    });
  }

}
