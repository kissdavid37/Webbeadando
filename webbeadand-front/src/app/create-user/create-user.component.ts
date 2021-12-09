import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  user:User=new User();
  constructor(private userServiece:UserService,
    private router:Router) { }

  ngOnInit(): void {
  }
  saveUser(){
    this.userServiece.createUser(this.user).subscribe(data=>{
      this.goToUserList();
    },
    error=>console.log(error));
  }

  goToUserList(){
    this.router.navigate(['/users']);
  }
  onsubmit(){
    console.log(this.user);
    this.saveUser();
      
  }

}
