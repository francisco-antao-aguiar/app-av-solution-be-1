package av.solutions.app.model;

import av.solutions.app.entity.ClientEntity;

import java.util.List;
import java.util.UUID;

public record ClientModel(
        UUID id,
        UUID image,
        String url
) {
    public static ClientModel entityToModel(ClientEntity clientEntity) {
        return new ClientModel(
                clientEntity.getId(),
                clientEntity.getImageId().getId(),
                clientEntity.getUrl()
        );
    }

    public static List<ClientModel> entityToModel(List<ClientEntity> clientEntities) {
        return clientEntities.stream().map(ClientModel::entityToModel).toList();
    }
}
