import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderslistitemComponent } from './orderslistitem.component';

describe('OrderslistitemComponent', () => {
  let component: OrderslistitemComponent;
  let fixture: ComponentFixture<OrderslistitemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrderslistitemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrderslistitemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
