import { Injectable } from '@angular/core';
import { Client, Message } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  private stompClient: Client;
  private taskUpdatesSubject = new Subject<string>();

  constructor() {
    this.connect();
  }

  private connect() {
    const socket = new SockJS('http://backend:8080/ws');
    this.stompClient = new Client({
      webSocketFactory: () => socket,
      debug: (msg) => console.log(msg),
      reconnectDelay: 5000,
    });

    this.stompClient.onConnect = () => {
      console.log("Conectado al WebSocket");
      this.stompClient.subscribe('/topic/tasks', (message: Message) => {
        console.log("Mensaje recibido:", message.body);
        this.taskUpdatesSubject.next(message.body);
      });
    };

    this.stompClient.activate();
  }

  getTaskUpdates(): Observable<string> {
    return this.taskUpdatesSubject.asObservable();
  }
}
