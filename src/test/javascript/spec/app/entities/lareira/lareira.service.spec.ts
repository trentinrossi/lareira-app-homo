import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LareiraService } from 'app/entities/lareira/lareira.service';
import { ILareira, Lareira } from 'app/shared/model/lareira.model';

describe('Service Tests', () => {
  describe('Lareira Service', () => {
    let injector: TestBed;
    let service: LareiraService;
    let httpMock: HttpTestingController;
    let elemDefault: ILareira;
    let expectedResult: ILareira | ILareira[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(LareiraService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Lareira(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Lareira', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Lareira()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Lareira', () => {
        const returnedFromService = Object.assign(
          {
            nome: 'BBBBBB',
            endereco: 'BBBBBB',
            bairro: 'BBBBBB',
            cep: 'BBBBBB',
            cidade: 'BBBBBB',
            estado: 'BBBBBB',
            telefone: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Lareira', () => {
        const returnedFromService = Object.assign(
          {
            nome: 'BBBBBB',
            endereco: 'BBBBBB',
            bairro: 'BBBBBB',
            cep: 'BBBBBB',
            cidade: 'BBBBBB',
            estado: 'BBBBBB',
            telefone: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Lareira', () => {
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
