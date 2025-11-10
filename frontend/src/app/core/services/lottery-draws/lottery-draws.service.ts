import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CreateLotteryDrawDTO, LotteryDraw } from '../../models/lottery-draw.model';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class LotteryDrawsService {
  private apiUrl = `${environment.apiUrl}/lottery-draws`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<LotteryDraw[]> {
    return this.http.get<LotteryDraw[]>(this.apiUrl);
  }

  getById(id: number): Observable<LotteryDraw> {
    return this.http.get<LotteryDraw>(`${this.apiUrl}/${id}`);
  }

  getByName(name: string): Observable<LotteryDraw> {
    return this.http.get<LotteryDraw>(`${this.apiUrl}/name/${name}`);
  }

  create(draw: CreateLotteryDrawDTO): Observable<LotteryDraw> {
    return this.http.post<LotteryDraw>(this.apiUrl, draw);
  }
}
