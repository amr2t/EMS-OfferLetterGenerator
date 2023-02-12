import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductslistitemComponent } from './productslistitem.component';

describe('ProductslistitemComponent', () => {
  let component: ProductslistitemComponent;
  let fixture: ComponentFixture<ProductslistitemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductslistitemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductslistitemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
