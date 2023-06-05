package ro.ubbcluj.app.domain.dto.foodDTOS;

import java.util.List;
import java.util.Map;

public class OverlayCategoryDTO {

    private Map<Integer, List<Integer>> categoryColors;
    private int[][][] overlayMap;

    public OverlayCategoryDTO() {
    }

    public Map<Integer, List<Integer>> getCategoryColors() {
        return categoryColors;
    }

    public void setCategoryColors(Map<Integer, List<Integer>> categoryColors) {
        this.categoryColors = categoryColors;
    }

    public int[][][] getOverlayMap() {
        return overlayMap;
    }

    public void setOverlayMap(int[][][] overlayMap) {
        this.overlayMap = overlayMap;
    }
}
