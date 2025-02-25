import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private apiUrl = 'http://backend:8080/tasks'; 

  constructor(private http: HttpClient) {}

  getTasks(role: string, email: string): Observable<any[]> {
    const url = role === 'Líder Técnico' ? `${this.apiUrl}/getTasks` : `${this.apiUrl}/getTasks?email=${email}`;
    return this.http.get<any[]>(url);
  }

  getTasksByFilters(status: string, email: string): Observable<any[]> {
    let url = `${this.apiUrl}/getTasks?`;
    if (status !== 'all') url += `status=${status}&`;
    if (email !== 'all' && email !== '') url += `email=${email}&`;
    return this.http.get<any[]>(url);
  }

  createTask(task: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/create`, task);
  }

  updateTask(task: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${task.id}`, task);
  }

  getUsers(): Observable<any[]> {
    return this.http.get<any[]>('http://backend:8080/users/all');
  }
}
