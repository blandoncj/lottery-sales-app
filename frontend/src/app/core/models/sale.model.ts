import { LotteryTicket } from './lottery-ticket.model';

export interface Sale {
  id: number;
  customerId: number;
  tickets: LotteryTicket[];
  totalAmount: number;
  saleDate: string;
}

export interface CreateSaleDTO {
  customerId: number;
  ticketIds: number[];
}
