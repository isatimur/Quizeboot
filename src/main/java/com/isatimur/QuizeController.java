package com.isatimur;


import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.TreeMap;

import static com.isatimur.QuizeType.*;

/**
 * Created by tisachenko on 07.05.16.
 */
@Component
@Path("/quize")
@Produces("application/json")
public class QuizeController {
    private final static Map<Long, Quize> preparedData;

    public static final int HALF_OF_A_MINUTE = 30000;

    static {
        preparedData = new TreeMap<Long, Quize>();
        preparedData.put(0l, Quize.builder().type(LUCKY_BADGE).id(0l).loc(new Location(43.212, 56.4552)).quest(
                QuizeQuest.builder().score(100).question("You're lucky today! \n here is your lucky badge \n and 100 points extra!").timer(HALF_OF_A_MINUTE).build()
        ).build());
        preparedData.put(1l, Quize.builder().type(UNLUCKY_BADGE).id(1l).loc(new Location(42.212, 46.4552)).quest(
                QuizeQuest.builder().score(-100).question("You're unlucky today! \n we are going to take away 100 points from you!").timer(HALF_OF_A_MINUTE).build()
        ).build());
        preparedData.put(2l, Quize.builder().type(RADIO_TIMER).id(2l).loc(new Location(44.212, 45.4552)).quest(
                QuizeQuest.builder().score(50).question("You need to answer the radio question:").choices(new String[]{}).answers(new String[]{"1"}).timer(HALF_OF_A_MINUTE).build()
        ).build());
        preparedData.put(3l, Quize.builder().type(CHECKBOX_TIMER).id(3l).loc(new Location(44.212, 43.4552)).quest(
                QuizeQuest.builder().score(50).question("You need to answer the checkbox question:").choices(new String[]{}).answers(new String[]{"1","2"}).timer(HALF_OF_A_MINUTE).build()
        ).build());
//  preparedData.put(2l, Quize.builder().type(LUCKY_BADGE).id(2l).loc(new Location(53.212, 53.4552)).quest(QuizeQuest.builder().score(100).question("You're lucky today! \n here is your lucky badge \n and 100 points extra!").timer(HALF_OF_A_MINUTE).build()).build());
//        preparedData.put(3l, Quize.builder().type(LUCKY_BADGE).id(3l).loc(new Location(37.212, 52.4552)).quest(QuizeQuest.builder().score(100).question("You're lucky today! \n here is your lucky badge \n and 100 points extra!").timer(HALF_OF_A_MINUTE).build()).build());
//        preparedData.put(4l, Quize.builder().type(LUCKY_BADGE).id(4l).loc(new Location(43.212, 42.4552)).quest(QuizeQuest.builder().score(100).question("You're lucky today! \n here is your lucky badge \n and 100 points extra!").timer(HALF_OF_A_MINUTE).build()).build());
    }


    @Path("/")
    @GET
    public Response allQuizes() {
        return Response.ok(preparedData.values()).status(Response.Status.ACCEPTED).build();
    }


    @Path("/{id}")
    @GET
    public Response quizeById(@PathParam("id") long id) {
        Quize quize = preparedData.get(id);
        if (quize != null) {
            return Response.ok(quize).status(Response.Status.ACCEPTED).build();
        } else {
            return Response.ok().status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/")
    @POST
    public Response newQuize(Quize quize) {

        return Response.ok(preparedData.putIfAbsent(quize.getId(), quize)).status(Response.Status.CREATED).build();
    }

    @Path("/{id}")
    @DELETE
    public void removeQuizeById(@PathParam("id") long id) {
        preparedData.remove(id);
    }

    @Path("/")
    @DELETE
    public void removeQuize(Quize quize) {
        if (preparedData.get(quize.getId()) != null) {
            removeQuizeById(quize.getId());
        }
    }

}
