import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CreateCustomerDTO, Customer } from '../../models/customer.model';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private apiUrl = `${environment.apiUrl}/customers`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.apiUrl);
  }

  getById(id: number): Observable<Customer> {
    return this.http.get<Customer>(`${this.apiUrl}/${id}`);
  }

  getByDocumentNumber(documentNumber: string): Observable<Customer> {
    return this.http.get<Customer>(`${this.apiUrl}/document/${documentNumber}`);
  }

  create(customer: CreateCustomerDTO): Observable<Customer> {
    return this.http.post<Customer>(this.apiUrl, customer);
  }
}
