package com.Order.Service;


import com.Order.Business.OrderBusiness;
import com.Order.Model.Order;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;
import org.glassfish.jersey.media.sse.SseFeature;
import org.springframework.stereotype.Component;
/*import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;*/
import java.util.Date;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
/*import javax.ws.rs.core.Context;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
//import javax.ws.rs.sse.SseBroadcaster;

import javax.ws.rs.sse.SseEventSink;*/
/*import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;*/

//import javax.ws.rs.*;
/*import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;
import javax.ws.rs.sse.OutboundSseEvent;*/
// import javax.ws.rs.sse.OutboundSseEvent;
//import javax.ws.rs.sse.OutboundSseEvent;
//import javax.ws.rs.sse.SseEventSink;
//import javax.ws.rs.sse.SseBroadcaster;
/*
import org.glassfish.jersey.media.sse.SseBroadcaster;
import org.glassfish.jersey.media.sse.SseBroadcaster;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;*/

@Component
@Path("/orderpub")
@Singleton
public class OrderPublicService {
    private final OrderBusiness orderBusiness;
   // @Inject
   //@Context
  //  private Sse sse;
   // private SseBroadcaster broadcaster;
   private SseBroadcaster broadcaster = new SseBroadcaster();
  // private long t = 7;
    //private SseBroadcaster broadcaster; //= new SseBroadcaster();
  // private final OrderServiceEmiter sse;
    @Inject
    public OrderPublicService(OrderBusiness prodBusiness) {
        this.orderBusiness = prodBusiness;
       // this.sse = sse;
      //  this.sse = Sse
      //  this.broadcaster = sse.newBroadcaster();
    }

    /*@Context
    public void setSse(Sse sse) {
        this.sse = sse;
       // this.eventBuilder = sse.newEventBuilder();
        this.broadcaster = sse.newBroadcaster();
    }*/

  /* @GET
   @Path("/c")
   @Produces(MediaType.APPLICATION_JSON)
   public Collection<Order> getProductsById(@QueryParam("id") long id) {
       List<Order> ret = new ArrayList<>();
       try {
           ret =  orderBusiness.getProductsByIdCompany(id);
       }catch (Exception e){
           e.printStackTrace();
       }
       return ret;
   }
*/
   /* private ExecutorService executor = Executors.newCachedThreadPool();
    private long idCompany = 0;
    public long getIdCompany() {
        return idCompany;
    }
    public void setIdCompany(long id) {
        idCompany = id;
    }
    @Path("/rbe")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Async
    public ResponseEntity<ResponseBodyEmitter> handleRbe() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        executor.execute(() -> {
            try {
                emitter.send("/rbe" + " @ " + new Date(), org.springframework.http.MediaType.TEXT_PLAIN);
              //  emitter.send(Constants.API_SSE_MSG + getIdCompany() +" @ " + new Date());
                System.out.println(emitter.toString());
                emitter.complete();
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return new ResponseEntity(emitter, HttpStatus.OK);
    }*/


    /*@GET
    @Path("/rbe3")
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput getServerSentEvents() {
        final EventOutput eventOutput = new EventOutput();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        // ... code that waits 1 second
                        final OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
                        System.out.println("Pega----> " + i);
                        eventBuilder.name("message-to-client");
                        eventBuilder.data(String.class, "Hello world " + i + "!");
                        final OutboundEvent event = eventBuilder.build();
                        eventOutput.write(event);
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Error when writing the event.", e);
                } finally {
                    try {
                        eventOutput.close();
                    } catch (IOException ioClose) {
                        throw new RuntimeException("Error when closing the event output.", ioClose);
                    }
                }
            }
        }).start();
        return eventOutput;
    }*/

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    /*public String broadcastMessage(String message) {
       final OutboundSseEvent event = sse.newEventBuilder()
                .name("message")
                .mediaType(MediaType.TEXT_PLAIN_TYPE)
                .data(String.class, message)
                .build();

        broadcaster.broadcast(event);

        return "Message '" + message + "' has been broadcast.";
    }*/

    public String broadcastMessage(long message) {
        OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
        OutboundEvent event = eventBuilder.name("m")
                .mediaType(MediaType.TEXT_PLAIN_TYPE)
               // .data(String.class, message)
                .data(Long.class, message)
                .reconnectDelay(500000)
                .build();
        broadcaster.broadcast(event);
      //  System.out.println("Obrigado Deus------->>" + message);
        return "";
    }

