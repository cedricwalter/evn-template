package ch.helvetia.evn.controller;

import ch.helvetia.evn.microservices.adapter.CLSAdapterService;
import ch.helvetia.evn.pojo.EVN;
import ch.helvetia.evn.pojo.Nachweis;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class CLSAdapterServiceTest {

    @Inject
    CLSAdapterService service;

    @Test
    public void withNachweis_process_expectEVNIdSet() {
        // Arrange
        Nachweis nachweis = new Nachweis();

        // Act
        EVN evn = service.process(nachweis);

        // Assert
        assertThat(evn.evnId, is(notNullValue()));
    }
}
