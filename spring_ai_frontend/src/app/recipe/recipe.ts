import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { RecipeService } from '../recipe-service';

@Component({
  selector: 'app-recipe',
  imports: [FormsModule , CommonModule , RouterLink],
  templateUrl: './recipe.html',
  styleUrl: './recipe.css',
})
export class Recipe {

  ingridients : string = '' ;
   
   messages : {send :string , text : string} []  = [] ; 
   
   
   constructor(private cd : ChangeDetectorRef , private recipe : RecipeService) {}

    generateRecipe (ingridients : string){
      this.messages.push({
          send : 'user' ,
          text : ingridients
     } ) ; 
       
      this.recipe.generateRecipe(ingridients, "any" , "").subscribe((result)=>{
        console.warn(result) ; 
         this.messages.push({
          send : 'ai' ,
          text : result
      }) ; 
      this.cd.detectChanges() ;
      })
      this.ingridients = '' ; 
    }
 
}
