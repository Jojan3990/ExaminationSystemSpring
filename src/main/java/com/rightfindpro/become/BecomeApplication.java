package com.rightfindpro.become;

import com.fasterxml.classmate.AnnotationConfiguration;
import com.rightfindpro.become.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@SpringBootApplication
public class BecomeApplication {


    public static void main(String[] args) {
        SpringApplication.run(BecomeApplication.class, args);


    }
    @Bean
    public ModelMapper modelMapper () {
        return new ModelMapper();
    }

//    public SessionFactory sessionFactory(@Qualifier("entityManagerFactory")EntityManagerFactory emf){
//        return emf.unwrap(SessionFactory.class);
//    }

}
