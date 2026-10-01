import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Chat } from './chat/chat';
import { Recipe } from './recipe/recipe';

export const routes: Routes = [

    {
        path : "" , 
        component : Home
    },
    {
        path : "chat",
        component : Chat
    },
    {
        path : "recipe",
        component : Recipe
    }
];
