package ro.ubbcluj.app.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ro.ubbcluj.app.domain.dto.foodDTOS.OverlayCategoryDTO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Service
public class ImageService {
    @Value(value = "${python.server.api}")
    private String PYTHON_API;
    @Value(value = "${image.api.key}")
    private String IMAGE_API_KEY;

    Logger logger = LoggerFactory.getLogger(ImageService.class);

    public OverlayCategoryDTO getSegmentedImage(byte[] imageBytes) throws InterruptedException {
        OverlayCategoryDTO overlayCategoryDTO = new OverlayCategoryDTO();
        try {
            Map<String, String> map = new HashMap<>();
            map.put("content", bytesToJsonArray(imageBytes));
            JSONObject jsonObject = new JSONObject(map);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(PYTHON_API + "/image"))
                    .header("Content-Type", "application/json")
                    .header("X-Api-Key", IMAGE_API_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(jsonObject)))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            overlayCategoryDTO = parseImageResponseToOverlay(response.body());
        } catch (IOException e) {
            logger.error("Error segmenting the image");
        }
        return overlayCategoryDTO;
    }

    public String convertImageToBase64(int[][][] pixelArray) {
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

    private OverlayCategoryDTO parseImageResponseToOverlay(String jsonToParse) {
        OverlayCategoryDTO dto = new OverlayCategoryDTO();
        JSONObject json = new JSONObject(jsonToParse);
        Gson gson = new Gson();
        Type type = new TypeToken<LinkedHashMap<String, ArrayList<Integer>>>() {
        }.getType();
        String innerJsonString = json.getString("color_map");
        Map<String, ArrayList<Integer>> colorJsonMap = gson.fromJson(innerJsonString, type);

        Map<Integer, List<Integer>> colorLabels = new LinkedHashMap<>();
        for (Map.Entry<String, ArrayList<Integer>> entry : colorJsonMap.entrySet()) {
            List<Integer> rgbPixel = entry.getValue();
            colorLabels.put(Integer.parseInt(entry.getKey()), rgbPixel);
        }
        dto.setCategoryColors(colorLabels);

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
        dto.setOverlayMap(matrix);
        return dto;
    }

    private String bytesToJsonArray(byte[] bytes) {
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
}
