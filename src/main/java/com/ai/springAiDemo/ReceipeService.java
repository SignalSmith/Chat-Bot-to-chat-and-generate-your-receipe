package com.ai.springAiDemo;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReceipeService {

    private final ChatModel chatModel ;

    public String generateReceipe(String ingredients, String cuisine, String dietaryRestriction) {
        var template = """
                Give me a recipe using: {ingredients}.
                        Cuisine: {cuisine}.
                        Dietary restrictions: {dietaryRestriction}.
                
                        Format the answer exactly like this, with each item on its own line:
                
                        Title: <recipe name>
                
                        Ingredients:
                        - <item>
                        - <item>
                
                        Steps:
                        1. <step>
                        2. <step>
            """;

        Prompt prompt = new PromptTemplate(template).create(Map.of(
                "ingredients", ingredients,
                "cuisine", cuisine,
                "dietaryRestriction", dietaryRestriction));

        return chatModel.call(prompt).getResult().getOutput().getText();
    }

}
