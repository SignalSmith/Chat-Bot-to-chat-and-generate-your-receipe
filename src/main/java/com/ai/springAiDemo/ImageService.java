//package com.ai.springAiDemo;
//
//import java.net.http.HttpClient;
//import java.time.Duration;
//import java.util.Base64;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//import java.util.concurrent.ConcurrentHashMap;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.MediaType;
//import org.springframework.http.client.JdkClientHttpRequestFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClient;
//import org.springframework.web.client.RestClientResponseException;
//
//@Service
//public class ImageService {
//
//    record InlineData(String mimeType, String data) {}
//    record Part(String text, InlineData inlineData) {}
//    record Content(List<Part> parts) {}
//    record Candidate(Content content) {}
//    record GeminiResponse(List<Candidate> candidates) {}
//
//    @Value("${gemini.api-key}")
//    private String apiKey;
//
//    @Value("${gemini.image-model}")
//    private String model;
//
//    private final Map<String, byte[]> store = new ConcurrentHashMap<>();
//    private final RestClient restClient = buildClient();
//
//    private static RestClient buildClient() {
//        HttpClient httpClient = HttpClient.newBuilder()
//                .version(HttpClient.Version.HTTP_1_1)
//                .connectTimeout(Duration.ofSeconds(15))
//                .build();
//
//        JdkClientHttpRequestFactory factory = new JdkClientHttpRequestFactory(httpClient);
//        factory.setReadTimeout(Duration.ofSeconds(120));
//
//        return RestClient.builder().requestFactory(factory).build();
//    }
//
//    /** Returns the saved image bytes for an id, or null if not found. */
//    public byte[] get(String id) {
//        return store.get(id);
//    }
//
//    /** Generates one image, saves it in memory, and returns its id in a list. */
//    public List<String> generate(String prompt) {
//        Map<String, Object> body = Map.of(
//                "contents", List.of(Map.of("parts", List.of(Map.of("text", prompt)))),
//                "generationConfig", Map.of("responseModalities", List.of("TEXT", "IMAGE"))
//        );
//
//        try {
//            GeminiResponse response = restClient.post()
//                    .uri("https://generativelanguage.googleapis.com/v1beta/models/{model}:generateContent", model)
//                    .header("x-goog-api-key", apiKey)
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .body(body)
//                    .retrieve()
//                    .body(GeminiResponse.class);
//
//            if (response != null && response.candidates() != null && !response.candidates().isEmpty()) {
//                for (Part part : response.candidates().get(0).content().parts()) {
//                    if (part.inlineData() != null) {
//                        byte[] image = Base64.getDecoder().decode(part.inlineData().data());
//                        String id = UUID.randomUUID().toString();
//                        store.put(id, image);
//                        return List.of(id);
//                    }
//                }
//            }
//            throw new IllegalStateException("Gemini returned no image (the prompt may have been blocked)");
//
//        } catch (RestClientResponseException e) {
//            throw new IllegalStateException("Gemini error " + e.getStatusCode().value()
//                    + ": " + e.getResponseBodyAsString(), e);
//        }
//    }
//}