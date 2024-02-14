import { AuthService } from './../services/auth.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RefreshService } from '../services/refresh.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit, OnDestroy {
  email: string="";
  userId: number=0;
  private refreshSubscription: Subscription;

  constructor(private authService: AuthService, private router: Router, private refreshService: RefreshService) {
    this.refreshSubscription = this.refreshService.refresh$.subscribe(() => {
      this.reinitialize();
    });
  }

  ngOnInit() {
    this.email = localStorage.getItem("email") || "";
    this.userId = parseInt(localStorage.getItem("userId") || "")||0;

  }
  logOut(){
    localStorage.clear();
    this.reinitialize()
    this.router.navigate(['/login']);
  }
  ngOnDestroy(): void {
    this.refreshSubscription.unsubscribe();
  }
  reinitialize(): void {
    this.email = localStorage.getItem("email") || "";
    this.userId = parseInt(localStorage.getItem("userId") || "")||0;
  }

  // loadData(): void {
  //   // Simulate loading data
  //   this.email = 'Updated data';
  // }
  
  
}
