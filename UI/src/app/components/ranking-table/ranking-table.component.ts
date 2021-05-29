// @ts-ignore
import {Component, HostListener, OnInit} from '@angular/core';
import {PremierLeagueServiceService} from '../../services/premier-league-service.service';

import {MatTableDataSource} from '@angular/material/table';
import {ClubsDetails} from '../../clubs-details';
import {MatchList} from '../../match-list';

// @ts-ignore
@Component({
  selector: 'app-ranking-table',
  templateUrl: './ranking-table.component.html',
  styleUrls: ['./ranking-table.component.css']
})
export class RankingTableComponent implements OnInit {

  clubsDetails: ClubsDetails[] = [];
  clubsColumns: string[] = ['position', 'nameOfTheClub', 'location', 'stadium', 'matchDate', 'numOfPlayedMatches', 'won', 'drawn', 'lost', 'numOfGoalsScored', 'numOfGoalsReceived', 'numOfGoalsDifference', 'numOfPoints'];

  clubsData = new MatTableDataSource<ClubsDetails>(this.clubsDetails);
  searchValue: null;

  constructor(private rankingService: PremierLeagueServiceService) {
  }

  ngOnInit(): void {
    this.rankingService.getRankingTable().subscribe((details: ClubsDetails[]) => this.clubsData.data = details as ClubsDetails[]);
  }

  dateFilter(column: string): void{
    this.clubsData.filterPredicate = (data: any, filter: string): boolean => {
      const test = data[column] && data[column].toLowerCase() || '';
      return test.indexOf(filter) !== -1;
    };
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.clubsData.filter = filterValue.trim().toLowerCase();
  }

  sortByPoints(): void {
    this.rankingService.getPointsSort().subscribe((rankingTableData: ClubsDetails[]) => {
      this.clubsData.data = rankingTableData as ClubsDetails[];
    });
  }

  sortByGoals(): void {
    this.rankingService.getGoalsSort().subscribe((rankingTableData: ClubsDetails[]) => {
      this.clubsData.data = rankingTableData as ClubsDetails[];
    });
  }

  sortByWins(): void {
    this.rankingService.getWinsSort().subscribe((rankingTableData: ClubsDetails[]) => {
      this.clubsData.data = rankingTableData as ClubsDetails[];
    });
  }
}
