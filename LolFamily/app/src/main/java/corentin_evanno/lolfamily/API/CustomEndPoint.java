package corentin_evanno.lolfamily.API;

import retrofit.Endpoint;


public final class CustomEndPoint implements Endpoint {
    private static final String protocol = "https://";
    private static final String base = ".api.pvp.net/";
    private String region;
    private String url;

    public void setUrl(String region) {
        this.region = region;
        this.url = protocol + region + base;
    }

    @Override
    public String getName() {
        return (this.region);
    }

    @Override
    public String getUrl() {
        if (this.url == null) {
            return (null);
        }
        return this.url;
    }
}
