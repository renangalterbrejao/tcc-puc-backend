package br.com.tcc.easyfood.core.jackson;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {

	@Override
	public void serialize(Page<?> page, JsonGenerator gen, 
			SerializerProvider serializers) throws IOException {
		
		gen.writeStartObject();
		
		gen.writeObjectField("conteudo", page.getContent());
		gen.writeNumberField("maximoRegistrosPorPagina", page.getSize());
		gen.writeNumberField("totalRegistros", page.getTotalElements());
		gen.writeNumberField("totalPaginas", page.getTotalPages());
		gen.writeNumberField("paginaAtual", page.getNumber());
		
		gen.writeEndObject();
	}

}