    @GET
    @Path("/rbe2")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    /*public void listenToBroadcast(@Context SseEventSink eventSink) {
        this.broadcaster.register(eventSink);
    }*/
    public EventOutput listenToBroadcast() {
        final EventOutput eventOutput = new EventOutput();
       // eventOutput.
        // eventOutput.
       // this.broadcaster.
        this.broadcaster.add(eventOutput);
        return eventOutput;
    }


    @GET
    @Path("/rbe3")
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput getServerSentEvents() {
        final EventOutput eventOutput = new EventOutput();
       /* if (0 != getIdCompany()){
                new Thread(() -> {
                    try {
                        final OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
                        System.out.println("Pega----> " + getIdCompany());
                        eventBuilder.name("message-to-client");
                        eventBuilder.data(String.class, "Novo Pedido " + getIdCompany());
                        final OutboundEvent event = eventBuilder.build();
                        eventOutput.write(event);
                        setIdCompany(0);
                    } catch (IOException e) {
                        throw new RuntimeException("Error when writing the event.", e);
                    } finally {
                        try {
                            eventOutput.close();
                        } catch (IOException ioClose) {
                            throw new RuntimeException("Error when closing the event output.", ioClose);
                        }
                    }
                }).start();
         }else{
            new Thread(() -> {
                try {
                    final OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
                    System.out.println("Pega 2----> " + getIdCompany());
                    eventBuilder.name("message-to-client");
                    eventBuilder.data(String.class, getIdCompany());
                    final OutboundEvent event = eventBuilder.build();
                    eventOutput.write(event);
                    setIdCompany(0);
                } catch (IOException e) {
                    throw new RuntimeException("Error when writing the event.", e);
                } finally {
                    try {
                        eventOutput.close();
                    } catch (IOException ioClose) {
                        throw new RuntimeException("Error when closing the event output.", ioClose);
                    }
                }
            }).start();
        }*/
        /*new Thread(() -> {
            try {
                final OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
                System.out.println("Pega 2----> " + getIdCompany());
                eventBuilder.name("m");
                eventBuilder.data(Long.class, getIdCompany());
                final OutboundEvent event = eventBuilder.build();
                eventOutput.write(event);
                setIdCompany(0);
            } catch (IOException e) {
                throw new RuntimeException("Error when writing the event.", e);
            } finally {
                try {
                    eventOutput.close();
                } catch (IOException ioClose) {
                    throw new RuntimeException("Error when closing the event output.", ioClose);
                }
            }
        }).start();*/
        return eventOutput;
    }

    @POST
    @Path("/s")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /*public Order makeOrder(final Order order) {*/
     public String makeOrder(Order order) {
      // Order orderRet = null;
       String orderRet = "N;";
        try {
            /*
            //create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();
            //convert json string to object
             order = objectMapper.readValue(jsonData, Order.class);
           // prod = orderBusiness.getProduct(id);
            */
           /* System.out.println("Nome Cliente-->" + order.getNameClient());
            System.out.println("\nValor do Pedido R$-->" + order.getValorTotal());
            System.out.println("\nData do Pedido R$-->" + order.getDataPedido());*/
            if(null != order.getLstProduct()) {
                order.getLstProduct().stream().forEach(product -> product.setOrder(order));
            }
            order.setStatus(1);
            order.setDataPedidoServer(new Date());
           /* orderRet = orderBusiness.save(order);
            orderRet.setLstProduct(null);*/
            orderBusiness.save(order);
            orderRet = "S;".concat(Long.toString(order.getIdOrder()));
           // OrderServiceEmiter sse = new OrderServiceEmiter();
           /* OrderServiceEmiter.setIdCompany(order.getIdCompany());
            OrderServiceEmiter.handleSse();*/
          //  System.out.println("Teste----> " + order.getIdOrder());
          //  order.setNameClient(order.getNameClient().concat("(Checked)"));
          //  setIdCompany(order.getIdCompany());
           // handleRbe();
          //  getServerSentEvents();

          broadcastMessage(order.getIdCompany());
         //   getServerSentEvents();
        }catch (Exception e){
            e.printStackTrace();
        }
       // System.out.println(orderRet);
        return orderRet;
    }




   /* @POST
    @Path("/teste")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        String orderRet = "TESTE--> ";
        try {
            setIdCompany(t);
       //  System.out.println("DEUS--> " + t);
            orderRet = orderRet.concat(Long.toString(t));
            t++;
            broadcastMessage(1);
        }catch (Exception e){
            e.printStackTrace();
        }
       // System.out.println(orderRet + t);
        return orderRet;
    }*/

}
