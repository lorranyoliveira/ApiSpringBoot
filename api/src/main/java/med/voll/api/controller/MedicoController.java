package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.ListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired  //faz a injeção de dependencia
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public void Cadastrar(@RequestBody @Valid DadosCadastroMedico dados)
    {

        System.out.println(dados.endereco());
        repository.save(new Medico(dados));

    }

    /*@GetMapping
    public List<ListagemMedico> listarMedicos()
    {
        return repository.findAll().stream().map(ListagemMedico::new).toList();

    }*/

    /*a paginação melhora a performance na hora de recuperar dados do banco*/
    @GetMapping
    public Page<ListagemMedico> listarMedicos(Pageable paginacao )
    {
        return repository.findAll(paginacao).map(ListagemMedico::new);

    }
}
