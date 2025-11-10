export interface Customer {
  id: number;
  documentNumber: string;
  name: string;
  email: string;
}

export interface CreateCustomerDTO {
  documentNumber: string;
  name: string;
  email: string;
}
