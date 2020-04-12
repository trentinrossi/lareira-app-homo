import { Moment } from 'moment';
import { ILareira } from 'app/shared/model/lareira.model';

export interface ICasal {
  id?: number;
  maridoNome?: string;
  maridoSobrenome?: string;
  maridoDataNascimento?: Moment;
  maridoProfissao?: string;
  maridoTelCelular?: string;
  maridoEmail?: string;
  maridoProblemaSaude?: string;
  esposaNome?: string;
  esposaSobrenome?: string;
  esposaDataNascimento?: Moment;
  esposaProfissao?: string;
  esposaTelCelular?: string;
  esposaEmail?: string;
  esposaProblemaSaude?: string;
  lareira?: ILareira;
}

export class Casal implements ICasal {
  constructor(
    public id?: number,
    public maridoNome?: string,
    public maridoSobrenome?: string,
    public maridoDataNascimento?: Moment,
    public maridoProfissao?: string,
    public maridoTelCelular?: string,
    public maridoEmail?: string,
    public maridoProblemaSaude?: string,
    public esposaNome?: string,
    public esposaSobrenome?: string,
    public esposaDataNascimento?: Moment,
    public esposaProfissao?: string,
    public esposaTelCelular?: string,
    public esposaEmail?: string,
    public esposaProblemaSaude?: string,
    public lareira?: ILareira
  ) {}
}
