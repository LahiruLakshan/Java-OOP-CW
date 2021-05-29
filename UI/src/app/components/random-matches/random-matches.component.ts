// @ts-ignore
import {Component, HostListener, OnInit} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {PremierLeagueServiceService} from '../../services/premier-league-service.service';
import {MatchList} from '../../match-list';


// @ts-ignore
@Component({
  selector: 'app-random-matches',
  templateUrl: './random-matches.component.html',
  styleUrls: ['./random-matches.component.css']
})
export class RandomMatchesComponent implements OnInit {

  matchesDetails: MatchList[] = [];
  matchColumns: string[] = ['matchDate', 'stadium', 'nameOfTheClub', 'numOfGoalsScored', 'numOfGoalsReceived', 'otherTeamName'];

  matchData = new MatTableDataSource<MatchList>(this.matchesDetails);
  searchValue: null;

  constructor(private matchesService: PremierLeagueServiceService) { }


  ngOnInit(): void {
    this.matchesService.getRandomTable().subscribe((randomMatchData: MatchList[]) => {
      this.matchData.data = randomMatchData as MatchList[];
    });
  }
  dateSearcher(column: string): void{
    this.matchData.filterPredicate = (data: any, filter: string): boolean => {
      const dateValue = data[column] && data[column].toLowerCase() || '';
      return dateValue.indexOf(filter) !== -1;
    };
  }


  applyFilter(event: Event): void {
    const searchValue = (event.target as HTMLInputElement).value;
    this.matchData.filter = searchValue.trim().toLowerCase();
  }

  randomPlay(): void{
    this.matchesService.getRandomPlayMatch().subscribe((randomMatchData: MatchList[]) => {
      this.matchData.data = randomMatchData as MatchList[];
    });
  }

  sortByDate(): void{
    this.matchesService.getRandomMatchDateSort().subscribe((randomMatchData: MatchList[]) => {
      this.matchData.data = randomMatchData as MatchList[];
    });
  }
}
