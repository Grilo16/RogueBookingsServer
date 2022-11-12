package RogueBookings.utilities;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

@Component
public class DTOConverter<DTO, Entity> {

    ModelMapper modelMapper;


    public DTOConverter() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
    }

    public DTO entityToDTO(Entity entity, Type type){
        return modelMapper.map(entity, type);

    }
    public List<DTO> entityToDTO(List<Entity> entities, Type type){
        return entities.stream().map((Entity entity) -> entityToDTO(entity, type)).toList();
    }

    public List<DTO> entityToDTO(Set<Entity> entities, Type type){
        return entities.stream().map((Entity entity) -> entityToDTO(entity, type)).toList();
    }

    public Entity DTOtoEntity(DTO dto, Type type){
        return modelMapper.map(dto, type);
    }

}
