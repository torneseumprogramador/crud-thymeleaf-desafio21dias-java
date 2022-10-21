package br.com.didox.crud.DAOs;

import org.springframework.data.repository.CrudRepository;

import br.com.didox.crud.models.Profissional;

public interface IProfissionalRepository extends CrudRepository<Profissional, Integer>  {
    
}
