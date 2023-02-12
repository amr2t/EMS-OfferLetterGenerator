import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/Entity/user';

@Component({
  selector: 'app-userslistitem',
  templateUrl: './userslistitem.component.html',
  styleUrls: ['./userslistitem.component.css']
})
export class UserslistitemComponent implements OnInit {
  @Input() user!:User;
  constructor() { }

  ngOnInit(): void {
  }

}
