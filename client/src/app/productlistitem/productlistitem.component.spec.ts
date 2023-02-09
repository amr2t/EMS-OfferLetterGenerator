import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductlistitemComponent } from './productlistitem.component';

describe('ProductlistitemComponent', () => {
  let component: ProductlistitemComponent;
  let fixture: ComponentFixture<ProductlistitemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductlistitemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductlistitemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
