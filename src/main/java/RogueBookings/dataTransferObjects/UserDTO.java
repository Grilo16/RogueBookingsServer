package RogueBookings.dataTransferObjects;

import RogueBookings.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private String userLayout;

    public UserDTO() {
    }

    public UserDTO(Long id, String firstName, String lastName, String email, String userLayout) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userLayout = userLayout;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public JsonNode getUserLayout() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(this.userLayout);
        return jsonNode;
    }

    public void setUserLayout(String userLayout) {
        this.userLayout = userLayout;
    }
}
