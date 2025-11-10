import { Component, OnInit } from '@angular/core';
import { CreateCustomerDTO, Customer } from '../../core/models/customer.model';
import { CustomerService } from '../../core/services/customer/customer.service';
import { Sale } from '../../core/models/sale.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SalesService } from '../../core/services/sale/sales.service';

@Component({
  selector: 'app-customers',
  imports: [CommonModule, FormsModule],
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css',
})
export class CustomersComponent implements OnInit {
  customers: Customer[] = [];
  loading: boolean = false;
  error: string | null = null;
  successMessage: string | null = null;

  newCustomer: CreateCustomerDTO = {
    documentNumber: '',
    name: '',
    email: '',
  };

  showHistoryModal: boolean = false;
  selectedCustomer: Customer | null = null;
  customerSales: Sale[] = [];
  loadingSales: boolean = false;

  constructor(
    private customerService: CustomerService,
    private salesService: SalesService,
  ) {}

  ngOnInit(): void {
    this.loadCustomers();
  }

  loadCustomers(): void {
    this.loading = true;
    this.error = null;
    this.customerService.getAll().subscribe({
      next: (data) => {
        this.customers = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Error al cargar los clientes.';
        console.error(err);
        this.loading = false;
      },
    });
  }

  onSubmit(): void {
    if (!this.newCustomer.documentNumber || !this.newCustomer.name || !this.newCustomer.email) {
      this.error = 'Todos los campos son obligatorios.';
      return;
    }
    this.loading = true;
    this.error = null;
    this.customerService.create(this.newCustomer).subscribe({
      next: (customer) => {
        this.successMessage = 'Cliente creado exitosamente.';
        this.loadCustomers();
        this.resetForm();
        setTimeout(() => (this.successMessage = null), 3000);
      },
      error: (err) => {
        this.error = 'Error al crear el cliente.';
        console.error(err);
        this.loading = false;
      },
    });
  }

  resetForm(): void {
    this.newCustomer = {
      documentNumber: '',
      name: '',
      email: '',
    };
  }

  openHistoryModal(customer: Customer): void {
    this.selectedCustomer = customer;
    this.showHistoryModal = true;
    this.loadSalesForCustomer(customer.id);
  }

  closeHistoryModal(): void {
    this.showHistoryModal = false;
    this.selectedCustomer = null;
    this.customerSales = [];
  }

  loadSalesForCustomer(customerId: number): void {
    this.loadingSales = true;
    this.salesService.getByCustomer(customerId).subscribe({
      next: (sales) => {
        this.customerSales = sales;
        this.loadingSales = false;
      },
      error: (err) => {
        console.error('Error al cargar ventas', err);
        this.loadingSales = false;
        this.customerSales = [];
      },
    });
  }

  getTotalPurchases(): number {
    return this.customerSales.reduce((sum, sale) => sum + sale.totalAmount, 0);
  }

  getTotalTickets(): number {
    return this.customerSales.reduce((sum, sale) => sum + sale.tickets.length, 0);
  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    return date.toLocaleDateString('es-CO', {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit',
    });
  }
}
