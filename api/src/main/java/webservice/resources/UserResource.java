package webservice.resources;

import webservice.dto.User;
import webservice.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource {

    private UserService userService;

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        return Response.ok(userService.getAllUsers()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int userId){
        return Response.ok(userService.getUser(userId)).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteUser(@PathParam("id") int userId) {

        return Response.ok(userService.deleteUser(userId)).build();
    }

    @PUT
    @Path("/update")
    public Response updateUser(User user) {
        return Response.ok(userService.updateUser(user)).build();
    }


}
