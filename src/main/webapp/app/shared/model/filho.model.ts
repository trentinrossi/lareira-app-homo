import { Moment } from 'moment';
import { Sexo } from 'app/shared/model/enumerations/sexo.model';

export interface IFilho {
  id?: number;
  nome?: string;
  sexo?: Sexo;
  dataNascimento?: Moment;
  idCasalId?: number;
}

export class Filho implements IFilho {
  constructor(public id?: number, public nome?: string, public sexo?: Sexo, public dataNascimento?: Moment, public idCasalId?: number) {}
}
