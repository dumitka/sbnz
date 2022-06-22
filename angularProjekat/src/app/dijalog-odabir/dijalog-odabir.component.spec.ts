import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DijalogOdabirComponent } from './dijalog-odabir.component';

describe('DijalogOdabirComponent', () => {
  let component: DijalogOdabirComponent;
  let fixture: ComponentFixture<DijalogOdabirComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DijalogOdabirComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DijalogOdabirComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
