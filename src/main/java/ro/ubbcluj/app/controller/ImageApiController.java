package ro.ubbcluj.app.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.ubbcluj.app.domain.dto.foodDTOS.OverlayCategoryDTO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.io.IOUtils.toByteArray;

@RestController
@CrossOrigin(origins = "*")
public class ImageApiController {

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> image() throws IOException {
        final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(
                "E:\\Licenta_DOC\\App\\src\\main\\resources\\image\\00000000.jpg"
        )));

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(inputStream.contentLength())
                .body(inputStream);
    }


    @PostMapping("/image")
    public ResponseEntity<?> saveImage(@RequestParam("image") MultipartFile multipartFile) throws IOException, InterruptedException {
        byte[] imageBytes = multipartFile.getBytes();

//        File file = new File("E:\\Licenta_DOC\\App\\src\\main\\resources\\image\\target.jpg");

//        multipartFile.transferTo(file);

        // Create a JSON payload with the image content
        String jsonPayload = String.format("{\"content\": %s}", bytesToJsonArray(imageBytes));
        Map<String, String> map = new HashMap<>();
        map.put("content", bytesToJsonArray(imageBytes));
        JSONObject jsonObject = new JSONObject(map);

        // Set the HTTP request URL and headers
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5000/image"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(jsonObject)))
                .build();

        // Send the HTTP request
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        OverlayCategoryDTO overlayCategoryDTO = jsonMaskToArray(response.body());

//      TODO - parse color_map from json
        String base64 = convertIntArrayToBase64(overlayCategoryDTO.overlayMap);
        JSONObject returnResponse = new JSONObject();
        returnResponse.put("overlay", base64);
        returnResponse.put("category", overlayCategoryDTO.categoryMap);

        /// Replace CategoryMap with an Array of CategoryObject ( Key, RGB-LIST)
        // Response is a string containing like a serialized json

        /// response them ass strings to parse in javascript
        return ResponseEntity.status(HttpStatus.OK).body(returnResponse.toString());
    }

    public String convertIntArrayToBase64(int[][][] pixelArray) {
        int width = pixelArray.length;
        int height = pixelArray[0].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int[] rgb = pixelArray[x][y];
                int color = (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];
                image.setRGB(x, y, color);
            }
        }

        String base64Image = "";
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", outputStream);
            base64Image = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return base64Image;
    }
    private static String bytesToJsonArray(byte[] bytes) {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < bytes.length; i++) {
            builder.append(bytes[i]);
            if (i < bytes.length - 1) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    private static OverlayCategoryDTO jsonMaskToArray(String jsonToParse){
        OverlayCategoryDTO dto = new OverlayCategoryDTO();

        JSONObject json = new JSONObject(jsonToParse);
        JSONObject color_map = new JSONObject(json.getString("color_map"));


        /// keys = category_labels
        // value - JSONArray of length 3

        /// length isnt cool -> the keys could be 5 10 42 104
        Map<Integer, List<Integer>> color_labels = new HashMap<>();
        for (String key: color_map.keySet()) {
            JSONArray jsonArray = color_map.getJSONArray(key);
            List<Integer> rgbPixel = jsonArray.toList().stream().map(Integer.class::cast).collect(Collectors.toList());
            color_labels.put(Integer.parseInt(key), rgbPixel);
        }
        dto.categoryMap = color_labels;
/////////////////////////////////////////////


        // Get the "matrix" array from the JSONObject
        JSONArray matrixArray = json.getJSONArray("mask");
// Loop through the matrixArray and create a 3D matrix
        int[][][] matrix = new int[matrixArray.length()][][];
        for (int i = 0; i < matrixArray.length(); i++) {
            JSONArray matrix2dArray = matrixArray.getJSONArray(i);
            matrix[i] = new int[matrix2dArray.length()][];
            for (int j = 0; j < matrix2dArray.length(); j++) {
                JSONArray matrix1dArray = matrix2dArray.getJSONArray(j);
                matrix[i][j] = new int[matrix1dArray.length()];
                for (int k = 0; k < matrix1dArray.length(); k++) {
                    matrix[i][j][k] = matrix1dArray.getInt(k);
                }
            }
        }
        dto.overlayMap = matrix;
        return dto;
    }
}
