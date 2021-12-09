import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {
  id:number;
  user:User=new User();
  constructor(private userServiece:UserService,
    private route:ActivatedRoute,
    private router:Router) { }

    
  ngOnInit(): void {
    this.id=this.route.snapshot.params['id'];


    this.userServiece.getUserById(this.id).subscribe(data=>{
      this.user=data;
    },error => console.log(error));
  }

  updateUser(){
    this.userServiece.updateUser(this.id,this.user).subscribe(data=>{
      this.gotoList();
    },error=>console.log(error));
  }
  gotoList(){
    this.router.navigate(['/users']);
  }

  onsubmit(){
    this.updateUser();
      
  }

  

}
