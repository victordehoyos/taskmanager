import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiURL = 'http://localhost:8080/users'

  constructor(private http: HttpClient) { }

  getUsers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiURL}/all`)
  }

  createUser(user: any): Observable<any> {
    return this.http.post(`${this.apiURL}/register`, user);
  }

  getRoles(): Observable<any> {
    return this.http.get(`${this.apiURL}/role/all`);
  }

  getRole(roleId: any): Observable<any> {
    return this.http.get(`${this.apiURL}/role/${roleId}`);
  }
}
