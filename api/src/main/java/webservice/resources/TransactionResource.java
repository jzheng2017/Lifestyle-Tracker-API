package webservice.resources;

import webservice.dto.Transaction;
import webservice.dto.TransactionRequest;
import webservice.services.TransactionService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/transaction")
public class TransactionResource {

    private TransactionService transactionService;

    @Inject
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTransactions(){
        return Response.ok(transactionService.getAllTransactions()).build();
    }

    @GET
    @Path("/income/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIncome(){
        return Response.ok(transactionService.getAllIncome()).build();
    }

    @GET
    @Path("/expense/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllExpense(){
        return Response.ok(transactionService.getAllExpense()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransaction(@PathParam("id") int transactionId){
        return Response.ok(transactionService.getTransaction(transactionId)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTransaction(TransactionRequest transaction) {
        return Response.ok(transactionService.insertTransaction(transaction)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTransaction(Transaction transaction){
        return Response.ok(transactionService.updateTransaction(transaction)).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTransaction(@PathParam("id") int transactionId){
        return Response.ok(transactionService.deleteTransaction(transactionId)).build();
    }
}
