package com.teste.pedidos.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.teste.pedidos.dto.OrdemDTO;
import com.teste.pedidos.entities.Ordem;

@Mapper(componentModel =  "spring")
@Component
public interface OrdemMapper {

	Ordem paraOrdem(OrdemDTO dto);
	
	OrdemDTO paraOrdemDTO(Ordem entity);
	
	List<OrdemDTO> paraListaOrdemDTO(List<Ordem> lista);
}
