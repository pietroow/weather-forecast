import { Injectable } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { htmlAstToRender3Ast } from '@angular/compiler/src/render3/r3_template_transform';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  url = 'http://localhost:8190/city';

  constructor(private http: HttpClient) { }

  findByName(value: string){
    return this.http.get<any>(`${this.url}?name=${value}`);
  }

  findAll() {
    return this.http.get<any>(`${this.url}/local`);
  }
}
