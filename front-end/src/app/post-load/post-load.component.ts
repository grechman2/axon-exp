import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PostLoadCommand} from "../shared/model/PostLoadCommand";

@Component({
  selector: 'app-post-load',
  templateUrl: './post-load.component.html',
  styleUrls: ['./post-load.component.css']
})
export class PostLoadComponent implements OnInit {
  form: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      owner: ['', Validators.required],
      from: ['', Validators.required],
      to:['', Validators.required],
      price:['', Validators.compose([Validators.required, Validators.min(1)])]
    })
  }

  onSubmit({value, valid}: { value: PostLoadCommand; valid: boolean}){

  }

}
