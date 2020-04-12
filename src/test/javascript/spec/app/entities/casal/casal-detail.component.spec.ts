import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LareiraAppHomoTestModule } from '../../../test.module';
import { CasalDetailComponent } from 'app/entities/casal/casal-detail.component';
import { Casal } from 'app/shared/model/casal.model';

describe('Component Tests', () => {
  describe('Casal Management Detail Component', () => {
    let comp: CasalDetailComponent;
    let fixture: ComponentFixture<CasalDetailComponent>;
    const route = ({ data: of({ casal: new Casal(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LareiraAppHomoTestModule],
        declarations: [CasalDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CasalDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CasalDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load casal on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.casal).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
