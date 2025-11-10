export enum TicketStatus {
  AVAILABLE = 'AVAILABLE',
  SOLD = 'SOLD',
}

export interface LotteryTicket {
  id: number;
  drawId: number;
  number: string;
  price: number;
  status: TicketStatus;
}

export interface CreateLotteryTicketDTO {
  drawId: number;
  number: string;
  price: number;
}

export interface GenerateTicketsDTO {
  drawId: number;
  quantity: number;
  price: number;
}
