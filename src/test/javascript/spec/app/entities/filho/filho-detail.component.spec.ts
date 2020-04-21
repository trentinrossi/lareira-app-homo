import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LareiraAppHomoTestModule } from '../../../test.module';
import { FilhoDetailComponent } from 'app/entities/filho/filho-detail.component';
import { Filho } from 'app/shared/model/filho.model';

describe('Component Tests', () => {
  describe('Filho Management Detail Component', () => {
    let comp: FilhoDetailComponent;
    let fixture: ComponentFixture<FilhoDetailComponent>;
    const route = ({ data: of({ filho: new Filho(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LareiraAppHomoTestModule],
        declarations: [FilhoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FilhoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FilhoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load filho on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.filho).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
