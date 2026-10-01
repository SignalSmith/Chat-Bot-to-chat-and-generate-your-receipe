package com.ai.springAiDemo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class GenAiController {


    private final ChatService chatService ;
   // private final ImageService imageService ;
    private final ReceipeService receipeService ;

    @GetMapping("/ask-ai")
    public String getRResponse(@RequestParam String prompt){
        return chatService.getResponse(prompt) ;
    }

    @GetMapping("/ask-ai/options")
    public String getOptions(@RequestParam String prompt){
        return chatService.getResponseOptions(prompt) ;
    }

//    @GetMapping("/image")
//    public List<String> getImages(@RequestParam String prompt) {
//        return imageService.generate(prompt).stream()
//                .map(id -> "/image/file/" + id)
//                .toList();
//    }


    @GetMapping("/receipe")
    public String generateReceipe(@RequestParam String ingredients,
                                  @RequestParam(defaultValue = "any") String cuisine,
                                  @RequestParam(defaultValue = "none") String dietaryRestriction) {
        return receipeService.generateReceipe(ingredients, cuisine, dietaryRestriction);
    }



}


