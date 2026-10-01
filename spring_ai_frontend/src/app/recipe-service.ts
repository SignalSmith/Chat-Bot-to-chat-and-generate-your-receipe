import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class RecipeService {

  constructor(private http : HttpClient) {}

  generateRecipe(ingredients : string , cuision : string , diet : string){
    return this.http.get(`http://localhost:8080/receipe?&ingredients=${encodeURIComponent(ingredients)} 
                          &cuision=${encodeURIComponent(cuision)}&diet=${encodeURIComponent(diet)}`, 
     {responseType : 'text'}) ; 
  }
}
