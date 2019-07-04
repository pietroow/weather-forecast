import { NgModule } from '@angular/core';
import { RouterModule, Route } from '@angular/router';

import { MainScreenComponent } from './main-screen/main-screen.component';
import { DetailsScreenComponent } from './details-screen/details-screen.component';

const routes: Route[] = [
  { path: '', component: MainScreenComponent },
  { path: 'main', component: MainScreenComponent },
  { path: 'details/:id', component: DetailsScreenComponent }
]


@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class AppRoutingModule { }
