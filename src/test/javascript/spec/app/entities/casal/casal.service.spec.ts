import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { CasalService } from 'app/entities/casal/casal.service';
import { ICasal, Casal } from 'app/shared/model/casal.model';

describe('Service Tests', () => {
  describe('Casal Service', () => {
    let injector: TestBed;
    let service: CasalService;
    let httpMock: HttpTestingController;
    let elemDefault: ICasal;
    let expectedResult: ICasal | ICasal[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CasalService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Casal(
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            maridoDataNascimento: currentDate.format(DATE_FORMAT),
            esposaDataNascimento: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Casal', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            maridoDataNascimento: currentDate.format(DATE_FORMAT),
            esposaDataNascimento: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            maridoDataNascimento: currentDate,
            esposaDataNascimento: currentDate
          },
          returnedFromService
        );

        service.create(new Casal()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Casal', () => {
        const returnedFromService = Object.assign(
          {
            maridoNome: 'BBBBBB',
            maridoSobrenome: 'BBBBBB',
            maridoDataNascimento: currentDate.format(DATE_FORMAT),
            maridoProfissao: 'BBBBBB',
            maridoTelCelular: 'BBBBBB',
            maridoEmail: 'BBBBBB',
            maridoProblemaSaude: 'BBBBBB',
            esposaNome: 'BBBBBB',
            esposaSobrenome: 'BBBBBB',
            esposaDataNascimento: currentDate.format(DATE_FORMAT),
            esposaProfissao: 'BBBBBB',
            esposaTelCelular: 'BBBBBB',
            esposaEmail: 'BBBBBB',
            esposaProblemaSaude: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            maridoDataNascimento: currentDate,
            esposaDataNascimento: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Casal', () => {
        const returnedFromService = Object.assign(
          {
            maridoNome: 'BBBBBB',
            maridoSobrenome: 'BBBBBB',
            maridoDataNascimento: currentDate.format(DATE_FORMAT),
            maridoProfissao: 'BBBBBB',
            maridoTelCelular: 'BBBBBB',
            maridoEmail: 'BBBBBB',
            maridoProblemaSaude: 'BBBBBB',
            esposaNome: 'BBBBBB',
            esposaSobrenome: 'BBBBBB',
            esposaDataNascimento: currentDate.format(DATE_FORMAT),
            esposaProfissao: 'BBBBBB',
            esposaTelCelular: 'BBBBBB',
            esposaEmail: 'BBBBBB',
            esposaProblemaSaude: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            maridoDataNascimento: currentDate,
            esposaDataNascimento: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Casal', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
