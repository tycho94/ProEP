/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import database.Database;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Item;
import service.ItemService;

/**
 * REST Web Service
 *
 * @author Amir
 */
@Path("item")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemsResource {

    Response r;
    Database db = new Database();
    ItemService itemService = null;

    @GET
    public Response getAllItems() throws Exception {
        List<Item> items = itemService.getAllItems();
        r = null;
        try {
            if (items != null) {
                return r = Response.ok(items).build();
            } else {
                throw new Exception("Nothing exist here!");
            }
        } catch (Exception e) {
            return r = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("{ItemId}")
    public Response getItemByID(@PathParam("ItemId") int id) throws Exception {
        r = null;
        Item item = db.GetItemByID(id);
        try {
            if (item != null) {
                r = Response.ok(item).build();
            } else {
                throw new Exception("Item not found!");
            }
        } catch (Exception e) {
            r = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
        return r;

    }

    @GET
    @Path("rest/{RestaurantID}")
    public Response getItemByRestaurantID(@PathParam("RestaurantID") int id) throws Exception {
        r = null;
        List<Item> items = db.GetItemsByRestaurantID(id);
        try {
            if (items != null) {
                r = Response.ok(items).build();
            } else {
                throw new Exception("Item not found!");
            }
        } catch (Exception e) {
            r = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
        return r;

    }

    @GET
    @Path("search/{search}")
    public Response Search(@PathParam("search") String name) throws Exception {
        r = null;
        List<Item> items = itemService.Search(name);
        try {
            if (items != null) {
                r = Response.ok(items).build();
            } else {
                throw new Exception("We dont have a list of items for that Search phrase.");
            }
        } catch (Exception e) {
            r = Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
        return r;

    }

    @POST
    public Response addItem(Item item) {
        try {
            itemService.addItem(item);
            r = Response.ok().build();
        } catch (Exception e) {
            r = Response.status(Response.Status.FORBIDDEN)
                    .entity(e.getMessage())
                    .build();
        }
        return r;

    }

    @PUT
    @Path("{ItemId}")
    public Response updateItem(@PathParam("ItemId") int id, Item item) {
        r = null;
        try {
            itemService.updateItem(id, item);
            r = Response.ok().build();
        } catch (Exception e) {
            r = Response.status(Response.Status.FORBIDDEN)
                    .entity(e.getMessage())
                    .build();
        }
        return r;

    }

}
