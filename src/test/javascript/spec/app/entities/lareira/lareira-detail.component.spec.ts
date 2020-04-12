import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LareiraAppHomoTestModule } from '../../../test.module';
import { LareiraDetailComponent } from 'app/entities/lareira/lareira-detail.component';
import { Lareira } from 'app/shared/model/lareira.model';

describe('Component Tests', () => {
  describe('Lareira Management Detail Component', () => {
    let comp: LareiraDetailComponent;
    let fixture: ComponentFixture<LareiraDetailComponent>;
    const route = ({ data: of({ lareira: new Lareira(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LareiraAppHomoTestModule],
        declarations: [LareiraDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LareiraDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LareiraDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load lareira on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.lareira).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
