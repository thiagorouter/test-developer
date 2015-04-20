package br.netshoes.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Thiago on 16/04/2015.
 */
public interface BaseService<T, I> extends Serializable {

    public T save(final T endereco);

    public T find(final I id);

    public void delete(final I id);

    public List<T> findAll();

    public List<T> findAll(final Integer maxResult);

}
