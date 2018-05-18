package br.com.pedidovenda.security;

import br.com.pedidovenda.model.Grupo;
import br.com.pedidovenda.model.Usuario;
import br.com.pedidovenda.repository.Usuarios;
import br.com.pedidovenda.util.cdi.CDIServiceLocator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AppUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuarios usuarios = CDIServiceLocator.getBean(Usuarios.class);
        Usuario usuarioEncontrado = usuarios.porEmail(email);
        UsuarioSistema user = null;

        if (usuarioEncontrado != null) {
            user = new UsuarioSistema(usuarioEncontrado, getGrupos(usuarioEncontrado));
        }

        return user;
    }

    private Collection<? extends GrantedAuthority> getGrupos(Usuario usuarioEncontrado) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Grupo grupo : usuarioEncontrado.getGrupos()) {
            authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
        }

        return authorities;
    }
}
