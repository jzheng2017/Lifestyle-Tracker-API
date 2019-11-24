package webservice.resources;

import webservice.services.CategoryService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/category")
public class CategoryResource {
    private CategoryService categoryService;

    @Inject
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories() {
        return Response.ok(categoryService.getAll()).build();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories(@PathParam("id") int categoryId) {
        return Response.ok(categoryService.getCategory(categoryId)).build();
    }

    @GET
    @Path("{id}/children")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllChildren(@PathParam("id") int parentId) {
        return Response.ok(categoryService.getChildren(parentId)).build();
    }
}
