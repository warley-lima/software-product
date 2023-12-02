package com.Order.Service;

import com.Order.Repository.OrderRepository;
import com.Order.Utils.Constants;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Path("/orderemit")
public class OrderServiceEmiter {
    private static ExecutorService nonBlockingService = Executors.newCachedThreadPool();
    private static long idCompany = 0;
    private final OrderRepository orderBusiness;
    @Inject
    public OrderServiceEmiter(OrderRepository prodBusiness) {
        this.orderBusiness = prodBusiness;
    }

    @GET
    @Path("/sse")
   // @Produces({"text/event-stream"})
    @Produces(MediaType.APPLICATION_JSON)
    @CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
    public static SseEmitter handleSse() {
        SseEmitter emitter = new SseEmitter();

        nonBlockingService.execute(() -> {
            try {
                emitter.send(Constants.API_SSE_MSG + getIdCompany() +" @ " + new Date());
               // emitter.send(this.orderBusiness.findAll());
                System.out.println(emitter.toString());
                emitter.complete();
            } catch (Exception ex) {
                System.out.println(Constants.GENERIC_EXCEPTION);
                emitter.completeWithError(ex);
            }
        });

        return emitter;
    }

    @GET
    @Path("/sse2")
    @Produces(MediaType.APPLICATION_JSON)
    public SseEmitter streamSseMvc() {
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();

        sseMvcExecutor.execute(() -> {
            try {
                for (int i = 0; true; i++) {
                    SseEmitter.SseEventBuilder event = SseEmitter.event()
                            .data("SSE MVC - " + LocalTime.now()
                                    .toString())
                            .id(String.valueOf(i))
                            .name("sse event - mvc");
                    emitter.send(event);
                    Thread.sleep(1000);
                }
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }

    public static long getIdCompany() {
        return idCompany;
    }

    public static void setIdCompany(long id) {
        idCompany = id;
    }
}
