package com.teste.pedidos.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.teste.pedidos.dto.ArtigoDTO;
import com.teste.pedidos.entities.Artigo;

@Mapper(componentModel =  "spring")
@Component
public interface ArtigoMapper {

	Artigo paraArtigo(ArtigoDTO dto);
	
	ArtigoDTO paraArtigoDTO(Artigo entity);
	
	List<ArtigoDTO> paraListaArtigoDTO(List<Artigo> lista);
}
