import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GnsggmsTestModule } from '../../../test.module';
import { PRoulComponent } from 'app/entities/p-roul/p-roul.component';
import { PRoulService } from 'app/entities/p-roul/p-roul.service';
import { PRoul } from 'app/shared/model/p-roul.model';

describe('Component Tests', () => {
  describe('PRoul Management Component', () => {
    let comp: PRoulComponent;
    let fixture: ComponentFixture<PRoulComponent>;
    let service: PRoulService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GnsggmsTestModule],
        declarations: [PRoulComponent],
      })
        .overrideTemplate(PRoulComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PRoulComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PRoulService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PRoul(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.pRouls && comp.pRouls[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
