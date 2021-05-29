// @ts-ignore
import {Component, ElementRef, HostListener, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {PremierLeagueServiceService} from '../../services/premier-league-service.service';
import {MatchList} from '../../match-list';

// @ts-ignore
@Component({
  selector: 'app-all-matches-table',
  templateUrl: './all-matches-table.component.html',
  styleUrls: ['./all-matches-table.component.css']
})
export class AllMatchesTableComponent implements OnInit {
  matchesDetails: MatchList[] = [];
  matchColumns: string[] = ['matchDate', 'stadium', 'nameOfTheClub', 'numOfGoalsScored', 'numOfGoalsReceived', 'otherTeamName'];

  matchData = new MatTableDataSource<MatchList>(this.matchesDetails);
  searchValue: null;

  constructor(private matchesService: PremierLeagueServiceService) { }

  ngOnInit(): void {
    this.matchesService.getAllMatches().subscribe((allMatchesData: MatchList[]) => {
      this.matchData.data = allMatchesData as MatchList[];
    });
  }

  dateFilter(column: string): void{
    this.matchData.filterPredicate = (data: any, filter: string): boolean => {
      const test = data[column] && data[column].toLowerCase() || '';
      return test.indexOf(filter) !== -1;
    };
  }


  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.matchData.filter = filterValue.trim().toLowerCase();
  }

  sortByDate(): void{
    this.matchesService.getDateSort().subscribe((allMatchesData: MatchList[]) => {
      this.matchData.data = allMatchesData as MatchList[];
    });
  }
}
