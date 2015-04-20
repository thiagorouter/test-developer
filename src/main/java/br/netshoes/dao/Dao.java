package br.netshoes.dao;

import br.netshoes.entity.BaseEntity;

import java.util.List;

public interface Dao<T extends BaseEntity, I> {

        List<T> findAll();

        long count();

        T find(I id);

        T save(T newsEntry);

        void delete(I id);

}
