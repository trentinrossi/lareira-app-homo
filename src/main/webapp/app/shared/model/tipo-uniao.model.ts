export interface ITipoUniao {
  id?: number;
  nome?: string;
  descricao?: string;
}

export class TipoUniao implements ITipoUniao {
  constructor(public id?: number, public nome?: string, public descricao?: string) {}
}
