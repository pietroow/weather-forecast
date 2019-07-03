import { Injectable } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { htmlAstToRender3Ast } from '@angular/compiler/src/render3/r3_template_transform';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  url = 'http://192.168.194.15:8190/city';
  urlOpenWeather = 'http://192.168.194.15:8190/city/openWeather';

  constructor(private http: HttpClient) { }

  findByName(value: string) {
    return this.http.get<any>(`${this.url}?name=${value}`);
  }

  findAll() {
    return this.http.get<any>(`${this.url}/local`);
  }

  registerCity(cityId: number) {
    return this.http.post(this.url, {cityId: cityId});
  }

  deleteById(cityId: number){
    return this.http.delete(`${this.url}/${cityId}`);
  }

  detail(cityId: number) {
    return this.http.get<any>(`${this.urlOpenWeather}/${cityId}`).toPromise();
  }

}
