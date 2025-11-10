import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LotteryDraw } from '../../core/models/lottery-draw.model';
import { Customer } from '../../core/models/customer.model';
import { GenerateTicketsDTO, LotteryTicket } from '../../core/models/lottery-ticket.model';
import { CreateSaleDTO } from '../../core/models/sale.model';
import { LotteryTicketsService } from '../../core/services/lottery-tickets/lottery-tickets.service';
import { LotteryDrawsService } from '../../core/services/lottery-draws/lottery-draws.service';
import { CustomerService } from '../../core/services/customer/customer.service';

@Component({
  selector: 'app-lottery-tickets',
  imports: [CommonModule, FormsModule],
  templateUrl: './lottery-tickets.component.html',
  styleUrl: './lottery-tickets.component.css',
})
export class LotteryTicketsComponent implements OnInit {
  draws: LotteryDraw[] = [];
  customers: Customer[] = [];
  tickets: LotteryTicket[] = [];
  availableTickets: LotteryTicket[] = [];

  loading: boolean = false;
  error: string | null = null;
  successMessage: string | null = null;

  activeTab: 'generate' | 'sell' = 'generate';

  generateForm: GenerateTicketsDTO = {
    drawId: 0,
    quantity: 1,
    price: 1,
  };

  sellForm: CreateSaleDTO = {
    customerId: 0,
    ticketIds: [],
  };

  selectedDrawForSale = 0;
  selectedTickets: number[] = [];

  constructor(
    private ticketService: LotteryTicketsService,
    private drawService: LotteryDrawsService,
    private customerService: CustomerService,
  ) {}

  ngOnInit(): void {
    this.loadDraws();
    this.loadCustomers();
  }

  loadDraws(): void {
    this.drawService.getAll().subscribe({
      next: (data) => {
        this.draws = data;
      },
      error: (err) => {
        this.error = 'Error al cargar los sorteos.';
        console.error(err);
        this.loading = false;
      },
    });
  }

  loadCustomers(): void {
    this.customerService.getAll().subscribe({
      next: (data) => {
        this.customers = data;
      },
      error: (err) => {
        this.error = 'Error al cargar los clientes.';
        console.error(err);
        this.loading = false;
      },
    });
  }

  onDrawChangeForSale(): void {
    if (this.selectedDrawForSale > 0) {
      this.loading = true;
      this.ticketService.getByDraw(this.selectedDrawForSale).subscribe({
        next: (data) => {
          this.availableTickets = data.filter((ticket) => ticket.status === 'AVAILABLE');
          this.loading = false;
        },
        error: (err) => {
          this.error = 'Error al cargar los boletos disponibles.';
          console.error(err);
          this.loading = false;
        },
      });
    }
  }

  generateTickets(): void {
    if (
      this.generateForm.drawId == 0 ||
      this.generateForm.quantity <= 0 ||
      this.generateForm.price <= 0
    ) {
      this.error = 'Todos los campos son obligatorios y deben ser válidos.';
      return;
    }

    this.loading = true;
    this.error = null;

    this.ticketService.generateTickets(this.generateForm).subscribe({
      next: (tickets) => {
        this.successMessage = `${tickets.length} billetes generados exitosamente`;
        this.resetGenerateForm();
        this.loading = false;

        setTimeout(() => (this.successMessage = ''), 3000);
      },
      error: (err) => {
        this.error = err.error?.message || 'Error al generar billetes';
        console.error(err);
        this.loading = false;
      },
    });
  }

  toggleTicketSelection(ticketId: number): void {
    const index = this.selectedTickets.indexOf(ticketId);
    if (index > -1) {
      this.selectedTickets.splice(index, 1);
    } else {
      this.selectedTickets.push(ticketId);
    }
  }

  isTicketSelected(ticketId: number): boolean {
    return this.selectedTickets.includes(ticketId);
  }

  calculateTotal(): number {
    return this.availableTickets
      .filter((t) => this.selectedTickets.includes(t.id))
      .reduce((sum, t) => sum + t.price, 0);
  }

  sellTickets(): void {
    if (this.sellForm.customerId == 0 || this.selectedTickets.length == 0) {
      this.error = 'Todos los campos son obligatorios y deben ser válidos.';
      return;
    }

    this.loading = true;
    this.error = null;

    this.sellForm.ticketIds = this.selectedTickets;

    this.ticketService.sellTickets(this.sellForm).subscribe({
      next: (response) => {
        this.successMessage = 'Boletos vendidos exitosamente';
        this.resetSellForm();
        this.onDrawChangeForSale();
        this.loading = false;

        setTimeout(() => (this.successMessage = ''), 3000);
      },
      error: (err) => {
        this.error = err.error?.message || 'Error al vender los boletos';
        console.error(err);
        this.loading = false;
      },
    });
  }

  resetGenerateForm(): void {
    this.generateForm = {
      drawId: 0,
      quantity: 1,
      price: 1,
    };
  }

  resetSellForm(): void {
    this.sellForm = {
      customerId: 0,
      ticketIds: [],
    };
    this.selectedTickets = [];
    this.selectedDrawForSale = 0;
    this.availableTickets = [];
  }

  setActiveTab(tab: 'generate' | 'sell'): void {
    this.activeTab = tab;
    this.error = null;
    this.successMessage = null;
  }
}
