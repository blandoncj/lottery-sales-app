import { Routes } from '@angular/router';
import { CustomersComponent } from './features/customers/customers.component';
import { LotteryDrawsComponent } from './features/lottery-draws/lottery-draws.component';
import { LotteryTicketsComponent } from './features/lottery-tickets/lottery-tickets.component';

export const routes: Routes = [
  { path: '', redirectTo: 'clientes', pathMatch: 'full' },
  { path: 'clientes', component: CustomersComponent },
  { path: 'sorteos', component: LotteryDrawsComponent },
  { path: 'billetes', component: LotteryTicketsComponent },
];
