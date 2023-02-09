import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserordersitemComponent } from './userordersitem.component';

describe('UserordersitemComponent', () => {
  let component: UserordersitemComponent;
  let fixture: ComponentFixture<UserordersitemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserordersitemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserordersitemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
