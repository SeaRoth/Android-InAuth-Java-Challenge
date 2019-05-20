package com.searoth.api.model.entity;

public class Coin<T> {

    private CoinInfo CoinInfo;

    public CoinInfo getCoinInfo() {
        return CoinInfo;
    }

    public void setCoinInfo(CoinInfo coinInfo) {
        CoinInfo = coinInfo;
    }

    public class CoinInfo{
        String Id;
        String Name;
        String ImageUrl;

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String imageUrl) {
            ImageUrl = imageUrl;
        }

        public String getName() {
            return Name;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public void setName(String name) {
            Name = name;
        }
    }
}
