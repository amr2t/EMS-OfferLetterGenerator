import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../Entity/user';
import { UserServiceService } from '../../Service/user-service.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  user = new User();
  
  constructor(private _service: UserServiceService,private _router:Router) { 
    this.user.roles = "ROLE_USER";
  }

  ngOnInit(): void {
  }

  onSubmit(){
    
    if(this.user.name==null||this.user.name===''){
      alert("please enter name")
    }
    else if(this.user.email==null||this.user.email===''){
      alert("please enter email")
    }
    else if(this.user.password==null||this.user.password===''){
      alert("please enter password")
    }
    else if(this.user.address==null||this.user.address===''){
      alert("please enter address")
    }
    else if(this.user.phone==null||this.user.phone===0){
      alert("please enter phone")
    }else{
      this._service.registerUserFromRemote(this.user).subscribe(
        data =>{
          console.log(data);
          alert("Registered successfully")
          this._router.navigate(['login']);
        },
        error => console.log(error)
      )
    }


    
  }

}
