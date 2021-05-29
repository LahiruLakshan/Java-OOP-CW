// @ts-ignore
import { NgModule } from '@angular/core';
// @ts-ignore
import { Routes, RouterModule } from '@angular/router';
import {RankingTableComponent} from './components/ranking-table/ranking-table.component';
import {AllMatchesTableComponent} from './components/all-matches-table/all-matches-table.component';
import {RandomMatchesComponent} from './components/random-matches/random-matches.component';
import {HomePageComponent} from './components/home-page/home-page.component';

const routes: Routes = [
  {path: 'home-page', component: HomePageComponent},
  {path: 'ranking-table', component: RankingTableComponent},
  {path: 'all-matches-table', component: AllMatchesTableComponent},
  {path: 'random-matches-table', component: RandomMatchesComponent},
  {path: '', redirectTo: '/home-page', pathMatch: 'full'}
];

// @ts-ignore
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
