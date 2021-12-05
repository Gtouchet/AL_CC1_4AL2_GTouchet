package esgi.al.cc1.domain.builders;

import esgi.al.cc1.domain.models.Entity;

public interface Builder<T extends Entity> // todo: where do I put my builders ?
{
    T build();
}
