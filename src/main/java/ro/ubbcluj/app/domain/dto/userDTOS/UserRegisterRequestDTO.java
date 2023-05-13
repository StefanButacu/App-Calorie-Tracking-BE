package ro.ubbcluj.app.domain.dto.userDTOS;

public class UserRegisterRequestDTO {

    String username;
    String password;
    Double startWeight;
    Double goalWeight;
    UserFitnessRequestDTO userFitnessRequestDTO;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(Double startWeight) {
        this.startWeight = startWeight;
    }

    public Double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(Double goalWeight) {
        this.goalWeight = goalWeight;
    }

    public UserFitnessRequestDTO getUserFitnessRequestDTO() {
        return userFitnessRequestDTO;
    }

    public void setUserFitnessRequestDTO(UserFitnessRequestDTO userFitnessRequestDTO) {
        this.userFitnessRequestDTO = userFitnessRequestDTO;
    }
}
