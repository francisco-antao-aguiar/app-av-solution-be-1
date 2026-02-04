package av.solutions.app.service;

import av.solutions.app.entity.ClientEntity;
import av.solutions.app.entity.ImageEntity;
import av.solutions.app.model.ClientModel;
import av.solutions.app.repository.ClientRepository;
import av.solutions.app.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ImageRepository imageRepository;
    private final CacheManager cacheManager;

    @Cacheable(value = "data", key = "#root.methodName")
    public List<ClientModel> getAllClients() {
        return ClientModel.entityToModel(clientRepository.findAll());
    }

    public ClientModel addClient(ClientModel clientModel) {
        ImageEntity imageEntity = imageRepository.getReferenceById(clientModel.image());

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setImageId(imageEntity);
        clientEntity.setUrl(clientModel.url());
        cacheManager.getCache("data").clear();
        return ClientModel.entityToModel(clientRepository.save(clientEntity));
    }

    public void deleteClient(UUID id) {
        cacheManager.getCache("data").clear();
        clientRepository.deleteById(id);
    }
}
