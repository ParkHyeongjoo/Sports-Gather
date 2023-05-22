package kr.co.opennote.booster.mapper;

import java.util.List;

public interface GenericMapper<D, E>{
    List<D> toDto(List<E> e);
    D toDto(E e);
    E toEntity(D d);
}
