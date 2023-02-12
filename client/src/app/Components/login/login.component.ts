import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../Entity/user';
import { UserServiceService } from '../../Service/user-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  User = new User();
  ResponseUser = new User();
  res!: string[];
  temp!: string;

  constructor(private _service:UserServiceService, private _router:Router) { }

  ngOnInit(): void {
  }
  
  onSubmit(){
    if(this.User.email===''||this.User.email==null){
      alert("Please enter email");
    }else if(this.User.password===''||this.User.password===null){
      alert("Please enter password");
    }else{
      this._service.loginUserFromRemote(this.User).subscribe(
        data => {
          console.log(data)
          this.ResponseUser.address=data.address;
          this.ResponseUser.name=data.name;
          this.ResponseUser.phone=data.phone;
          this.ResponseUser.email=data.email;
          this.ResponseUser.userid = data.userId;
          this.ResponseUser.roles = data.roles;
          console.log(this.ResponseUser);
          localStorage.setItem("token",data.token);
          localStorage.setItem("roles",data.roles);
          localStorage.setItem("userid",data.userId);
          if(data.roles==='ROLE_ADMIN'){
            this._router.navigate(['productslist']);
          }else{
            this._router.navigate(['home']);
          }
          // window.location.href='/home';
        },
        error =>{
          this._router.navigate(['forbidden']);
          console.log(error);
        }
      )
    }

    
  }

}
