import { ChangeDetectorRef, Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { RefreshService } from '../services/refresh.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  formData = { email: '', password: '' };
  constructor(private authService: AuthService, private router: Router, private jwtHelper: JwtHelperService, private refreshService: RefreshService) {}

  ngOnInit(): void {}
  onSubmit() {
    console.log('Form submitted with data:', this.formData);
    this.authService.loginUser(this.formData).subscribe((data) => {
      // console.log(data);
      const decodedToken = this.jwtHelper.decodeToken(data.accessToken);
      // console.log(decodedToken);
      localStorage.setItem("email", decodedToken.sub);
      localStorage.setItem("userId", decodedToken.userId);
      localStorage.setItem("userData", decodedToken);
      this.triggerReinitialize();
      this.router.navigate(['/user/profile/'+decodedToken.userId]);
    });
    
  }
  triggerReinitialize(): void {
    this.refreshService.triggerRefresh();
  }
}
