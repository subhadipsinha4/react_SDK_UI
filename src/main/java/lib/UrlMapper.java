package lib;

public enum UrlMapper {

    LOGIN("/unbxdlogin"),
    SEARCH_DASHBOARD("/search/sites/%s/dashboard"),
    BROWSE_DASHBOARD("/category-pages/sites/%s/dashboard"),
    CATEGORY_PAGES("/category-pages/sites/%s/browse"),
    SEARCH_PAGE("/search/sites/%s/query-rules"),

    LIBRARIES("/search/sites/%s/synonyms#"),
    TYPEAHEAD("/search/sites/%s/promoted_suggestions"),

    SEARCH_REPORT("/search/sites/%s/reports/overview"),
    TYPEAHEAD_REPORT("/search/sites/%s/reports/typeahead-reports"),
    PRODUCT_REPORT("/search/sites/%s/reports/product-report"),

    BROWSE_REPORT("/category-pages/sites/%s/reports/overview"),
    BROWSE_CONFIGURE_SITE("/category-pages/sites/%s/keys"),


    SEARCH_CONFIGURE_SITE("/search/sites/%s/keys"),
    CONFIGURE_SEARCH("/sites/%S/relevancy"),


    SEARCH_FACETS_CONFIG("/search/sites/%s/global-facets"),
    SEGMENTS("/category-pages/sites/%s/segments"),

    ACCOUNT_MANAGEMENT("/search/sites/%s/manage");


    private final String urlPath;
    UrlMapper(String url) {
        this.urlPath=url;
    }

    public String getUrlPath(String... args)
    {
        String result=String.format(this.urlPath,args);
        return EnvironmentConfig.getApplicationUrl()+result;
    }


}
