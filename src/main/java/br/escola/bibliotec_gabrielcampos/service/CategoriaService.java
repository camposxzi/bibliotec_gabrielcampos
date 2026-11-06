package br.escola.bibliotec_gabrielcampos.service;

import br.escola.bibliotec_gabrielcampos.model.Categoria;
import br.escola.bibliotec_gabrielcampos.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAllByOrderByNomeAsc();
    }

    public boolean nomeJaExiste(Categoria categoria) {
        return categoriaRepository.findByNomeIgnoreCase(categoria.getNome().trim())
                .filter(c -> !c.getId().equals(categoria.getId()))
                .isPresent();
    }

    public void salvar(Categoria categoria) {
        categoria.setNome(categoria.getNome().trim());
        categoriaRepository.save(categoria);
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public void excluir(Long id) {
        categoriaRepository.deleteById(id);
    }
}