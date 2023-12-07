package com.digicert.hotel.booking.config;

import com.digicert.hotel.booking.exceptions.GlobalExceptionMapper;
import com.digicert.hotel.booking.exceptions.ReservationCreationException;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {

        packages("com/digicert/hotel/booking/controller");
        packages("com/digicert/hotel/booking/exceptions");
        register(GlobalExceptionMapper.class);

    }
}
