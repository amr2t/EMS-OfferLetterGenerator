import { Component, OnInit } from '@angular/core';
import { UserServiceService } from 'src/app/Service/user-service.service';
import { User } from '../../Entity/user';

@Component({
  selector: 'app-myprofile',
  templateUrl: './myprofile.component.html',
  styleUrls: ['./myprofile.component.css']
})
export class MyprofileComponent implements OnInit {
  User = new User();
  orders!: Number;
  userid = localStorage.getItem('userid');
  constructor(private _service:UserServiceService) {
    this._service.getProfileFromRemote(this.userid).subscribe(
      data => {
        console.log(data);
        this.User.name = data.name;
        this.User.address = data.address;
        this.User.email = data.email;
        this.User.phone = data.phone;
        this.orders = data.orderList.length;
      },
      error => console.log(error)
    )
    // this.User.name = "Chandresh Carpenter";
    // this.User.email = "chandresh@gmail.com",
    // this.User.phone = 9898989898;
    // this.User.address = "12 Effiel Tower C2I Mall"
   }

  ngOnInit(): void {
  }

}
