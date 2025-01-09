package com.teste.pedidos.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.teste.pedidos.dto.UsuarioDTO;
import com.teste.pedidos.entities.Usuario;

@Mapper(componentModel =  "spring")
@Component
public interface UsuarioMapper {
	
	Usuario paraUsuario(UsuarioDTO dto);
	
	UsuarioDTO paraUsuarioDTO(Usuario entity);
	
	List<UsuarioDTO> paraListaUsuarioDTO(List<Usuario> lista);

}
