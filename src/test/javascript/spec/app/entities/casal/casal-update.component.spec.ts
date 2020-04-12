import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LareiraAppHomoTestModule } from '../../../test.module';
import { CasalUpdateComponent } from 'app/entities/casal/casal-update.component';
import { CasalService } from 'app/entities/casal/casal.service';
import { Casal } from 'app/shared/model/casal.model';

describe('Component Tests', () => {
  describe('Casal Management Update Component', () => {
    let comp: CasalUpdateComponent;
    let fixture: ComponentFixture<CasalUpdateComponent>;
    let service: CasalService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LareiraAppHomoTestModule],
        declarations: [CasalUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CasalUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CasalUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CasalService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Casal(123);
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
        const entity = new Casal();
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
