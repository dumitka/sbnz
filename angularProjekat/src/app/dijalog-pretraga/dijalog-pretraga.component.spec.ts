import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DijalogPretragaComponent } from './dijalog-pretraga.component';

describe('DijalogPretragaComponent', () => {
  let component: DijalogPretragaComponent;
  let fixture: ComponentFixture<DijalogPretragaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DijalogPretragaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DijalogPretragaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
