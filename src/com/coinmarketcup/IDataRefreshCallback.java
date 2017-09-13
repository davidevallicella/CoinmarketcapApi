package com.coinmarketcup;

/**
 * Created by grege on 07.08.2017.
 */

public interface IDataRefreshCallback {
    void onSuccess ();
    void onFail ();
}