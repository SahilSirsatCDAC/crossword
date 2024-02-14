import { CrosswordService } from './../services/crossword.service';
import { AuthService } from './../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

  constructor(private crosswordService :CrosswordService) {}

  ngOnInit(): void {
    this.crosswordService.getCrosswords().subscribe((data: any) => {
      console.log(data);
    });
  }
  
}
