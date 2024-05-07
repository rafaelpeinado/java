package io.github.rafaelpeinado.sbootexpsecurity.domain.service;

import io.github.rafaelpeinado.sbootexpsecurity.domain.entity.Grupo;
import io.github.rafaelpeinado.sbootexpsecurity.domain.entity.Usuario;
import io.github.rafaelpeinado.sbootexpsecurity.domain.entity.UsuarioGrupo;
import io.github.rafaelpeinado.sbootexpsecurity.domain.repository.GrupoRepository;
import io.github.rafaelpeinado.sbootexpsecurity.domain.repository.UsuarioGrupoRepository;
import io.github.rafaelpeinado.sbootexpsecurity.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final GrupoRepository grupoRepository;
    private final UsuarioGrupoRepository usuarioGrupoRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario salvar(Usuario usuario, List<String> grupos) {
        String senhaCriptografada = usuario.getSenha();
        usuario.setSenha(senhaCriptografada);
        passwordEncoder.encode(senhaCriptografada);
        repository.save(usuario);

        List<UsuarioGrupo> listaUsuarioGrupo = grupos.stream().map(nomeGrupo -> {
                    Optional<Grupo> possivelGrupo = grupoRepository.findByNome(nomeGrupo);
                    if (possivelGrupo.isPresent()) {
                        Grupo grupo = possivelGrupo.get();
                        return new UsuarioGrupo(usuario, grupo);
                    }
                    return null;
                })
                .filter(grupo -> grupo != null)
                .collect(Collectors.toList());
        usuarioGrupoRepository.saveAll(listaUsuarioGrupo);

        return usuario;
    }
}
