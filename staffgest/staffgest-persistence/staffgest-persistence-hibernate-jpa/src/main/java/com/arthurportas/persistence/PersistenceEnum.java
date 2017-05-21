package com.arthurportas.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by arthurportas on 10/05/2017.
 */
@Getter
@AllArgsConstructor
public enum PersistenceEnum {

    PERSISTENCE_UNIT("jpa-example");

    private String value;

}
