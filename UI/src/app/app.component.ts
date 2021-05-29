// @ts-ignore
import { Component } from '@angular/core';

// @ts-ignore
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'UI';

  color: string | undefined;
  background: string | undefined;
  navBackground: string | undefined;

  navColorChange(colorValue: string, backgroundColorValue: string, navColorValue: string): void {
    this.color = colorValue;
    this.background = backgroundColorValue;
    this.navBackground = '  background: linear-gradient(90deg, '+navColorValue+' 5%, #38003c 5%, #38003c 5%, #38003c 10%);\n';
  }
}




