// @ts-ignore
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RandomMatchesComponent } from './random-matches.component';

describe('RandomMatchesComponent', () => {
  let component: RandomMatchesComponent;
  let fixture: ComponentFixture<RandomMatchesComponent>;

  // @ts-ignore
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RandomMatchesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RandomMatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
