/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import database.Database;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import model.Restaurant;

/**
 *
 * @author tycho
 */
@Path("restaurant")
@Produces(MediaType.APPLICATION_JSON)
public class RestaurantResource {

    Response r;
    Database db = new Database();

    @GET
    @Path("id/{restaurant_ID}")
    public Response getRestaurantByID(@PathParam("restaurant_ID") int id) {
        try {
            r = null;
            Restaurant Res = db.getRestaurantByID(id);
            r = Response.ok(Res).build();
        } catch (Exception e) {
            r = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
        return r;
    }

    @GET
    @Path("name/{resname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRestaurantByName(@PathParam("resname") String res_name) {
        try {
            r = null;
            Restaurant Res = db.getRestaurantByName(res_name);
            r = Response.ok(Res).build();
        } catch (Exception e) {
            r = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
        return r;
    }

    @GET
    @Path("city/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRestaurantsByCity(@PathParam("city") String city) {
        List<Restaurant> res = null;
        r = null;
        try {
            res = db.getRestaurantByCity(city);
            if (res != null) {
                r = Response.ok(res).build();
            } else {
                r = Response.status(Response.Status.NOT_FOUND).
                        entity("Not found").build();
            }
        } catch (Exception e) {
            r = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
        return r;
    }
}
