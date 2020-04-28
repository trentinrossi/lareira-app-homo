import { Moment } from 'moment';
import { IFilho } from 'app/shared/model/filho.model';
import { UF } from 'app/shared/model/enumerations/uf.model';

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
  casalFoneFixo?: string;
  casalFoneContato?: string;
  casalCep?: string;
  casalNomeRua?: string;
  casalNumeroRua?: string;
  casalBairro?: string;
  casalCidade?: string;
  casalEstado?: UF;
  fotoCasalContentType?: string;
  fotoCasal?: any;
  dataUniao?: Moment;
  numeroFicha?: number;
  informacoesCasal?: string;
  filhos?: IFilho[];
  apadrinhados?: ICasal[];
  lareiraId?: number;
  tipoUniaoId?: number;
  casalPadrinhoId?: number;
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
    public casalFoneFixo?: string,
    public casalFoneContato?: string,
    public casalCep?: string,
    public casalNomeRua?: string,
    public casalNumeroRua?: string,
    public casalBairro?: string,
    public casalCidade?: string,
    public casalEstado?: UF,
    public fotoCasalContentType?: string,
    public fotoCasal?: any,
    public dataUniao?: Moment,
    public numeroFicha?: number,
    public informacoesCasal?: string,
    public filhos?: IFilho[],
    public apadrinhados?: ICasal[],
    public lareiraId?: number,
    public tipoUniaoId?: number,
    public casalPadrinhoId?: number
  ) {}
}
