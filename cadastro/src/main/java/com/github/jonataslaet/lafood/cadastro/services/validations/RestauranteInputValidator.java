package com.github.jonataslaet.lafood.cadastro.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.github.jonataslaet.lafood.cadastro.entities.Restaurante;
import com.github.jonataslaet.lafood.cadastro.resources.dtos.FieldMessage;
import com.github.jonataslaet.lafood.cadastro.resources.dtos.input.RestauranteInputDto;

public class RestauranteInputValidator implements ConstraintValidator<RestauranteInputValid, RestauranteInputDto> {
	
	@Override
	public void initialize(RestauranteInputValid ann) {
	}

	@Override
	public boolean isValid(RestauranteInputDto dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		Optional<Restaurante> restauranteOptional = Restaurante.find("cnpj", dto.cnpj).firstResultOptional();
		if (restauranteOptional.isPresent()) {
			list.add(new FieldMessage("cnpj", "O cnpj informado já existe neste sistema"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
