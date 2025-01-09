package com.teste.pedidos.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.teste.pedidos.dto.MovimentoAcoesDTO;
import com.teste.pedidos.entities.MovimentoAcoes;

@Mapper(componentModel =  "spring")
@Component
public interface MovimentoAcoesMapper {

	MovimentoAcoes paraMovimentoAcoes(MovimentoAcoesDTO dto);
	
	MovimentoAcoesDTO paraMovimentoAcoesDTO(MovimentoAcoes entity);
	
	List<MovimentoAcoesDTO> paraListaMovimentoAcoesDTO(List<MovimentoAcoes> lista);
	
}
