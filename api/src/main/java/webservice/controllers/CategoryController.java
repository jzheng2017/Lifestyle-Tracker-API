package webservice.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
//    private CategoryService categoryService;
//
//    @Inject
//    public void setCategoryService(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
//
//    @GET
//    @Path("/all")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllCategories() {
//        return Response.ok(categoryService.getAll()).build();
//    }
//
//
//    @GET
//    @Path("{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllCategories(@PathParam("id") int categoryId) {
//        return Response.ok(categoryService.getCategory(categoryId)).build();
//    }
//
//    @GET
//    @Path("{id}/children")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllChildren(@PathParam("id") int parentId) {
//        return Response.ok(categoryService.getChildren(parentId)).build();
//    }
}
