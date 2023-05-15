package ro.ubbcluj.app.domain.dto.userDTOS;

public class EnumDTO {
    String key;
    String text;

    public EnumDTO(String key, String text) {
        this.key = key;
        this.text = text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
