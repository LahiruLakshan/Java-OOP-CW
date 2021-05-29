// @ts-ignore
import { BrowserModule } from '@angular/platform-browser';
// @ts-ignore
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RankingTableComponent } from './components/ranking-table/ranking-table.component';
// @ts-ignore
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTableModule} from '@angular/material/table';
// @ts-ignore
import {HttpClientModule} from '@angular/common/http';
import {MatLineModule} from '@angular/material/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { AllMatchesTableComponent } from './components/all-matches-table/all-matches-table.component';
import {MatFormFieldModule} from '@angular/material/form-field';
// @ts-ignore
import {MatInputModule} from '@angular/material/input';
// @ts-ignore
import {FormsModule} from '@angular/forms';
import {MatGridListModule} from '@angular/material/grid-list';
import { RandomMatchesComponent } from './components/random-matches/random-matches.component';
import { HomePageComponent } from './components/home-page/home-page.component';

// @ts-ignore
@NgModule({
  declarations: [
    AppComponent,
    RankingTableComponent,
    AllMatchesTableComponent,
    RandomMatchesComponent,
    HomePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTableModule,
    HttpClientModule,
    MatLineModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatGridListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
