import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LareiraAppHomoTestModule } from '../../../test.module';
import { TipoUniaoUpdateComponent } from 'app/entities/tipo-uniao/tipo-uniao-update.component';
import { TipoUniaoService } from 'app/entities/tipo-uniao/tipo-uniao.service';
import { TipoUniao } from 'app/shared/model/tipo-uniao.model';

describe('Component Tests', () => {
  describe('TipoUniao Management Update Component', () => {
    let comp: TipoUniaoUpdateComponent;
    let fixture: ComponentFixture<TipoUniaoUpdateComponent>;
    let service: TipoUniaoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LareiraAppHomoTestModule],
        declarations: [TipoUniaoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TipoUniaoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TipoUniaoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoUniaoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TipoUniao(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TipoUniao();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
