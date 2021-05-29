import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllMatchesTableComponent } from './all-matches-table.component';

describe('AllMatchesTableComponent', () => {
  let component: AllMatchesTableComponent;
  let fixture: ComponentFixture<AllMatchesTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllMatchesTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllMatchesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
