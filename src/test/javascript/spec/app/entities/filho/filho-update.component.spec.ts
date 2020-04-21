import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LareiraAppHomoTestModule } from '../../../test.module';
import { FilhoUpdateComponent } from 'app/entities/filho/filho-update.component';
import { FilhoService } from 'app/entities/filho/filho.service';
import { Filho } from 'app/shared/model/filho.model';

describe('Component Tests', () => {
  describe('Filho Management Update Component', () => {
    let comp: FilhoUpdateComponent;
    let fixture: ComponentFixture<FilhoUpdateComponent>;
    let service: FilhoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LareiraAppHomoTestModule],
        declarations: [FilhoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FilhoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FilhoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FilhoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Filho(123);
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
        const entity = new Filho();
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
