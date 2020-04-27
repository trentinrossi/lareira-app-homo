import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LareiraAppHomoTestModule } from '../../../test.module';
import { TipoUniaoComponent } from 'app/entities/tipo-uniao/tipo-uniao.component';
import { TipoUniaoService } from 'app/entities/tipo-uniao/tipo-uniao.service';
import { TipoUniao } from 'app/shared/model/tipo-uniao.model';

describe('Component Tests', () => {
  describe('TipoUniao Management Component', () => {
    let comp: TipoUniaoComponent;
    let fixture: ComponentFixture<TipoUniaoComponent>;
    let service: TipoUniaoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LareiraAppHomoTestModule],
        declarations: [TipoUniaoComponent]
      })
        .overrideTemplate(TipoUniaoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TipoUniaoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoUniaoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TipoUniao(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tipoUniaos && comp.tipoUniaos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
