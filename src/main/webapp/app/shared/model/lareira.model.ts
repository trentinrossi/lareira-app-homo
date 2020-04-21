import { ICasal } from 'app/shared/model/casal.model';

export interface ILareira {
  id?: number;
  nome?: string;
  endereco?: string;
  bairro?: string;
  cep?: string;
  cidade?: string;
  estado?: string;
  telefone?: string;
  ids?: ICasal[];
}

export class Lareira implements ILareira {
  constructor(
    public id?: number,
    public nome?: string,
    public endereco?: string,
    public bairro?: string,
    public cep?: string,
    public cidade?: string,
    public estado?: string,
    public telefone?: string,
    public ids?: ICasal[]
  ) {}
}
