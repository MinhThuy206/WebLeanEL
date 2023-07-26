package com.weblearnel.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.weblearnel.model.Word;
import com.weblearnel.service.WordService;

@RestController
@RequestMapping("/word")
public class WordController {
    @Autowired
    private WordService wordService;

    private static final String FILE_PATH = "src/main/resources/static/images/";

    // private final ResourceLoader resourceLoader;

    // Add Word
    @PostMapping("/")
    public Word addWord(@RequestBody Word word) {
        return wordService.addWord(word);
    }

    // Get All Words
    @GetMapping("/")
    public List<Word> findAllWords() {
        return wordService.getAllWords();
    }

    // Get One Word
    @GetMapping("/{id}")
    public Word findWord(@PathVariable long id) {
        return wordService.getOneWord(id);
    }

    // update word
    @PutMapping("/{id}")
    public Word updateWord(@PathVariable("id") long id, @RequestBody Word word) {
        return wordService.updateWord(word, id);
    }

    // Delete word
    @DeleteMapping("/{id}")
    public boolean deleteWord(@PathVariable int id) {
        return wordService.deleteWord(id);
    }

    @PutMapping("/{word_id}/topic/{topic_id}")
    public Word assignTopicToWord(@PathVariable("word_id") long word_id, @PathVariable("topic_id") long topic_id) {
        return wordService.assignExamToResult(word_id, topic_id);
    }

    // @GetMapping(
    //     value = "/image/{filename}",
    //     produces = MediaType.IMAGE_JPEG_VALUE
    // )
    // public ResponseEntity<Resource> getImage(@PathVariable("filename") String filename) {
    //     try {
    //         // Form the complete path to the requested image using the filename parameter.
    //         String imagePath = FILE_PATH + filename + ".jpg";
    //         // Load the image resource from the classpath based on the requested filename.
    //         Resource resource = new ClassPathResource(imagePath);

    //         // Check if the resource exists.
    //         if (!resource.exists()) {
    //             System.out.println("not found");
    //             return ResponseEntity.notFound().build();
    //         }

    //         // Determine the content type based on the file extension or use a default
    //         // value.
    //         String contentType = "image/jpg"; // You can customize this based on the image types you have.

    //         // Return the image as a ResponseEntity with appropriate headers.
    //         return ResponseEntity.ok()
    //                 .header("Content-Type", contentType)
    //                 .body(resource);
    //     } catch (Exception e) {
    //         // Handle any exceptions that may occur while loading the image.
    //         System.out.println("error");
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    // @GetMapping(value = "/image/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
    // public void getImage(@PathVariable("filename") String filename, HttpServletResponse response) throws IOException {
    //     String imagePath = "images/" + filename + ".jpg";
    //     var imgFile = new ClassPathResource(imagePath);

    //     response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    //     StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    // }

    @RequestMapping(value = "/image/{filename}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) throws IOException {

        String imagePath = "static/images/" + filename + ".jpg";
        var imgFile = new ClassPathResource(imagePath);
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
