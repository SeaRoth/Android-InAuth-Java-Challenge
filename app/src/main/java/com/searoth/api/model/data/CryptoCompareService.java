package com.searoth.api.model.data;

import com.searoth.api.model.entity.Coin;
import com.searoth.api.model.entity.Response;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface CryptoCompareService {
    String BASE_URL = "https://min-api.cryptocompare.com/data/top/";

    @GET("totalvolfull")
    Observable<Response<List<Coin>>> getCurrencies(@Query("limit") Integer limit, @Query("tsym") String tsym);
}
