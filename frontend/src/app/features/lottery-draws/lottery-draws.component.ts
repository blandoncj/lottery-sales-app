import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CreateLotteryDrawDTO, LotteryDraw } from '../../core/models/lottery-draw.model';
import { LotteryDrawsService } from '../../core/services/lottery-draws/lottery-draws.service';
import { LotteryTicketsService } from '../../core/services/lottery-tickets/lottery-tickets.service';
import { CustomerService } from '../../core/services/customer/customer.service';
import { LotteryTicket } from '../../core/models/lottery-ticket.model';
import { Customer } from '../../core/models/customer.model';
import { CreateSaleDTO } from '../../core/models/sale.model';

@Component({
  selector: 'app-lottery-draws',
  imports: [CommonModule, FormsModule],
  templateUrl: './lottery-draws.component.html',
  styleUrl: './lottery-draws.component.css',
})
export class LotteryDrawsComponent implements OnInit {
  draws: LotteryDraw[] = [];
  customers: Customer[] = [];
  loading: boolean = false;
  error: string | null = null;
  successMessage: string | null = null;

  newDraw: CreateLotteryDrawDTO = {
    name: '',
    drawDate: '',
  };

  // Para expandir sorteos y ver billetes
  expandedDrawId: number | null = null;
  drawTickets: { [key: number]: LotteryTicket[] } = {};
  loadingTickets: { [key: number]: boolean } = {};

  // Para venta
  selectedCustomerId: number = 0;
  selectedTicketsForDraw: { [key: number]: number[] } = {};

  constructor(
    private drawService: LotteryDrawsService,
    private ticketService: LotteryTicketsService,
    private customerService: CustomerService,
  ) {}

  ngOnInit(): void {
    this.loadDraws();
    this.loadCustomers();
  }

  loadDraws(): void {
    this.loading = true;
    this.error = null;
    this.drawService.getAll().subscribe({
      next: (data) => {
        this.draws = data;
        this.loading = false;
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
        console.error('Error al cargar clientes', err);
      },
    });
  }

  onSubmit(): void {
    if (!this.newDraw.name || !this.newDraw.drawDate) {
      this.error = 'Todos los campos son obligatorios.';
      return;
    }
    this.loading = true;
    this.error = null;
    this.drawService.create(this.newDraw).subscribe({
      next: (draw) => {
        this.successMessage = 'Sorteo creado exitosamente.';
        this.loadDraws();
        this.resetForm();
        setTimeout(() => (this.successMessage = null), 3000);
      },
      error: (err) => {
        this.error = err.error?.message || 'Error al crear el sorteo.';
        console.error(err);
        this.loading = false;
      },
    });
  }

  toggleDrawExpansion(drawId: number): void {
    if (this.expandedDrawId === drawId) {
      this.expandedDrawId = null;
    } else {
      this.expandedDrawId = drawId;
      this.loadTicketsForDraw(drawId);
    }
  }

  loadTicketsForDraw(drawId: number): void {
    if (this.drawTickets[drawId]) return; // Ya cargados

    this.loadingTickets[drawId] = true;
    this.ticketService.getByDraw(drawId).subscribe({
      next: (tickets) => {
        this.drawTickets[drawId] = tickets.filter((t) => t.status === 'AVAILABLE');
        this.loadingTickets[drawId] = false;
      },
      error: (err) => {
        console.error('Error al cargar billetes', err);
        this.loadingTickets[drawId] = false;
      },
    });
  }

  toggleTicketSelection(drawId: number, ticketId: number): void {
    if (!this.selectedTicketsForDraw[drawId]) {
      this.selectedTicketsForDraw[drawId] = [];
    }

    const index = this.selectedTicketsForDraw[drawId].indexOf(ticketId);
    if (index > -1) {
      this.selectedTicketsForDraw[drawId].splice(index, 1);
    } else {
      this.selectedTicketsForDraw[drawId].push(ticketId);
    }
  }

  isTicketSelected(drawId: number, ticketId: number): boolean {
    return this.selectedTicketsForDraw[drawId]?.includes(ticketId) || false;
  }

  getSelectedTicketsCount(drawId: number): number {
    return this.selectedTicketsForDraw[drawId]?.length || 0;
  }

  calculateTotal(drawId: number): number {
    const selectedIds = this.selectedTicketsForDraw[drawId] || [];
    const tickets = this.drawTickets[drawId] || [];
    return tickets.filter((t) => selectedIds.includes(t.id)).reduce((sum, t) => sum + t.price, 0);
  }

  sellTickets(drawId: number): void {
    if (this.selectedCustomerId === 0 || !this.selectedTicketsForDraw[drawId]?.length) {
      this.error = 'Selecciona un cliente y al menos un billete';
      return;
    }

    const saleData: CreateSaleDTO = {
      customerId: this.selectedCustomerId,
      ticketIds: this.selectedTicketsForDraw[drawId],
    };

    this.loading = true;
    this.error = null;

    this.ticketService.sellTickets(saleData).subscribe({
      next: () => {
        this.successMessage = 'Venta realizada exitosamente';
        // Recargar billetes
        delete this.drawTickets[drawId];
        this.loadTicketsForDraw(drawId);
        // Limpiar selección
        this.selectedTicketsForDraw[drawId] = [];
        this.selectedCustomerId = 0;
        this.loading = false;

        setTimeout(() => (this.successMessage = null), 3000);
      },
      error: (err) => {
        this.error = err.error?.message || 'Error al realizar la venta';
        console.error(err);
        this.loading = false;
      },
    });
  }

  resetForm(): void {
    this.newDraw = {
      name: '',
      drawDate: '',
    };
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
