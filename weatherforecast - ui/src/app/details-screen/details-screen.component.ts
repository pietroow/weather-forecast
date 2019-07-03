import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { WeatherService } from '../shared/weather.service';
import { Chart } from 'chart.js';


@Component({
  selector: 'app-details-screen',
  templateUrl: './details-screen.component.html',
  styleUrls: ['./details-screen.component.css']
})
export class DetailsScreenComponent implements OnInit {

  city: {};
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

  backPage() {
    this._router.navigate(['']);
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
    const dates = this.detail.list.map(obj => obj.date);
    this.minTemperatures = this.detail.list.map(obj => obj.temp_min);
    this.maxTemperatures = this.detail.list.map(obj => obj.temp_max);

    this.city = {
      name: this.detail.city.name,
      country: this.detail.city.country
    }
    // console.log(this.detail.city.name);
    // this.weatherDescription = this.detail.list.map(obj => obj.weather[0].main);

    var options = { day: 'numeric', month: 'long', weekday: 'long' };

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
              'rgba(255, 99, 132, 0.2)',
              'rgba(255, 99, 132, 0.2)',
              'rgba(255, 99, 132, 0.2)',
              'rgba(255, 99, 132, 0.2)',
              'rgba(255, 99, 132, 0.2)',
            ],
            borderColor: [
              'rgba(255, 99, 132, 1)',
              'rgba(255, 99, 132, 1)',
              'rgba(255, 99, 132, 1)',
              'rgba(255, 99, 132, 1)',
              'rgba(255, 99, 132, 1)',
              'rgba(255, 99, 132, 1)',
            ],
            borderWidth: 1,
            type: 'bar'
          },
          {
            label: 'Max Temperature - Line',
            data: this.maxTemperatures,
            fill: false,
            backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
              'rgba(255, 99, 132, 0.2)',
              'rgba(255, 99, 132, 0.2)',
              'rgba(255, 99, 132, 0.2)',
              'rgba(255, 99, 132, 0.2)',
              'rgba(255, 99, 132, 0.2)',
            ],
            borderColor: [
              'rgba(255, 99, 132, 1)',
              'rgba(255, 99, 132, 1)',
              'rgba(255, 99, 132, 1)',
              'rgba(255, 99, 132, 1)',
              'rgba(255, 99, 132, 1)',
              'rgba(255, 99, 132, 1)',
            ],
            borderWidth: 1,
            type: 'line'
          },
          {
            label: 'Min Temperature',
            data: this.minTemperatures,
            backgroundColor: [
              'rgba(0, 255, 204, 0.2)',
              'rgba(0, 255, 204, 0.2)',
              'rgba(0, 255, 204, 0.2)',
              'rgba(0, 255, 204, 0.2)',
              'rgba(0, 255, 204, 0.2)',
              'rgba(0, 255, 204, 0.2)',
            ],
            borderColor: [
              'rgba(0, 255, 204, 1)',
              'rgba(0, 255, 204, 1)',
              'rgba(0, 255, 204, 1)',
              'rgba(0, 255, 204, 1)',
              'rgba(0, 255, 204, 1)',
              'rgba(0, 255, 204, 1)',
            ],
            borderWidth: 1,
            type: 'bar'
          },
          {
            label: 'Min Temperature - Line',
            data: this.minTemperatures,
            fill: false,
            backgroundColor: [
              'rgba(0, 255, 204, 0.2)',
              'rgba(0, 255, 204, 0.2)',
              'rgba(0, 255, 204, 0.2)',
              'rgba(0, 255, 204, 0.2)',
              'rgba(0, 255, 204, 0.2)',
              'rgba(0, 255, 204, 0.2)',
            ],
            borderColor: [
              'rgba(0, 255, 204, 1)',
              'rgba(0, 255, 204, 1)',
              'rgba(0, 255, 204, 1)',
              'rgba(0, 255, 204, 1)',
              'rgba(0, 255, 204, 1)',
              'rgba(0, 255, 204, 1)',
            ],
            borderWidth: 1,
            type: 'line'
          }
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
