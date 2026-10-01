import { ChangeDetectorRef, Component, resource } from '@angular/core';
import { ChatService } from '../chat-service';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-chat',
  imports: [RouterLink , CommonModule,FormsModule],
  templateUrl: './chat.html',
  styleUrl: './chat.css',
})
export class Chat {

   prompt : string = '' ; 

   messages : { send : string , text : string} [] = [] ; 
  constructor(private chatService : ChatService , private cd : ChangeDetectorRef){}

   generateChat(prompt : string) {

    this.messages.push({
      send : 'user' , 
      text : prompt
    }) ;
     

     this.chatService.getChatResponse(prompt).subscribe((result)=>{
         console.warn("the anns is : " , result) ; 
         this.messages.push({
          send : 'ai',
          text : String(result)
         });
         this.cd.detectChanges() ;
     }) ; 
     this.prompt = '' ; 


   }


}
