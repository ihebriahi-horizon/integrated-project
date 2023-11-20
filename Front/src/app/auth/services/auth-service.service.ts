import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { AppCookieService } from 'src/app/shared/services/AppCookieService';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/api/auth';
  private authenticated = new BehaviorSubject<boolean>(false);
  private token: string = "403";

  constructor(private http: HttpClient,
    private appCookieService: AppCookieService) {
    // Check for saved token in session storage
    this.token = this.getToken();
    if (this.token) {
      // Update authenticated subject if token is present
      this.authenticated.next(true);
    } else {
      // Set authenticated subject to false if token is not present
      this.authenticated.next(false);
    }
  }

  login(email: string, password: string): Observable<HttpResponse<any>> {
    const request = {
      userEmail: email,
      userPassword: password
    };
    return this.http.post(`${this.baseUrl}/authenticate`, request, { observe: 'response' }).pipe(
      map(response => {
        // Check if the response status is 200
        if (response.status === 200) {
          this.token = response.body['token'];
          // Save the token in session storage
          this.appCookieService.set("jwtToken", response.body['token']);
          // Set the authentication status to true
          this.authenticated.next(true);
          // Return the response body as is
          return response;
        } else {
          // If the response status is not 200, throw an error
          throw new Error('An error occurred while logging in');
        }
      }),
      catchError(error => {
        // If the error status is 401, return the custom response from Spring Boot
        if (error.status === 401) {
          return throwError({
            status: 'UNAUTHORIZED',
            message: 'Invalid email or password'
          });
        } else {
          // For any other error, throw a generic error message
          return throwError({
            status: 'ERROR',
            message: 'An error occurred while logging in'
          });
        }
      })
    );
  }

  getToken(): string {
    return this.appCookieService.get("jwtToken")
  }

  logout() {
    // Set the authentication status to false
    console.log("logged out")
    this.token = "";
    this.appCookieService.remove("jwtToken");
    this.authenticated.next(false);

    // Perform any other logout tasks such as clearing local storage
  }

  isAuthenticated(): Observable<boolean> {
    return this.authenticated.asObservable();
  }

}