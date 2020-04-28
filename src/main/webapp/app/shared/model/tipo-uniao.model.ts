import { ICasal } from 'app/shared/model/casal.model';

export interface ITipoUniao {
  id?: number;
  nome?: string;
  descricao?: string;
  casals?: ICasal[];
}

export class TipoUniao implements ITipoUniao {
  constructor(public id?: number, public nome?: string, public descricao?: string, public casals?: ICasal[]) {}
}
