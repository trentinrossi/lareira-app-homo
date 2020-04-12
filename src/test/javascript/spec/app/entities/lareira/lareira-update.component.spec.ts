import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LareiraAppHomoTestModule } from '../../../test.module';
import { LareiraUpdateComponent } from 'app/entities/lareira/lareira-update.component';
import { LareiraService } from 'app/entities/lareira/lareira.service';
import { Lareira } from 'app/shared/model/lareira.model';

describe('Component Tests', () => {
  describe('Lareira Management Update Component', () => {
    let comp: LareiraUpdateComponent;
    let fixture: ComponentFixture<LareiraUpdateComponent>;
    let service: LareiraService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LareiraAppHomoTestModule],
        declarations: [LareiraUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LareiraUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LareiraUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LareiraService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Lareira(123);
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
        const entity = new Lareira();
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
