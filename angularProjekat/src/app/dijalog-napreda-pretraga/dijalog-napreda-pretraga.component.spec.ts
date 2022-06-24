import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DijalogNapredaPretragaComponent } from './dijalog-napreda-pretraga.component';

describe('DijalogNapredaPretragaComponent', () => {
  let component: DijalogNapredaPretragaComponent;
  let fixture: ComponentFixture<DijalogNapredaPretragaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DijalogNapredaPretragaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DijalogNapredaPretragaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
