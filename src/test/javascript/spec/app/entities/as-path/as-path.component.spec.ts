import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GnsggmsTestModule } from '../../../test.module';
import { ASPathComponent } from 'app/entities/as-path/as-path.component';
import { ASPathService } from 'app/entities/as-path/as-path.service';
import { ASPath } from 'app/shared/model/as-path.model';

describe('Component Tests', () => {
  describe('ASPath Management Component', () => {
    let comp: ASPathComponent;
    let fixture: ComponentFixture<ASPathComponent>;
    let service: ASPathService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GnsggmsTestModule],
        declarations: [ASPathComponent],
      })
        .overrideTemplate(ASPathComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ASPathComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ASPathService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ASPath(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.aSPaths && comp.aSPaths[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
