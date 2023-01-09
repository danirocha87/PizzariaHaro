package com.PizzariaHaro.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.PizzariaHaro.model.IngredientesModel;
import com.PizzariaHaro.repository.IngredientesRepository;



	@RestController
	@RequestMapping("/ingredientes")
	@CrossOrigin(origins = "*", allowedHeaders = "*")//usamos essa configuração para no futuro conseguir acessar de qualquer plataforma ** todas as rotas liberadas
	public class IngredientesController {
	
	@Autowired
	private IngredientesRepository ingredientesRepository;


	
	@PostMapping //para receber alguma informação
	public ResponseEntity<IngredientesModel>post(@jakarta.validation.Valid @RequestBody IngredientesModel ingrediente){
		return ResponseEntity.status(HttpStatus.CREATED).body(ingredientesRepository.save(ingrediente));
	}
	
	@GetMapping // uso para fazer uma lista 
	public ResponseEntity<List<IngredientesModel>> getAll() {
		return ResponseEntity.ok(ingredientesRepository.findAll());			
	}
	
	@GetMapping("/{id}")// uso para buscar por ID
	public ResponseEntity<IngredientesModel> getById(@PathVariable Long id){
		return ingredientesRepository.findById(id).map(resposta -> ResponseEntity.ok (resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	
	
	@PutMapping// USO PARA ATUALIZAR
	public ResponseEntity<IngredientesModel> put(@Validated @RequestBody IngredientesModel ingredientes) {
		return ingredientesRepository.findById(ingredientes.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(ingredientesRepository.save(ingredientes)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}") //SERVE PARA DELETAR 
	public void delete(@PathVariable Long id) {
		Optional<IngredientesModel> ingredientes = ingredientesRepository.findById(id);

		if (ingredientes.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		ingredientesRepository.deleteById(id);

}
	
	
	
	
}
