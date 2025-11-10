import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GenerateTicketsDTO, LotteryTicket } from '../../models/lottery-ticket.model';
import { CreateSaleDTO, Sale } from '../../models/sale.model';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class LotteryTicketsService {
  private apiUrl = `${environment.apiUrl}/lottery-tickets`;

  constructor(private http: HttpClient) {}

  getByDraw(drawId: number): Observable<LotteryTicket[]> {
    return this.http.get<LotteryTicket[]>(`${this.apiUrl}/draw/${drawId}`);
  }

  getByCustomer(customerId: number): Observable<LotteryTicket[]> {
    return this.http.get<LotteryTicket[]>(`${this.apiUrl}/customer/${customerId}`);
  }

  generateTickets(data: GenerateTicketsDTO): Observable<LotteryTicket[]> {
    return this.http.post<LotteryTicket[]>(`${this.apiUrl}/generate`, data);
  }

  sellTickets(data: CreateSaleDTO): Observable<Sale> {
    return this.http.post<Sale>(`${this.apiUrl}/sell`, data);
  }
}
