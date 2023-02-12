import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/Entity/user';
import { UserServiceService } from 'src/app/Service/user-service.service';

@Component({
  selector: 'app-userslist',
  templateUrl: './userslist.component.html',
  styleUrls: ['./userslist.component.css']
})
export class UserslistComponent implements OnInit {
  UserList : User[] = [];
  constructor(private _service:UserServiceService) {
    this._service.getAllUsersFromRemote().subscribe(
      data => {
        console.log(data);
        data.forEach((i:any) => {
          let tempuser = new User();
          tempuser.address = i.address;
          tempuser.email = i.email;
          tempuser.name = i.name;
          tempuser.userid = i.id;
          this.UserList.push(tempuser);
        });
        console.log(this.UserList);
        
      },
      error => {
        console.log(error);
      }
    )
   }

  ngOnInit(): void {
  }
  onLogout(){
    localStorage.clear();
    window.location.href="home";
  }
}
