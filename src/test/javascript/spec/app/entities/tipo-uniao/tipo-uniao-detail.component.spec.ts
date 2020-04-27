import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LareiraAppHomoTestModule } from '../../../test.module';
import { TipoUniaoDetailComponent } from 'app/entities/tipo-uniao/tipo-uniao-detail.component';
import { TipoUniao } from 'app/shared/model/tipo-uniao.model';

describe('Component Tests', () => {
  describe('TipoUniao Management Detail Component', () => {
    let comp: TipoUniaoDetailComponent;
    let fixture: ComponentFixture<TipoUniaoDetailComponent>;
    const route = ({ data: of({ tipoUniao: new TipoUniao(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LareiraAppHomoTestModule],
        declarations: [TipoUniaoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TipoUniaoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TipoUniaoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tipoUniao on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tipoUniao).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
