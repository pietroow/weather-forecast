import { Component, OnInit, AfterViewInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { WeatherService } from '../shared/weather.service';
import { Chart } from 'chart.js';


@Component({
  selector: 'app-details-screen',
  templateUrl: './details-screen.component.html',
  styleUrls: ['./details-screen.component.css']
})
export class DetailsScreenComponent implements OnInit {

  cityId: number;
  detail: any;
  weatherChart: any;
  columns: any[];
  temperatures: any[];

  constructor(
    private _activatedRoute: ActivatedRoute,
    private _router: Router,
    private _weatherService: WeatherService
  ) { }

  ngOnInit() {
    this._activatedRoute.params.subscribe(res => {
      if (!res.id) {
        this._router.navigate(['']);
      }
      this.cityId = res.id;
      this.loadWeatherForecast();
    });
  }

  loadWeatherForecast() {
    this._weatherService.detail(this.cityId).then(res => {
      this.detail = res;
      this.manipulateDays();
      this.manipulateTemperature();
      this.mountWeatherForecast();
    })
      .catch(err => {

      });
  }

  manipulateDays() {
    this.columns = this.detail.list.map( obj => obj.dt_txt);
  }

  manipulateTemperature() {
    this.temperatures = this.detail.list.map( obj => obj.main.temp_max);
  }

  mountWeatherForecast() {
    this.weatherChart = new Chart('myChart', {
      type: 'line',
      data: {
        labels: this.columns,
        datasets: [{
          label: 'Max Temperature',
          data: this.temperatures,
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 206, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(153, 102, 255, 0.2)',
            'rgba(255, 159, 64, 0.2)'
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)',
            'rgba(255, 159, 64, 1)'
          ],
          borderWidth: 1,
          type: 'line'
        }]
      },
      options: {
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        }
      }
    });
  }



}
