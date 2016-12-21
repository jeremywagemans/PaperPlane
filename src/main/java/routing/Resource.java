package routing;

import http.HttpMethod;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Resource {
    private String name;
    private String endpoint;
    private String description;
    private Object implInstance;

    Map<HttpMethod, List<SubResource>> subResources;

    public Resource(String name, String endpoint, String description, Object implInstance) {
        this.name = name;
        this.endpoint = endpoint;
        this.description = description;
        this.implInstance = implInstance;
        this.subResources = new HashMap<HttpMethod, List<SubResource>>();
    }

    public Resource() {
        this(null, null, null, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getImplInstance() {
        return implInstance;
    }

    public void setImplInstance(Object implInstance) {
        this.implInstance = implInstance;
    }

    public void addSubResource(SubResource subResource) {
        HttpMethod httpMethod = subResource.getMethod();
        List<SubResource> subResourceList = subResources.get(httpMethod);
        if(subResourceList == null) {
            subResourceList = new LinkedList<SubResource>();
            subResources.put(httpMethod, subResourceList);
        }
        subResourceList.add(subResource);
    }

    public void getSubResource(HttpMethod method, String path) {

    }

}
