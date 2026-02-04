package av.solutions.app.controller;

import av.solutions.app.model.ClientModel;
import av.solutions.app.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping()
    public List<ClientModel> getClients() {
        return clientService.getAllClients();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientModel createClient(@RequestBody ClientModel clientModel) {
        return clientService.addClient(clientModel);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
    }
}
