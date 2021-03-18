package com.app.conventer.Store;

import com.app.model.Trade;
import lombok.Data;

import java.util.Set;

@Data
public class TradeStore {
    private Set<Trade> trades;
}
