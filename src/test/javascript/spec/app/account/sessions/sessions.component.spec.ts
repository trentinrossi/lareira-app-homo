import { ComponentFixture, TestBed, inject, tick, fakeAsync } from '@angular/core/testing';
import { of, throwError } from 'rxjs';

import { LareiraAppHomoTestModule } from '../../../test.module';
import { Session } from 'app/account/sessions/session.model';
import { SessionsComponent } from 'app/account/sessions/sessions.component';
import { SessionsService } from 'app/account/sessions/sessions.service';
import { MockAccountService } from '../../../helpers/mock-account.service';
import { AccountService } from 'app/core/auth/account.service';

describe('Component Tests', () => {
  let sessions: Session[];
  let fixture: ComponentFixture<SessionsComponent>;
  let comp: SessionsComponent;

  describe('SessionsComponent', () => {
    beforeEach(() => {
      sessions = [new Session('xxxxxx==', new Date(2015, 10, 15), '0:0:0:0:0:0:0:1', 'Mozilla/5.0')];

      fixture = TestBed.configureTestingModule({
        imports: [LareiraAppHomoTestModule],
        declarations: [SessionsComponent]
      })
        .overrideTemplate(SessionsComponent, '')
        .createComponent(SessionsComponent);
      comp = fixture.componentInstance;
    });

    it('should define its initial state', inject(
      [AccountService, SessionsService],
      fakeAsync((mockAccountService: MockAccountService, service: SessionsService) => {
        mockAccountService.spy('identity').and.returnValue(
          of({
            id: 'fuzzer'
          })
        );
        spyOn(service, 'findAll').and.returnValue(of(sessions));

        comp.ngOnInit();
        tick();

        expect(mockAccountService.identitySpy).toHaveBeenCalled();
        expect(service.findAll).toHaveBeenCalled();
        expect(comp.success).toBe(false);
        expect(comp.error).toBe(false);
        expect(comp.account).toEqual({
          id: 'fuzzer'
        });
        expect(comp.sessions).toEqual(sessions);
      })
    ));

    it('should call delete on Sessions to invalidate a session', inject(
      [AccountService, SessionsService],
      fakeAsync((mockAccountService: MockAccountService, service: SessionsService) => {
        mockAccountService.spy('identity').and.returnValue(
          of({
            id: 'fuzzer'
          })
        );
        spyOn(service, 'findAll').and.returnValue(of(sessions));
        spyOn(service, 'delete').and.returnValue(of({}));

        comp.ngOnInit();
        comp.invalidate('xyz');
        tick();

        expect(service.delete).toHaveBeenCalledWith('xyz');
      })
    ));

    it('should call delete on Sessions and notify of error', inject(
      [AccountService, SessionsService],
      fakeAsync((mockAccountService: MockAccountService, service: SessionsService) => {
        mockAccountService.spy('identity').and.returnValue(
          of({
            id: 'fuzzer'
          })
        );
        spyOn(service, 'findAll').and.returnValue(of(sessions));
        spyOn(service, 'delete').and.returnValue(throwError({}));

        comp.ngOnInit();
        comp.invalidate('xyz');
        tick();

        expect(comp.success).toBe(false);
        expect(comp.error).toBe(true);
      })
    ));

    it('should call notify of success upon session invalidation', inject(
      [AccountService, SessionsService],
      fakeAsync((mockAccountService: MockAccountService, service: SessionsService) => {
        mockAccountService.spy('identity').and.returnValue(
          of({
            id: 'fuzzer'
          })
        );
        spyOn(service, 'findAll').and.returnValue(of(sessions));
        spyOn(service, 'delete').and.returnValue(of({}));

        comp.ngOnInit();
        comp.invalidate('xyz');
        tick();

        expect(comp.error).toBe(false);
        expect(comp.success).toBe(true);
      })
    ));
  });
});
