package com.api.core.utils;

import io.restassured.http.Method;

import java.util.Map;
import java.util.function.Consumer;

public class RequestBuilder {

    public RequestBuilder() {

    }

    public Map<String, Object> QueryParameters;

    public String ApiPath;
    public Method RequestType;
    private Map<String, String> Headers;
    private String ContentType;
    private String RequestBody;

    public String BaseUrl;
    public int Port;

    /** +
     *
     * @param builderFunction
     * @return
     */
    public RequestBuilder With(
            Consumer<RequestBuilder> builderFunction) {
        builderFunction.accept(this);
        return this;
    }

    public Request buildRequestObject() {
        return new Request(BaseUrl,ApiPath, RequestType, Headers, RequestBody, QueryParameters,ContentType
                );
    }

}
