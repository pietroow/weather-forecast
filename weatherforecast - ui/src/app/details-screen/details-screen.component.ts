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
  minTemperatures: any[];
  maxTemperatures: any[];
  weatherDescription: any[];

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
      this.getGraphicData();
      this.mountWeatherForecast();
    })
      .catch(err => {

      });
  }

  getGraphicData() {
    console.log(this.detail);
    const dates = this.detail.map(obj => obj.date);
    this.minTemperatures = this.detail.map(obj => obj.temp_min);
    this.maxTemperatures = this.detail.map(obj => obj.temp_max);
    // this.weatherDescription = this.detail.list.map(obj => obj.weather[0].main);

    var options = { day: 'numeric', month: 'long', weekday: 'long'};

    let datasformatadas = [];
    dates.forEach(element => {
      let date = new Date(element);
      datasformatadas.push(date.toLocaleDateString('pt', options));
    });
    this.columns = datasformatadas;
  }


  mountWeatherForecast() {
    this.weatherChart = new Chart('myChart', {
      type: 'bar',
      data: {
        labels: this.columns,
        datasets: [
          {
            label: 'Max Temperature',
            data: this.maxTemperatures,
            backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
            ],
            borderColor: [
              'rgba(255, 99, 132, 1)',
            ],
            borderWidth: 1,
            type: 'bar'
          },
          {
            label: 'Min Temperature',
            data: this.minTemperatures,
            backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
            ],
            borderColor: [
              'rgba(255, 99, 132, 1)',
            ],
            borderWidth: 1,
            type: 'bar'
          },
        ]
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
