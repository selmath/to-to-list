import { Component } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import { environment } from "../environments/environment";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'TO-DO APP!!!';
  todo: any;
  todoTitle: any;
  todoDesc: any;
  status: any;
  constructor(public http: Http) {
    //console.log('Hello ServiceImplProvider Provider');
    this.todo = [];
    this.status = "inprogress";
    this.getToDoList();
  }

  invokeHTTP(method, url, payload) {
   /**
   * For using common HTTP functions
   * @param {string} method - GET,POST,PUT,DELETE
   * @param {string} context - context path
   * @param {json}  payload - json payload to be sent
   * @return {json}
   */
    let headers = new Headers({
        'content-type':'application/json'
      });

    let options = new RequestOptions({
      headers:headers
    });

    return new Promise(
      (resolve, reject) => {
        switch (method) {
          case "GET": {
            this.http.get(url)
              .map(result => result.json())
              .subscribe(result => {
                resolve(result)
              },
              err => {
                reject(err)
              }
              )
            break;
          }
          case "POST": {
            this.http.post(url,payload,options)
              .map(result => result.json())
              .subscribe(result => {
                console.log("result", result);
                resolve(result)
              },
              err => {
                console.log("err", err);
                reject(err)
              }
              )
            break;
          }
          
          default: {
            reject(new Error("Invalid HTTP method."))
            break;
          }
        }
      }
    )
  }

  saveToDo(){
    console.log(this.todoTitle);
    var payload = {
         "title":this.todoTitle,
         "description":this.todoDesc,
         "status":"inprogress",
         "remarks":"",
         "user":{"name":"selmath","email":"selmathmu2008@gmail.com"}
    }

    this.invokeHTTP("POST",environment.url+"/todo/save",payload)
    .then((response) => {
      console.log("response"+ response);
            if(response["code"] == 200){
                this.getToDoList();
                this.todoTitle="";
                this.todoDesc="";
            }else{
                console.log("something went wrong!!");
            }
      },
      fail=>{
            this.todo =  [];
            console.log(fail);
      });
  }

  getToDoList(){
    var payload = {
         "activityType":"todo.all",
         "status":this.status
    };
    this.invokeHTTP("GET",environment.url+"/todo/list?payload="+encodeURIComponent(JSON.stringify(payload)),"")
    .then((response) => {
      console.log("response"+ response);
            if(response["code"] == 200){
                if(response["payload"].length > 0){             
                    this.todo = response["payload"];
                }else{
                    this.todo =  [];
                    console.log("something went wrong!!");
                }
            }else{
                    this.todo =  [];
                    console.log("something went wrong!!");
            }
      },
      fail=>{
            this.todo =  [];
            console.log(fail);
      }); 
  }
  removeTask(task){
    console.log(JSON.stringify(task));
     var payload = {
         "id":task.id,
         "title":task.title,
         "description":task.description,
         "status":"inprogress",
         "remarks":"",
         "name":"selmath",
         "email":"selmathmu2008@gmail.com"
    };
    this.invokeHTTP("POST",environment.url+"/todo/remove",payload)
    .then((response) => {
      console.log("response"+ response);
            if(response["code"] == 200){
                this.getToDoList();
                this.todoTitle="";
                this.todoDesc="";
            }else{
                console.log("something went wrong!!");
            }
      },
      fail=>{
            this.todo =  [];
            console.log(fail);
      });
  } 
}


