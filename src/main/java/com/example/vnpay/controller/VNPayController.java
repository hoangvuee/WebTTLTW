package com.example.vnpay.controller;

import com.example.vnpay.service.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

@Path("/vnpay")
public class VNPayController {
    
    private final VNPayService vnPayService = new VNPayService();

    @GET
    @Path("/create-payment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPayment(@Context HttpServletRequest request, @QueryParam("amount") long amount) {
        try {
            String paymentUrl = vnPayService.createPaymentUrl(request, amount);
            JSONObject response = new JSONObject();
            response.put("paymentUrl", paymentUrl);
            return Response.ok(response.toString()).build();
        } catch (UnsupportedEncodingException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error creating payment URL: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/vnpay-payment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response paymentCallback(@Context HttpServletRequest request) {
        JSONObject response = vnPayService.processPaymentResponse(request);
        return Response.ok(response.toString()).build();
    }
} 