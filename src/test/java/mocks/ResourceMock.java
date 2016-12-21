package mocks;

import http.HttpMethod;
import routing.annotations.Resource;
import routing.annotations.SubResource;

@Resource(name = "Resource Mock", endpoint = "/resource-mock", description = "Actions on resource mocks")
public class ResourceMock {

    @SubResource(path = "/", method = HttpMethod.GET, accept = "", description = "Show all resource mocks")
    public String showAll() {
        return "";
    }

    @SubResource(path = "/{id}", method = HttpMethod.GET, accept = "", description = "Show one resource mock")
    public String showOne() {
        return "";
    }

}
