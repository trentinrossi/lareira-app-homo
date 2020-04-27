import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LareiraAppHomoTestModule } from '../../../test.module';
import { FilhoComponent } from 'app/entities/filho/filho.component';
import { FilhoService } from 'app/entities/filho/filho.service';
import { Filho } from 'app/shared/model/filho.model';

describe('Component Tests', () => {
  describe('Filho Management Component', () => {
    let comp: FilhoComponent;
    let fixture: ComponentFixture<FilhoComponent>;
    let service: FilhoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LareiraAppHomoTestModule],
        declarations: [FilhoComponent]
      })
        .overrideTemplate(FilhoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FilhoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FilhoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Filho(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.filhos && comp.filhos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
