package br.com.backend.backend.controllers;

import br.com.backend.backend.domains.user.DataGetUser;
import br.com.backend.backend.domains.user.DataUpdateUser;
import br.com.backend.backend.domains.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/user/{id}")
    @Transactional
    public ResponseEntity getUser(@PathVariable Long id) {
        var user = this.repository.getReferenceById(id);
        return ResponseEntity.ok(new DataGetUser(user));
    }

    @PutMapping("/user/{id}")
    @Transactional
    public ResponseEntity updateUser(@RequestBody @Valid DataUpdateUser data, @PathVariable Long id) {
        var user = this.repository.getReferenceById(id);
        user.updateUser(data);

        return ResponseEntity.ok(new DataGetUser(user));
    }

    @DeleteMapping("/user/{id}")
    @Transactional
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        var user = this.repository.getReferenceById(id);
        user.deleteUser();

        return ResponseEntity.ok("Usuário deletado com sucesso.");
    }
}
