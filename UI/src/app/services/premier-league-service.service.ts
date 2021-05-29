// @ts-ignore
import { Injectable } from '@angular/core';
// @ts-ignore
import {HttpClient} from '@angular/common/http';
// @ts-ignore
import {Observable} from 'rxjs';

// @ts-ignore
@Injectable({
  providedIn: 'root'
})
export class PremierLeagueServiceService {

  constructor(private http: HttpClient) {}

  public getRankingTable(): Observable<any>{
    return this.http.get('http://localhost:9000/ranking-table');
  }

  public getAllMatches(): Observable<any>{
    return this.http.get('http://localhost:9000/match-table');
  }

  public getDateSort(): Observable<any>{
    return this.http.get('http://localhost:9000/date-sort');
  }

  public getRandomTable(): Observable<any>{
    return this.http.get('http://localhost:9000/random-match');
  }

  public getRandomPlayMatch(): Observable<any>{
    return this.http.get('http://localhost:9000/random-play-match');
  }
  public getRandomMatchDateSort(): Observable<any>{
    return this.http.get('http://localhost:9000/random-match-date-sort');
  }
  public getPointsSort(): Observable<any>{
    return this.http.get('http://localhost:9000/points-sort');
  }
  public getGoalsSort(): Observable<any>{
    return this.http.get('http://localhost:9000/goals-sort');
  }
  public getWinsSort(): Observable<any>{
    return this.http.get('http://localhost:9000/wins-sort');
  }

}
