import { NgbDateParserFormatter, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import * as moment from 'moment';
import { Injectable } from '@angular/core';

@Injectable()
export class MomentDateFormatter extends NgbDateParserFormatter {
  readonly DT_FORMAT = 'DD/MM/YYYY';
  readonly DELIMITER = '/';

  parse(value: string): NgbDateStruct {
    if (value) {
      // console.log(value);
      const mdt = moment(value.trim(), this.DT_FORMAT);
      return { day: mdt.date(), month: mdt.months() + 1, year: mdt.year() };
    }
    return { year: 0, month: 0, day: 0 };
  }

  format(date: NgbDateStruct): string {
    if (!date) return '';
    // tslint:disable-next-line: no-console
    // console.log(date);

    const mdt = moment([date.year, date.month - 1, date.day]);
    // console.log(mdt);

    if (!mdt.isValid()) return '';
    return mdt.format(this.DT_FORMAT);
  }
}
