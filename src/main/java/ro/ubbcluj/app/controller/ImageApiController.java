package ro.ubbcluj.app.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ro.ubbcluj.app.domain.dto.CategoryColorDTO;
import ro.ubbcluj.app.domain.dto.foodDTOS.OverlayCategoryDTO;
import ro.ubbcluj.app.service.ImageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class ImageApiController {

    private final ImageService imageService;

    public ImageApiController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/image")
    public ResponseEntity<String> segmentImage(@RequestParam("image") MultipartFile multipartFile) throws IOException, InterruptedException {
        byte[] imageBytes = multipartFile.getBytes();
        OverlayCategoryDTO overlayCategoryDTO = imageService.getSegmentedImage(imageBytes);
        String base64 = imageService.convertImageToBase64(overlayCategoryDTO.getOverlayMap());
        List<CategoryColorDTO> categoryColorDTOS = overlayCategoryDTO.getCategoryColors().entrySet().stream().map(entry ->{ return new CategoryColorDTO(entry.getKey(), entry.getValue());}).toList();
        JSONObject returnResponse = new JSONObject();
        returnResponse.put("overlay", base64);
        returnResponse.put("category", categoryColorDTOS);
        return ResponseEntity.status(HttpStatus.OK).body(returnResponse.toString());
    }
}
