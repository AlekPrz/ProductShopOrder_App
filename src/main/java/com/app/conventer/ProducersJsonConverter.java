package com.app.conventer;

import com.app.conventer.Store.CustomerStore;
import com.app.conventer.Store.ProducerStore;

public class ProducersJsonConverter extends converter<ProducerStore> {
    public ProducersJsonConverter(String fileName) {
        super(fileName);
    }
}
